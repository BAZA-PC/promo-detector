package com.example.promodetector.model;

import com.example.promodetector.util.SafeNumberDeserializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

@Data
public class Price {

    @JsonProperty("Chain_name")
    private String chainName;

    @JsonProperty("Material No")
    private String materialNo;

    @JsonProperty("Regular price per unit")
    @JsonDeserialize(using = SafeNumberDeserializer.class)
    private Double regularPricePerUnit;
}