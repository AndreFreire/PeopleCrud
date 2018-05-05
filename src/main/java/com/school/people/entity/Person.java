package com.school.people.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    private String id;

    @Indexed
    private String name;

    @Indexed
    private String lastName;

    @Indexed(unique = true)
    private String documentId;

    private LocalDate birthday;

    private Address address;

    private List<Phone> phones;

    private List<String> emails;

    private boolean status;

}
