package com.school.people.templates;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.school.people.controller.json.ListIdJson;

import java.util.Collections;

public class ListIdJsonTemplates implements TemplateLoader {

    public static final String VALID = "valid";

    @Override
    public void load() {
        Fixture.of(ListIdJson.class).addTemplate(VALID, new Rule() {{
            add("ids", Collections.singletonList("1"));
        }});
    }
}
