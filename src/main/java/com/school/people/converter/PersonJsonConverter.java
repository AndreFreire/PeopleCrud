package com.school.people.converter;

import com.school.people.controller.json.PersonJson;
import com.school.people.entity.Address;
import com.school.people.entity.Person;
import com.school.people.entity.Phone;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class PersonJsonConverter implements Converter<List<PersonJson>, List<Person>> {

    @Override
    public List<Person> convert(final List<PersonJson> peopleJson) {
        final List<Person> people = new ArrayList<>();
        peopleJson.forEach(personJson -> {
            log.trace(String.format("Converting PersonJson to Person - documentId: %s", personJson.getDocumentId()));

            final List<Phone> phones = new ArrayList<>();
            personJson.getPhones().forEach( phoneJson ->
                    phones.add(Phone.builder()
                            .number(phoneJson.getNumber())
                            .type(phoneJson.getType())
                            .build())
            );

            final Person person = Person.builder()
                    .id(personJson.getId())
                    .name(personJson.getName())
                    .lastName(personJson.getLastName())
                    .documentId(personJson.getDocumentId())
                    .birthday(personJson.getBirthday())
                    .address(Address.builder()
                            .street(personJson.getAddress().getStreet())
                            .number(personJson.getAddress().getNumber())
                            .zipcode(personJson.getAddress().getZipcode())
                            .neighborhood(personJson.getAddress().getNeighborhood())
                            .city(personJson.getAddress().getCity())
                            .state(personJson.getAddress().getState())
                            .build())
                    .phones(phones)
                    .emails(personJson.getEmails())
                    .status(personJson.isStatus())
                    .build();

            people.add(person);
        });
        return people;
    }
}
