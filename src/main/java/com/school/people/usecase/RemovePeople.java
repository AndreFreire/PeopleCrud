package com.school.people.usecase;

import com.school.people.gateway.PersonGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class RemovePeople {

    private final PersonGateway personGateway;
    private final ValidatePeopleList validatePeopleList;

    public void execute(final List<String> ids) {
        validatePeopleList.execute(ids);
        personGateway.delete(ids);
        log.info("People deleted");
    }
}
