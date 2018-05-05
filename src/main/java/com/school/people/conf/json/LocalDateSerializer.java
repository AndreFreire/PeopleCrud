package com.school.people.conf.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateSerializer extends StdSerializer<LocalDate> {

    private static final long serialVersionUID = -8547510821733185958L;

    public static final String LOCAL_DATE_PATTERN = "yyyyMMdd";

    protected LocalDateSerializer() {
        super(LocalDate.class);
    }

    @Override
    public void serialize(final LocalDate value, final JsonGenerator gen, final SerializerProvider provider) throws IOException {
        gen.writeNumber(Long.valueOf(value.format(DateTimeFormatter.ofPattern(LOCAL_DATE_PATTERN))));
    }
}