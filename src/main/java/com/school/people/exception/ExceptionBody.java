package com.school.people.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ExceptionBody implements Serializable {

    private static final long serialVersionUID = -902836543740423760L;

    private String message;
}
