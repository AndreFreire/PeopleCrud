package com.school.people.usecase;

import com.school.people.entity.Person;
import com.school.people.entity.SearchParams;
import com.school.people.exception.NotFoundException;
import com.school.people.gateway.PersonGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class SearchPeople {

    private final PersonGateway personGateway;

    public List<Person> execute(final SearchParams searchParams) {
        final List<Person> savedPeople = personGateway.search(searchParams);

        if (savedPeople.isEmpty()) {
            throw new NotFoundException();
        }

        log.info("People searched");
        return savedPeople;

    }
}
