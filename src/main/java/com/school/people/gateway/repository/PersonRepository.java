package com.school.people.gateway.repository;

import com.school.people.entity.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, String> {

    void deleteAllById(final List<String> ids);

    List<Person> findByIdIn(List<String> ids);

    @Override
    List<Person> findAll();

    List<Person> findAllByNameEqualsOrLastNameEqualsOrDocumentIdEquals(final String name,
                                                                       final String lastName,
                                                                       final String documentId);
}
