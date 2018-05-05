package com.school.people.usecase;


import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.school.people.entity.Person;
import com.school.people.exception.NotFoundException;
import com.school.people.gateway.PersonGateway;
import com.school.people.templates.PersonTemplates;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class UpdatePeopleTest {

    @Autowired
    private UpdatePeople updatePeople;

    @MockBean
    private PersonGateway personGateway;

    @MockBean
    private ValidatePeopleList validatePeopleList;

    @Before
    public void setup() {
        FixtureFactoryLoader.loadTemplates("com.school.people.templates");
    }

    @Test
    public void updatePeopleTest() {
        final List<String> ids = Collections.singletonList("1");

        final List<Person> people = Fixture.from(Person.class).gimme(1,
                PersonTemplates.VALID_1);

        given(validatePeopleList.execute(ids)).willReturn(people);
        given(personGateway.update(people)).willReturn(people);

        final List<Person> updatedPeople = updatePeople.execute(people);

        assertNotNull(updatedPeople);
        assertEquals(people, updatedPeople);

        then(personGateway).should().update(people);
    }

    @Test(expected = NotFoundException.class)
    public void notFountUpdatePeopleTest() {
        final List<String> ids = Arrays.asList("1", "2");

        final List<Person> people = Fixture.from(Person.class).gimme(2,
                PersonTemplates.VALID_1, PersonTemplates.VALID_2);

        given(validatePeopleList.execute(ids)).willThrow(NotFoundException.class);

        updatePeople.execute(people);
    }
}
