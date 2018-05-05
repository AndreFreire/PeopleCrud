package com.school.people.gateway;

import com.school.people.entity.Person;
import com.school.people.entity.SearchParams;

import java.util.List;
import java.util.Optional;

public interface PersonGateway {

    List<Person> create(final List<Person> people);

    List<Person> update(final List<Person> people);

    void delete(final List<String> ids);

    Optional<Person> findById(final String id);

    List<Person> findListByIds(final List<String> ids);

    List<Person> listPeople();

    List<Person> search(SearchParams searchParams);
}
