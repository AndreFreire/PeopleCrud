package com.school.people.controller;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.people.controller.json.IdJson;
import com.school.people.controller.json.ListIdJson;
import com.school.people.controller.json.PersonJson;
import com.school.people.entity.Person;
import com.school.people.entity.SearchParams;
import com.school.people.exception.NotFoundException;
import com.school.people.templates.*;
import com.school.people.usecase.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
public class PersonControllerTest {

    @MockBean
    private CreatePeople createPeople;

    @MockBean
    private UpdatePeople updatePeople;

    @MockBean
    private RemovePeople removePeople;

    @MockBean
    private GetPerson getPerson;

    @MockBean
    private ListPeople listPeople;

    @MockBean
    private UpdateStatusPeople updateStatusPeople;

    @MockBean
    private SearchPeople searchPeople;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setup() {
        FixtureFactoryLoader.loadTemplates("com.school.people.templates");
    }

    @Test
    public void createPersonTest() throws Exception {
        final List<Person> people = Fixture.from(Person.class).gimme(1, PersonTemplates.VALID_1);
        when(createPeople.execute(any())).thenReturn(people);

        final PersonJson personJson = Fixture.from(PersonJson.class).gimme(PersonJsonTemplates.VALID_WITH_NO_ID);

        mockMvc.perform(
                post("/person")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(personJson)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(people.get(0).getId())));
    }

    @Test
    public void createPeopleTest() throws Exception {
        final List<Person> people = Fixture.from(Person.class).gimme(1, PersonTemplates.VALID_1);
        when(createPeople.execute(any())).thenReturn(people);

        final List<PersonJson> personJson = Fixture.from(PersonJson.class)
                .gimme(1, PersonJsonTemplates.VALID_WITH_NO_ID);

        mockMvc.perform(
                post("/people")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(personJson)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", equalTo(people.get(0).getId())));
    }

    @Test
    public void updatePersonTest() throws Exception {
        final List<Person> people = Fixture.from(Person.class).gimme(1, PersonTemplates.VALID_1);
        when(updatePeople.execute(any())).thenReturn(people);

        final PersonJson personJson = Fixture.from(PersonJson.class)
                .gimme(PersonJsonTemplates.VALID);

        mockMvc.perform(
                put("/person")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(personJson)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(personJson.getId())));
    }

    @Test
    public void updatePeopleTest() throws Exception {
        final List<Person> people = Fixture.from(Person.class).gimme(1, PersonTemplates.VALID_1);
        when(updatePeople.execute(any())).thenReturn(people);

        final List<PersonJson> personJson = Fixture.from(PersonJson.class)
                .gimme(1, PersonJsonTemplates.VALID);

        mockMvc.perform(
                put("/people")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(personJson)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", equalTo(people.get(0).getId())));
    }

    @Test
    public void notFoundUpdatePersonTest() throws Exception {
        when(updatePeople.execute(any())).thenThrow(new NotFoundException("1"));

        final PersonJson personJson = Fixture.from(PersonJson.class)
                .gimme(PersonJsonTemplates.VALID);

        mockMvc.perform(
                put("/person")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(personJson)))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", equalTo("Not found entity: 1")));
    }

    @Test
    public void notFoundUpdatePeopleTest() throws Exception {
        when(updatePeople.execute(any())).thenThrow(new NotFoundException("1"));

        final List<PersonJson> personJson = Fixture.from(PersonJson.class)
                .gimme(1, PersonJsonTemplates.VALID);

        mockMvc.perform(
                put("/people")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(personJson)))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", equalTo("Not found entity: 1")));
    }

    @Test
    public void deletePersonTest() throws Exception {
        final IdJson idJson = Fixture.from(IdJson.class).gimme(IdJsonTemplates.VALID);

        mockMvc.perform(
                delete("/person")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(idJson)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void deletePeopleTest() throws Exception {
        final ListIdJson listIdJson = Fixture.from(ListIdJson.class).gimme(ListIdJsonTemplates.VALID);

        mockMvc.perform(
                delete("/person")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(listIdJson)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void notFoundDeletePersonTest() throws Exception {
        final IdJson idJson = Fixture.from(IdJson.class).gimme(IdJsonTemplates.VALID);

        doThrow(new NotFoundException("1")).when(removePeople).execute(any());

        mockMvc.perform(
                delete("/person")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(idJson)))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", equalTo("Not found entity: 1")));
    }

    @Test
    public void notFoundDeletePeopleTest() throws Exception {
        final ListIdJson listIdJson = Fixture.from(ListIdJson.class).gimme(ListIdJsonTemplates.VALID);

        doThrow(new NotFoundException("1")).when(removePeople).execute(any());

        mockMvc.perform(
                delete("/person")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(listIdJson)))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", equalTo("Not found entity: 1")));
    }

    @Test
    public void getPersonTest() throws Exception {
        final Person person = Fixture.from(Person.class).gimme(PersonTemplates.VALID_1);
        when(getPerson.execute(any())).thenReturn(person);

        mockMvc.perform(
                get(String.format("/person/%s", person.getId())))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(person.getId())));
    }

    @Test
    public void listPeopleTest() throws Exception {
        final List<Person> person = Fixture.from(Person.class).gimme(1, PersonTemplates.VALID_1);
        when(listPeople.execute()).thenReturn(person);

        mockMvc.perform(
                get("/people"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", equalTo(person.get(0).getId())));
    }

    @Test
    public void notFoundGetPersonTest() throws Exception {
        when(getPerson.execute(any())).thenThrow(new NotFoundException("1"));

        mockMvc.perform(
                get("/person/1"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", equalTo("Not found entity: 1")));
    }

    @Test
    public void notFoundListPeopleTest() throws Exception {
        when(listPeople.execute()).thenThrow(new NotFoundException());

        mockMvc.perform(
                get("/people"))
                .andDo(print()).andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", equalTo("Entity not found")));
    }


    @Test
    public void activatePeopleTest() throws Exception {
        final IdJson idJson = Fixture.from(IdJson.class).gimme(IdJsonTemplates.VALID);

        final List<Person> person = Fixture.from(Person.class).gimme(1, PersonTemplates.VALID_1);
        when(updateStatusPeople.execute(any(), anyBoolean())).thenReturn(person);

        mockMvc.perform(
                patch("/people/activate")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(idJson)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", equalTo(person.get(0).getId())));
    }

    @Test
    public void notFoundActivatePeopleTest() throws Exception {
        final IdJson idJson = Fixture.from(IdJson.class).gimme(IdJsonTemplates.VALID);

        when(updateStatusPeople.execute(any(), anyBoolean())).thenThrow(new NotFoundException(idJson.getId()));

        mockMvc.perform(
                patch("/people/activate")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(idJson)))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", equalTo("Not found entity: 1")));
    }

    @Test
    public void deactivatePeopleTest() throws Exception {
        final IdJson idJson = Fixture.from(IdJson.class).gimme(IdJsonTemplates.VALID);

        final List<Person> people = Fixture.from(Person.class).gimme(1, PersonTemplates.VALID_1);
        when(updateStatusPeople.execute(any(), anyBoolean())).thenReturn(people);

        mockMvc.perform(
                patch("/people/deactivate")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(idJson)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", equalTo(people.get(0).getId())));
    }

    @Test
    public void notFoundDeactivatePeopleTest() throws Exception {
        final IdJson idJson = Fixture.from(IdJson.class).gimme(IdJsonTemplates.VALID);

        when(updateStatusPeople.execute(any(), anyBoolean())).thenThrow(new NotFoundException(idJson.getId()));

        mockMvc.perform(
                patch("/people/deactivate")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(idJson)))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", equalTo("Not found entity: 1")));
    }

    @Test
    public void searchPeopleTest() throws Exception {
        final SearchParams searchParams = Fixture.from(SearchParams.class).gimme(SearchParamsTemplates.VALID_COMPLETE);

        final List<Person> person = Fixture.from(Person.class).gimme(1, PersonTemplates.VALID_1);
        when(searchPeople.execute(any())).thenReturn(person);

        mockMvc.perform(
                get("/people/search")
                        .param("name", searchParams.getName())
                        .param("lastName", searchParams.getLastName())
                        .param("documentId", searchParams.getDocumentId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", equalTo(person.get(0).getId())));
    }

    @Test
    public void notFoundSearchPeopleTest() throws Exception {
        final SearchParams searchParams = Fixture.from(SearchParams.class).gimme(SearchParamsTemplates.VALID_COMPLETE);

        when(searchPeople.execute(any())).thenThrow(new NotFoundException());

        mockMvc.perform(
                get("/people/search")
                        .param("name", searchParams.getName())
                        .param("lastName", searchParams.getLastName())
                        .param("documentId", searchParams.getDocumentId()))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", equalTo("Entity not found")));
    }
}
