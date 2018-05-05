package com.school.people.usecase;

import com.school.people.entity.Person;
import com.school.people.gateway.PersonGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class UpdateStatusPeople {

    private final PersonGateway personGateway;

    private final ValidatePeopleList validatePeopleList;

    public List<Person> execute(final List<String> ids, final boolean status) {
        final List<Person> people = validatePeopleList.execute(ids);
        people.forEach(person -> person.setStatus(status));
        final List<Person> updatePeople = personGateway.update(people);
        log.info("People status updated");
        return updatePeople;
    }
}
