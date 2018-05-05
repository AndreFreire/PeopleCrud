package com.school.people.templates;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.school.people.entity.Address;
import com.school.people.entity.Person;
import com.school.people.entity.Phone;

import java.time.LocalDate;
import java.util.Collections;

public class PersonTemplates implements TemplateLoader {

    public static final String VALID_1 = "valid1";
    public static final String VALID_2 = "valid2";
    public static final String VALID_WITH_NO_ID = "validWithNoId";

    @Override
    public void load() {
        Fixture.of(Person.class).addTemplate(VALID_1, new Rule() {{
            add("id", "1");
            add("name", "Pipoqueiro");
            add("lastName", "Osvaldo");
            add("documentId", "44157761243");
            add("birthday", LocalDate.now());
            add("address", one(Address.class, AddressTemplates.VALID));
            add("phones", has(1).of(Phone.class, PhoneTemplates.VALID));
            add("emails", Collections.singletonList("osvaldo@pipoqueiro.com.br"));
            add("status", true);
        }});

        Fixture.of(Person.class).addTemplate(VALID_WITH_NO_ID).inherits(VALID_1, new Rule() {{
            add("id", null);
        }});

        Fixture.of(Person.class).addTemplate(VALID_2, new Rule() {{
            add("id", "2");
            add("name", "Marceneiro");
            add("lastName", "Pedro");
            add("documentId", "51822313007");
            add("birthday", LocalDate.now());
            add("address", one(Address.class, AddressTemplates.VALID));
            add("phones", has(1).of(Phone.class, PhoneTemplates.VALID));
            add("emails", Collections.singletonList("osvaldo@pipoqueiro.com.br"));
        }});
    }
}
