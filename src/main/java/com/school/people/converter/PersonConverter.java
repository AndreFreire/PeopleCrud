package com.school.people.converter;

import com.school.people.controller.json.AddressJson;
import com.school.people.controller.json.PersonJson;
import com.school.people.controller.json.PhoneJson;
import com.school.people.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Component
public class PersonConverter implements Converter<List<Person>, List<PersonJson>> {

    @Override
    public List<PersonJson> convert(final List<Person> people) {
        final List<PersonJson> peopleJson = new ArrayList<>();
        people.forEach(person -> {
            log.trace(String.format("Converting Person to PersonJson - documentId: %s", person.getDocumentId()));
            final List<PhoneJson> phonesJson = new ArrayList<>();
            person.getPhones().forEach( phone ->
                    phonesJson.add(PhoneJson.builder()
                            .number(phone.getNumber())
                            .type(phone.getType())
                            .build())
            );

            final PersonJson personJson = PersonJson.builder()
                    .id(person.getId())
                    .name(person.getName())
                    .lastName(person.getLastName())
                    .documentId(person.getDocumentId())
                    .birthday(person.getBirthday())
                    .address(AddressJson.builder()
                            .street(person.getAddress().getStreet())
                            .number(person.getAddress().getNumber())
                            .zipcode(person.getAddress().getZipcode())
                            .neighborhood(person.getAddress().getNeighborhood())
                            .city(person.getAddress().getCity())
                            .state(person.getAddress().getState())
                            .build())
                    .phones(phonesJson)
                    .emails(person.getEmails())
                    .status(person.isStatus())
                    .build();

            peopleJson.add(personJson);
        });
        return peopleJson;
    }
}
