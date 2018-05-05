package com.school.people.controller.json;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IdJson implements Serializable {

    private static final long serialVersionUID = 2808305175284679864L;

    private String id;

}
