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

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class GetPersonTest {

    @Autowired
    private GetPerson getPerson;

    @MockBean
    private PersonGateway personGateway;

    @Before
    public void setup() {
        FixtureFactoryLoader.loadTemplates("com.school.people.templates");
    }

    @Test
    public void getPersonTest() {
        final Person people = Fixture.from(Person.class).gimme(PersonTemplates.VALID_1);

        given(personGateway.findById(people.getId())).willReturn(Optional.of(people));

        final Person savedPerson = getPerson.execute(people.getId());

        assertNotNull(savedPerson);
        assertEquals(people, savedPerson);

        then(personGateway).should().findById(people.getId());
    }

    @Test(expected = NotFoundException.class)
    public void notFoundGetPersonTest() {
        final String id = "xxx";

        given(personGateway.findById(id)).willReturn(Optional.empty());

        getPerson.execute(id);

        then(personGateway).should().findById(id);
    }
}
