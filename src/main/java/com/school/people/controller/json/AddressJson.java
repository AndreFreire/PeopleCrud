package com.school.people.controller.json;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressJson implements Serializable {

    private static final long serialVersionUID = -2227160366468547967L;

    @NotNull
    private String street;

    @NotNull
    private String number;

    @NotNull
    private String zipcode;

    @NotNull
    private String neighborhood;

    @NotNull
    private String city;

    @NotNull
    private String state;

}

