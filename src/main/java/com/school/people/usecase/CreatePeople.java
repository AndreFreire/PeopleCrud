package com.school.people.usecase;

import com.school.people.entity.Person;
import com.school.people.gateway.PersonGateway;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class CreatePeople {

    private final PersonGateway personGateway;

    public List<Person> execute(final List<Person> person) {
        final List<Person> savedPeople = personGateway.create(person);
        log.info("People saved");
        return savedPeople;
    }
}
