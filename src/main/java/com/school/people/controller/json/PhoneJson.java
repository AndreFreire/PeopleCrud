package com.school.people.controller.json;

import com.school.people.entity.PhoneType;
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
public class PhoneJson implements Serializable{

    private static final long serialVersionUID = -9198665592028203879L;

    @NotNull
    private String number;

    @NotNull
    private PhoneType type;
}
