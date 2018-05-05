package com.school.people.usecase;

import com.school.people.entity.Person;
import com.school.people.gateway.PersonGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class UpdatePeople {

    private final PersonGateway personGateway;
    private final ValidatePeopleList validatePeopleList;

    public List<Person> execute(final List<Person> people) {
        final List<String> ids = people.stream()
                .map(Person::getId)
                .collect(Collectors.toList());

        validatePeopleList.execute(ids);

        log.info("People updated");
        return personGateway.update(people);
    }
}
