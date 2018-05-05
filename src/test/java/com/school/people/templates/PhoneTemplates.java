package com.school.people.templates;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.school.people.entity.Phone;

import static com.school.people.entity.PhoneType.CELL_PHONE;

public class PhoneTemplates implements TemplateLoader{

    public static final String VALID = "valid";

    @Override
    public void load() {
        Fixture.of(Phone.class).addTemplate(VALID, new Rule(){{
            add("number", "(11)987675672");
            add("type", CELL_PHONE);
        }});
    }
}
