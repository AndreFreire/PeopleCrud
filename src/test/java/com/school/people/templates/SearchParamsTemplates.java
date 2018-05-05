package com.school.people.templates;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.school.people.entity.SearchParams;
import org.apache.commons.lang.StringUtils;

public class SearchParamsTemplates implements TemplateLoader {
    
    public static final String VALID_COMPLETE = "validComplete";
    public static final String VALID_NAME = "validName";
    public static final String VALID_LAST_NAME = "validLastName";
    public static final String VALID_DOCUMENT_ID = "validDocumentId";
    
    @Override
    public void load() {
        Fixture.of(SearchParams.class).addTemplate(VALID_COMPLETE, new Rule(){{
            add("name", "Pipoqueiro");
            add("lastName", "Pedro");
            add("documentId", "44157761243");
        }});

        Fixture.of(SearchParams.class).addTemplate(VALID_NAME, new Rule(){{
            add("name", "Pipoqueiro");
            add("lastName", StringUtils.EMPTY);
            add("documentId", StringUtils.EMPTY);
        }});

        Fixture.of(SearchParams.class).addTemplate(VALID_LAST_NAME, new Rule(){{
            add("name", StringUtils.EMPTY);
            add("lastName", "Osvaldo");
            add("documentId", StringUtils.EMPTY);
        }});

        Fixture.of(SearchParams.class).addTemplate(VALID_DOCUMENT_ID, new Rule(){{
            add("name", StringUtils.EMPTY);
            add("lastName", StringUtils.EMPTY);
            add("documentId", "44157761243");
        }});
    }
}
