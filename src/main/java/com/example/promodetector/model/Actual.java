package com.example.promodetector.model;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonProperty;


@Data
public class Actual {

    @JsonProperty("CH3 Ship To Code")
    private String shipToCode;

    @JsonProperty("Material No")
    private String materialNo;

    @JsonProperty("Volume, units")
    private double volume;

    @JsonProperty("Actual Sales Value")
    private double actualSalesValue;

    @JsonProperty("Date")
    private String date;
}