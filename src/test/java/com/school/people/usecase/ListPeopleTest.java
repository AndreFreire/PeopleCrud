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

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ListPeopleTest {

    @Autowired
    private ListPeople listPeople;

    @MockBean
    private PersonGateway personGateway;

    @Before
    public void setup() {
        FixtureFactoryLoader.loadTemplates("com.school.people.templates");
    }

    @Test
    public void listPeopleTest() {
        final List<Person> people = Fixture.from(Person.class).gimme(1, PersonTemplates.VALID_1);

        given(personGateway.listPeople()).willReturn(people);

        final List<Person> savedPeople = listPeople.execute();

        assertNotNull(savedPeople);
        assertEquals(people, savedPeople);

        then(personGateway).should().listPeople();
    }

    @Test(expected = NotFoundException.class)
    public void notFoundPeopleTest() {
        given(personGateway.listPeople()).willReturn(new ArrayList<>());

        listPeople.execute();
    }
}
