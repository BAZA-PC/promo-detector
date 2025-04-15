package com.example.promodetector.service;

import com.example.promodetector.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PromoDetectorService {

    private final List<ActualWithPriceType> actuals = new ArrayList<>();
    private final Map<String, Customer> customers = new HashMap<>();
    private final Map<String, Map<String, Double>> priceMap = new HashMap<>();

    public void loadData(List<Actual> actualList, List<Customer> customerList, List<Price> priceList) {
        customers.clear();
        priceMap.clear();
        actuals.clear();

        customerList.forEach(c -> customers.put(c.getShipToCode(), c));
        for (Price p : priceList) {
            priceMap.computeIfAbsent(p.getChainName(), k -> new HashMap<>())
                    .put(p.getMaterialNo(), p.getRegularPricePerUnit());
        }

        for (Actual a : actualList) {
            ActualWithPriceType awp = new ActualWithPriceType();
            awp.setShipToCode(a.getShipToCode());
            awp.setMaterialNo(a.getMaterialNo());
            awp.setVolume(a.getVolume());
            awp.setActualSalesValue(a.getActualSalesValue());
            awp.setDate(a.getDate());
            awp.setPriceType(determinePriceType(a));
            actuals.add(awp);
        }
    }

    public List<ActualWithPriceType> getAll(String priceType) {
        if (priceType == null) return new ArrayList<>(actuals);
        return actuals.stream()
                .filter(a -> a.getPriceType().equalsIgnoreCase(priceType))
                .collect(Collectors.toList());
    }

    public void addActual(Actual newActual) {
        ActualWithPriceType awp = new ActualWithPriceType();
        awp.setShipToCode(newActual.getShipToCode());
        awp.setMaterialNo(newActual.getMaterialNo());
        awp.setVolume(newActual.getVolume());
        awp.setActualSalesValue(newActual.getActualSalesValue());
        awp.setDate(newActual.getDate());
        awp.setPriceType(determinePriceType(newActual));
        actuals.add(awp);
    }

    private String determinePriceType(Actual a) {
        Customer customer = customers.get(a.getShipToCode());
        if (customer == null) return "неизвестный";

        Map<String, Double> productPrices = priceMap.get(customer.getChainName());
        if (productPrices == null) return "неизвестный";

        Double regularPrice = productPrices.get(a.getMaterialNo());
        if (regularPrice == null) return "неизвестный";

        double volume = a.getVolume();
        if (volume == 0) return "недействительный";

        double unitPrice = a.getActualSalesValue() / volume;
        return Math.abs(unitPrice - regularPrice) < 0.01 ? "регулярный" : "акция";
    }
}