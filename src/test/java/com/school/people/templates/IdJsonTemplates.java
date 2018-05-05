package com.school.people.templates;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.school.people.controller.json.IdJson;

public class IdJsonTemplates implements TemplateLoader {

    public static final String VALID = "valid";

    @Override
    public void load() {
        Fixture.of(IdJson.class).addTemplate(VALID, new Rule() {{
            add("id", "1");
        }});
    }
}
