package com.school.people.controller.json;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.school.people.conf.json.LocalDateDeserializer;
import com.school.people.conf.json.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonJson implements Serializable{

    private static final long serialVersionUID = 7733586500201019476L;

    private String id;

    @NotNull
    private String name;

    @NotNull
    private String lastName;

    @NotNull
    private String documentId;

    @NotNull
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate birthday;

    @Valid
    @NotNull
    private AddressJson address;

    @Valid
    @NotNull
    @NotEmpty
    private List<PhoneJson> phones;

    @NotNull
    @NotEmpty
    private List<String> emails;

    private boolean status = true;

}
