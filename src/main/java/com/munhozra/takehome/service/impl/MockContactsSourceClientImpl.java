package com.munhozra.takehome.service.impl;

import com.munhozra.takehome.service.ContactsSourceClient;
import com.munhozra.takehome.dto.ContactDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("MockAPI")
public class MockContactsSourceClientImpl implements ContactsSourceClient {
    @Value("${mockapi.endpoint}") String mockAPIEndpoint;
    private static final Logger LOGGER = LoggerFactory.getLogger(MockContactsSourceClientImpl.class);
    public static final String IMPL_NAME = "MockAPI";

    @Autowired
    private RestTemplate restTemplate;
    @Override
    public ContactDTO[] getContacts() {
        LOGGER.debug("MockContactsSourceClientImpl.getContacts.mockAPIEndpoint={}", mockAPIEndpoint);
        return restTemplate.getForObject(mockAPIEndpoint, ContactDTO[].class);
    }
}