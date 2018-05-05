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
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ValidatePeopleListTest {

    @MockBean
    private PersonGateway personGateway;

    @Autowired
    private ValidatePeopleList validatePeopleList;

    @Before
    public void setup() {
        FixtureFactoryLoader.loadTemplates("com.school.people.templates");
    }

    @Test
    public void validatePeopleTest() {
        final List<String> ids = Collections.singletonList("1");

        final List<Person> people = Fixture.from(Person.class).gimme(1,
                PersonTemplates.VALID_1);

        given(personGateway.findListByIds(ids)).willReturn(people);

        final List<Person> validadPeople = validatePeopleList.execute(ids);

        assertNotNull(validadPeople);
        assertEquals(people, validadPeople);

        then(personGateway).should().findListByIds(ids);
    }

    @Test(expected = NotFoundException.class)
    public void invalidatePeopleTest() {
        final List<String> ids = Collections.singletonList("1");

        given(personGateway.findListByIds(ids)).willReturn(new ArrayList<>());

        validatePeopleList.execute(ids);

    }

}
