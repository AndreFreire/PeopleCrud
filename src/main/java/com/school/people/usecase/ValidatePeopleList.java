package com.school.people.usecase;

import com.school.people.entity.Person;
import com.school.people.exception.NotFoundException;
import com.school.people.gateway.PersonGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class ValidatePeopleList {

    private static final String SEPARATOR = ", ";

    private final PersonGateway personGateway;

    public List<Person> execute(final List<String> ids) {
        log.info("Validating people");
        final List<Person> people = personGateway.findListByIds(ids);
        if (ids.size() != people.size()) {

            final List<String> savedIds = people.stream()
                    .map(Person::getId)
                    .collect(Collectors.toList());

            final String notFoundIds = ids.stream()
                    .filter(id -> !savedIds.contains(id))
                    .collect(Collectors.joining(SEPARATOR));

            throw new NotFoundException(notFoundIds);

        }
        log.info("People validated");
        return people;
    }
}
