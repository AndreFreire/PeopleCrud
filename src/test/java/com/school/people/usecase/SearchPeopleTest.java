package com.school.people.usecase;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.school.people.entity.Person;
import com.school.people.entity.SearchParams;
import com.school.people.exception.NotFoundException;
import com.school.people.gateway.PersonGateway;
import com.school.people.templates.PersonTemplates;
import com.school.people.templates.SearchParamsTemplates;
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
public class SearchPeopleTest {

    @Autowired
    private SearchPeople searchPeople;

    @MockBean
    private PersonGateway personGateway;

    @Before
    public void setup() {
        FixtureFactoryLoader.loadTemplates("com.school.people.templates");
    }

    @Test
    public void completeSearchPeopleTest() {

        final SearchParams searchParams = Fixture.from(SearchParams.class).gimme(SearchParamsTemplates.VALID_COMPLETE);
        final List<Person> people = Fixture.from(Person.class).gimme(1, PersonTemplates.VALID_1);

        given(personGateway.search(searchParams)).willReturn(people);

        final List<Person> savedPeople = searchPeople.execute(searchParams);

        assertNotNull(savedPeople);
        assertEquals(people, savedPeople);

        then(personGateway).should().search(searchParams);
    }

    @Test
    public void nameSearchPeopleTest() {

        final SearchParams searchParams = Fixture.from(SearchParams.class).gimme(SearchParamsTemplates.VALID_NAME);
        final List<Person> people = Fixture.from(Person.class).gimme(1, PersonTemplates.VALID_1);

        given(personGateway.search(searchParams)).willReturn(people);

        final List<Person> savedPeople = searchPeople.execute(searchParams);

        assertNotNull(savedPeople);

        then(personGateway).should().search(searchParams);
    }

    @Test(expected = NotFoundException.class)
    public void notFoundSearchPeopleTest() {

        final SearchParams searchParams = Fixture.from(SearchParams.class).gimme(SearchParamsTemplates.VALID_COMPLETE);

        given(personGateway.search(searchParams)).willReturn(new ArrayList<>());

        searchPeople.execute(searchParams);
    }

}
