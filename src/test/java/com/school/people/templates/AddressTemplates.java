package com.school.people.templates;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.school.people.entity.Address;

public class AddressTemplates implements TemplateLoader{

    public static final String VALID = "valid";

    @Override
    public void load() {
        Fixture.of(Address.class).addTemplate(VALID, new Rule(){{
            add("street", "Rua das pipocas");
            add("number", "1357");
            add("zipcode", "02256000");
            add("neighborhood", "Vila do Milho");
            add("city", "São Paulo");
            add("state", "SP");
        }});
    }
}
