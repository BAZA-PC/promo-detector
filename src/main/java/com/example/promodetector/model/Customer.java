package com.example.promodetector.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Customer {

    @JsonProperty("CH3 Ship To Code")
    private String shipToCode;

    @JsonProperty("CH3 Ship To Name")
    private String shipToName;

    @JsonProperty("Chain_name")
    private String chainName;
}