package com.fidenz.model.dto;

import lombok.Data;

@Data
public class Address {
    private String number;
    private String street;
    private String city;
    private String state;
    private String zipcode;
}
