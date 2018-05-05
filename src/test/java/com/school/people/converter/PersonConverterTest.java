package com.school.people.converter;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.school.people.controller.json.PersonJson;
import com.school.people.entity.Person;
import com.school.people.templates.PersonTemplates;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class PersonConverterTest {

    private final PersonConverter personConverter = new PersonConverter();

    @Before
    public void setup() {
        FixtureFactoryLoader.loadTemplates("com.school.people.templates");
    }

    @Test
    public void personJsonConverterTest(){
        final List<Person> personList = Fixture.from(Person.class).gimme(1, PersonTemplates.VALID_1);
        final List<PersonJson> personJsonList = personConverter.convert(personList);
        final PersonJson personJson = personJsonList.get(0);
        final Person person = personList.get(0);

        Assert.assertEquals(personJson.getId(), person.getId());
        Assert.assertEquals(personJson.getName(), person.getName());
        Assert.assertEquals(personJson.getLastName(), person.getLastName());
        Assert.assertEquals(personJson.getDocumentId(), person.getDocumentId());
        Assert.assertEquals(personJson.getBirthday(), person.getBirthday());
        Assert.assertEquals(personJson.isStatus(), person.isStatus());
        Assert.assertEquals(personJson.getAddress().getStreet(), person.getAddress().getStreet());
        Assert.assertEquals(personJson.getAddress().getNumber(), person.getAddress().getNumber());
        Assert.assertEquals(personJson.getAddress().getZipcode(), person.getAddress().getZipcode());
        Assert.assertEquals(personJson.getAddress().getCity(), person.getAddress().getCity());
        Assert.assertEquals(personJson.getAddress().getState(), person.getAddress().getState());

    }
}
