package com.school.people.gateway;

import com.school.people.entity.Person;
import com.school.people.entity.SearchParams;
import com.school.people.gateway.repository.PersonRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Component
@AllArgsConstructor
public class PersonGatewayMongo implements PersonGateway {

    private final PersonRepository personRepository;

    @Override
    public List<Person> create(final List<Person> people) {
        log.info("Saving people in database");
        final Iterable<Person> savedPeople = personRepository.saveAll(people);
        return StreamSupport.stream(savedPeople.spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public List<Person> update(final List<Person> people) {
        log.info("Updating people in database");
        final Iterable<Person> savedPeople = personRepository.saveAll(people);
        return StreamSupport.stream(savedPeople.spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(final List<String> ids) {
        log.info("Deleting people in database");
        personRepository.deleteAllById(ids);
    }

    @Override
    public Optional<Person> findById(final String id) {
        log.info("Finding person by id in database");
        return personRepository.findById(id);
    }

    @Override
    public List<Person> findListByIds(final List<String> ids) {
        log.info("Finding people list by id in database");
        return personRepository.findByIdIn(ids);
    }

    @Override
    public List<Person> listPeople() {
        log.info("Listing all people in database");
        return personRepository.findAll();
    }

    @Override
    public List<Person> search(final SearchParams searchParams) {
        log.info("Seraching people in database");
        return personRepository.findAllByNameEqualsOrLastNameEqualsOrDocumentIdEquals(
                searchParams.getName(), searchParams.getLastName(), searchParams.getDocumentId()
        );
    }
}
