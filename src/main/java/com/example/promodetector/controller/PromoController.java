package com.example.promodetector.controller;

import com.example.promodetector.model.Actual;
import com.example.promodetector.model.ActualWithPriceType;
import com.example.promodetector.service.PromoDetectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/actuals")
@RequiredArgsConstructor
public class PromoController {

    private final PromoDetectorService promoService;

    @GetMapping
    public List<ActualWithPriceType> getAll(@RequestParam(required = false) String priceType) {
        return promoService.getAll(priceType);
    }

    @PostMapping
    public void add(@RequestBody Actual actual) {
        promoService.addActual(actual);
    }
}