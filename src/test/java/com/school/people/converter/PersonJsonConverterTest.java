package com.school.people.converter;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.school.people.controller.json.PersonJson;
import com.school.people.entity.Person;
import com.school.people.templates.PersonJsonTemplates;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class PersonJsonConverterTest {

    private final PersonJsonConverter personJsonConverter = new PersonJsonConverter();

    @Before
    public void setup() {
        FixtureFactoryLoader.loadTemplates("com.school.people.templates");
    }

    @Test
    public void personJsonConverterTest(){
        final List<PersonJson> personJsonList = Fixture.from(PersonJson.class).gimme(1, PersonJsonTemplates.VALID);
        final List<Person> personList = personJsonConverter.convert(personJsonList);
        final Person person = personList.get(0);
        final PersonJson personJson = personJsonList.get(0);

        Assert.assertEquals(person.getId(), personJson.getId());
        Assert.assertEquals(person.getName(), personJson.getName());
        Assert.assertEquals(person.getLastName(), personJson.getLastName());
        Assert.assertEquals(person.getDocumentId(), personJson.getDocumentId());
        Assert.assertEquals(person.getBirthday(), personJson.getBirthday());
        Assert.assertEquals(person.isStatus(), personJson.isStatus());
        Assert.assertEquals(person.getAddress().getStreet(), personJson.getAddress().getStreet());
        Assert.assertEquals(person.getAddress().getNumber(), personJson.getAddress().getNumber());
        Assert.assertEquals(person.getAddress().getZipcode(), personJson.getAddress().getZipcode());
        Assert.assertEquals(person.getAddress().getCity(), personJson.getAddress().getCity());
        Assert.assertEquals(person.getAddress().getState(), personJson.getAddress().getState());

    }
}
