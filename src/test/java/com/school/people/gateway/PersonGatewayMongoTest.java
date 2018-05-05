package com.school.people.gateway;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.school.people.entity.Person;
import com.school.people.entity.SearchParams;
import com.school.people.templates.PersonTemplates;
import com.school.people.templates.SearchParamsTemplates;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataMongoTest
@ComponentScan("com.school.people.gateway")
public class PersonGatewayMongoTest {

    @Autowired
    private PersonGateway personGateway;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Before
    public void setup() {
        FixtureFactoryLoader.loadTemplates("com.school.people.templates");
        mongoTemplate.findAllAndRemove(new Query(), Person.class);
    }

    @Test
    public void createPersonTest() {
        final List<Person> people = Fixture.from(Person.class).gimme(1, PersonTemplates.VALID_WITH_NO_ID);

        final List<Person> savedPeople = personGateway.create(people);

        assertFalse(savedPeople.stream().anyMatch(person -> Objects.isNull(person.getId())));
    }

    @Test
    public void updatePersonTest() {
        final Person person = Fixture.from(Person.class).gimme(PersonTemplates.VALID_1);
        mongoTemplate.save(person);

        final String newName = "Padeiro";
        person.setName(newName);

        final List<Person> updatedPeople = personGateway.update(Collections.singletonList(person));

        assertTrue(updatedPeople.stream().anyMatch(p -> p.getName().equals(newName)));
    }

    @Test
    public void findPersonByIdTest() {
        final Person person = Fixture.from(Person.class).gimme(PersonTemplates.VALID_1);
        mongoTemplate.save(person);

        assertTrue(personGateway.findById(person.getId()).isPresent());
    }

    @Test
    public void findPersonBysIdTest() {
        final List<Person> person = Fixture.from(Person.class).gimme( 2,
                Arrays.asList(PersonTemplates.VALID_1, PersonTemplates.VALID_2));
        person.forEach(people -> mongoTemplate.save(people));

        final List<String> ids = person.stream().map(Person::getId).collect(Collectors.toList());

        assertEquals(personGateway.findListByIds(ids).size(), 2);
    }

    @Test
    public void listPeopleTest() {
        final List<Person> person = Fixture.from(Person.class).gimme( 2,
                Arrays.asList(PersonTemplates.VALID_1, PersonTemplates.VALID_2));
        person.forEach(people -> mongoTemplate.save(people));

        assertEquals(personGateway.listPeople().size(), 2);
    }

    @Test
    public void searchPeopleByNameTest() {
        final List<Person> people = Fixture.from(Person.class).gimme( 2,
                Arrays.asList(PersonTemplates.VALID_1, PersonTemplates.VALID_2));
        people.forEach(person -> mongoTemplate.save(person));

        final SearchParams searchParams = Fixture.from(SearchParams.class).gimme(SearchParamsTemplates.VALID_NAME);
        assertEquals(personGateway.search(searchParams).size(), 1);
    }

    @Test
    public void searchPeopleByLastNameTest() {
        final List<Person> people = Fixture.from(Person.class).gimme( 2,
                Arrays.asList(PersonTemplates.VALID_1, PersonTemplates.VALID_2));
        people.forEach(person -> mongoTemplate.save(person));

        final SearchParams searchParams = Fixture.from(SearchParams.class).gimme(SearchParamsTemplates.VALID_LAST_NAME);
        assertEquals(personGateway.search(searchParams).size(), 1);
    }

    @Test
    public void searchPeopleByDocumentIdTest() {
        final List<Person> people = Fixture.from(Person.class).gimme( 2,
                Arrays.asList(PersonTemplates.VALID_1, PersonTemplates.VALID_2));
        people.forEach(person -> mongoTemplate.save(person));

        final SearchParams searchParams = Fixture.from(SearchParams.class).gimme(SearchParamsTemplates.VALID_DOCUMENT_ID);
        assertEquals(personGateway.search(searchParams).size(), 1);
    }

    @Test
    public void searchPeopleByAllArgsTest() {
        final List<Person> people = Fixture.from(Person.class).gimme( 2,
                Arrays.asList(PersonTemplates.VALID_1, PersonTemplates.VALID_2));
        people.forEach(person -> mongoTemplate.save(person));

        final SearchParams searchParams = Fixture.from(SearchParams.class).gimme(SearchParamsTemplates.VALID_COMPLETE);
        assertEquals(personGateway.search(searchParams).size(), 2);
    }
}
