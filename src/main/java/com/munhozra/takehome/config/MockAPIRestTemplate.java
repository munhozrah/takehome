package com.munhozra.takehome.config;

import com.munhozra.takehome.dto.MockAPIContactDTO;
import com.munhozra.takehome.dto.MockAPIContactDTOList;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class MockAPIRestTemplate {
    @Value("${mockapi.endpoint}") String mockAPIEndpoint;
    public MockAPIContactDTO[] getContacts() {
        RestTemplate restTemplate = new RestTemplate();
//        var result = restTemplate.getForObject(mockAPIEndpoint, MockAPIContactDTOList.class);
        var result = restTemplate.getForObject(mockAPIEndpoint, MockAPIContactDTO[].class);
        return result;
    }
}