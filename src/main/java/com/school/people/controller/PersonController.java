package com.school.people.controller;

import com.school.people.controller.json.IdJson;
import com.school.people.controller.json.ListIdJson;
import com.school.people.controller.json.PersonJson;
import com.school.people.converter.PersonConverter;
import com.school.people.converter.PersonJsonConverter;
import com.school.people.entity.Person;
import com.school.people.entity.SearchParams;
import com.school.people.usecase.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PersonController {

    private final PersonJsonConverter personJsonConverter;
    private final PersonConverter personConverter;
    private final CreatePeople createPeople;
    private final UpdatePeople updatePeople;
    private final RemovePeople removePeople;
    private final GetPerson getPerson;
    private final ListPeople listPeople;
    private final UpdateStatusPeople updateStatusPeople;
    private final SearchPeople searchPeople;

    @PostMapping(path = "/person", produces = APPLICATION_JSON_UTF8_VALUE, consumes = APPLICATION_JSON_UTF8_VALUE)
    public PersonJson createPerson(@NotNull @Valid @RequestBody final PersonJson personJson) {
        log.info("Request to createPerson");
        final List<Person> people = personJsonConverter.convert(Collections.singletonList(personJson));

        final List<Person> savedPeople = createPeople.execute(people);

        final List<PersonJson> savedPeopleJson = personConverter.convert(savedPeople);

        return savedPeopleJson.stream().findFirst().orElse(null);

    }

    @PostMapping(path = "/people", produces = APPLICATION_JSON_UTF8_VALUE, consumes = APPLICATION_JSON_UTF8_VALUE)
    public List<PersonJson> createPeople(@NotNull @Valid @RequestBody final List<PersonJson> peopleJson) {
        log.info("Request to createPeople");
        final List<Person> people = personJsonConverter.convert(peopleJson);

        final List<Person> savedPeople = createPeople.execute(people);

        return personConverter.convert(savedPeople);
    }

    @PutMapping(path = "/person", produces = APPLICATION_JSON_UTF8_VALUE, consumes = APPLICATION_JSON_UTF8_VALUE)
    public PersonJson updatePerson(@NotNull @Valid @RequestBody final PersonJson personJson) {
        log.info("Request to updatePerson");
        final List<Person> people = personJsonConverter.convert(Collections.singletonList(personJson));

        final List<Person> savedPeople = updatePeople.execute(people);

        final List<PersonJson> savedPeopleJson = personConverter.convert(savedPeople);

        return savedPeopleJson.stream().findFirst().orElse(null);
    }

    @PutMapping(path = "/people", produces = APPLICATION_JSON_UTF8_VALUE, consumes = APPLICATION_JSON_UTF8_VALUE)
    public List<PersonJson> updatePeople(@NotNull @Valid @RequestBody final List<PersonJson> peopleJson) {
        log.info("Request to updatePeople");
        final List<Person> people = personJsonConverter.convert(peopleJson);

        final List<Person> savedPeople = updatePeople.execute(people);

        return personConverter.convert(savedPeople);
    }

    @DeleteMapping(path = "/person", consumes = APPLICATION_JSON_UTF8_VALUE)
    public void deletePerson(@NotNull @Valid @RequestBody final IdJson idJson) {
        log.info("Request to deletePerson");
        final List<String> ids = Collections.singletonList(idJson.getId());

        removePeople.execute(ids);
    }

    @DeleteMapping(path = "/people", consumes = APPLICATION_JSON_UTF8_VALUE)
    public void deletePeople(@NotNull @Valid @RequestBody final ListIdJson listIdJson) {
        log.info("Request to deletePeople");
        removePeople.execute(listIdJson.getIds());
    }

    @GetMapping(path = "/person/{personId}", produces = APPLICATION_JSON_UTF8_VALUE)
    public PersonJson getPerson(@PathVariable(name = "personId") final String personId) {
        log.info("Request to getPerson");
        final Person person = getPerson.execute(personId);

        return personConverter.convert(Collections.singletonList(person)).stream().findFirst().orElse(null);
    }

    @GetMapping(path = "/people", produces = APPLICATION_JSON_UTF8_VALUE)
    public List<PersonJson> listPeople() {
        log.info("Request to listPeople");
        final List<Person> person = listPeople.execute();

        return personConverter.convert(person);
    }

    @PatchMapping(path = "/people/activate", produces = APPLICATION_JSON_UTF8_VALUE, consumes = APPLICATION_JSON_UTF8_VALUE)
    public List<PersonJson> activatePeople(@NotNull @Valid @RequestBody final ListIdJson listIdJson) {
        log.info("Request to activatePeople");
        final List<Person> savedPeople = updateStatusPeople.execute(listIdJson.getIds(), true);

        return personConverter.convert(savedPeople);
    }

    @PatchMapping(path = "/people/deactivate",
            produces = APPLICATION_JSON_UTF8_VALUE, consumes = APPLICATION_JSON_UTF8_VALUE)
    public List<PersonJson> deactivatePeople(@NotNull @Valid @RequestBody final ListIdJson listIdJson) {
        log.info("Request to deactivatePeople");
        final List<Person> savedPeople = updateStatusPeople.execute(listIdJson.getIds(), false);

        return personConverter.convert(savedPeople);
    }

    @GetMapping(path = "/people/search", produces = APPLICATION_JSON_UTF8_VALUE)
    public List<PersonJson> searchPeople(
            @RequestParam(name = "name", defaultValue = StringUtils.EMPTY, required = false) final String name,
            @RequestParam(name = "lastName", defaultValue = StringUtils.EMPTY, required = false) final String lastName,
            @RequestParam(name = "documentId", defaultValue = StringUtils.EMPTY, required = false) final String documentId) {
        log.info("Request to searchPeople");
        final SearchParams searchParams = SearchParams.builder()
                .name(name)
                .lastName(lastName)
                .documentId(documentId)
                .build();

        final List<Person> people = searchPeople.execute(searchParams);

        return personConverter.convert(people);
    }

}
