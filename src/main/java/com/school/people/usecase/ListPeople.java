package com.school.people.usecase;

import com.school.people.entity.Person;
import com.school.people.exception.NotFoundException;
import com.school.people.gateway.PersonGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ListPeople {

    private final PersonGateway personGateway;

    public List<Person> execute() {
        final List<Person> savedPeople = personGateway.listPeople();

        if (savedPeople.isEmpty()) {
            throw new NotFoundException();
        }
        log.info("People Listed");
        return savedPeople;
    }
}
