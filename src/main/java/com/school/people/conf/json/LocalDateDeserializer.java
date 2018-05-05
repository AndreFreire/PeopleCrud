package com.school.people.conf.json;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateDeserializer extends StdDeserializer<LocalDate> {

    private static final long serialVersionUID = 7743363299651082721L;

    public LocalDateDeserializer() {
        this(null);
    }

    LocalDateDeserializer(final Class<?> vc) {
        super(vc);
    }


    @Override
    public LocalDate deserialize(final JsonParser p, final DeserializationContext ctxt) throws IOException {
        return LocalDate.parse(p.getText(), DateTimeFormatter.ofPattern(LocalDateSerializer.LOCAL_DATE_PATTERN));
    }
}