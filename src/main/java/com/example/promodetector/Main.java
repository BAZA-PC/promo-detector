package com.example.promodetector;

import com.example.promodetector.model.*;
import com.example.promodetector.service.PromoDetectorService;
import com.example.promodetector.util.JsonLoader;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    CommandLineRunner init(PromoDetectorService service) {
        return args cd "C:\Users\BAZA PC\Desktop\testR"-> {
            List<Actual> actuals = JsonLoader.loadList("/json/Actuals.json", new TypeReference<>() {});
            List<Customer> customers = JsonLoader.loadList("/json/Customers.json", new TypeReference<>() {});
            List<Price> prices = JsonLoader.loadList("/json/Price.json", new TypeReference<>() {});
            service.loadData(actuals, customers, prices);
            System.out.println("Загружены исходные данные.");
        };
    }
}