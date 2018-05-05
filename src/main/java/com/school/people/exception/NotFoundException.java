package com.school.people.exception;

import lombok.extern.slf4j.Slf4j;

import static java.lang.String.format;

@Slf4j
public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = -450340440544260053L;

    public NotFoundException(final String entity) {
        super(format("Not found entity: %s", entity));
        log.info(String.format("Not found entity: %s", entity));
    }

    public NotFoundException() {
        super("Entity not found");
        log.info("Entity not found");
    }
}
