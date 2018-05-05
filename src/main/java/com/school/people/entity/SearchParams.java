package com.school.people.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchParams {

    private String name;

    private String lastName;

    private String documentId;

}
