package com.school.people.templates;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.school.people.controller.json.AddressJson;
import com.school.people.controller.json.PersonJson;
import com.school.people.controller.json.PhoneJson;

import java.time.LocalDate;
import java.util.Collections;

public class PersonJsonTemplates implements TemplateLoader{

    public static final String VALID = "valid";
    public static final String VALID_WITH_NO_ID = "validWithNoId";

    @Override
    public void load() {
        Fixture.of(PersonJson.class).addTemplate(VALID, new Rule() {{
            add("id", "1");
            add("name", "Pipoqueiro");
            add("lastName", "Osvaldo");
            add("documentId", "44157761243");
            add("birthday", LocalDate.now());
            add("address", one(AddressJson.class, AddressJsonTemplates.VALID));
            add("phones", has(1).of(PhoneJson.class, PhoneJsonTemplates.VALID));
            add("emails", Collections.singletonList("osvaldo@pipoqueiro.com.br"));
        }});

        Fixture.of(PersonJson.class).addTemplate(VALID_WITH_NO_ID, new Rule() {{
            add("name", "Pipoqueiro");
            add("lastName", "Osvaldo");
            add("documentId", "44157761243");
            add("birthday", LocalDate.now());
            add("address", one(AddressJson.class, AddressJsonTemplates.VALID));
            add("phones", has(1).of(PhoneJson.class, PhoneJsonTemplates.VALID));
            add("emails", Collections.singletonList("osvaldo@pipoqueiro.com.br"));

        }});
    }
}
