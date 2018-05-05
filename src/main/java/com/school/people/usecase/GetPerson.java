package com.school.people.usecase;

import com.school.people.entity.Person;
import com.school.people.exception.NotFoundException;
import com.school.people.gateway.PersonGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class GetPerson {

    private final PersonGateway personGateway;

    public Person execute(final String id) {
        final Person savedPerson = personGateway.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
        log.info("Found person");
        return savedPerson;
    }
}
