package com.school.people.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private String street;

    private String number;

    private String zipcode;

    private String neighborhood;

    private String city;

    private String state;

}

