package com.munhozra.takehome.service.impl;

import com.munhozra.takehome.service.MailAutomationClient;
import com.munhozra.takehome.dto.MailChimpMemberDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("MailChimpV3")
public class MailChimpClientImpl implements MailAutomationClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(MailChimpClientImpl.class);
    public static final String IMPL_NAME = "MailChimpV3";

    @Value("${mailChimp.baseAPI}")
    String mailChimpBaseAPIEndpoint;
    @Value("${mailChimp.listId}")
    String mailChimpListId;
    @Value("${mailChimp.username}")
    String mailChimpUsername;
    @Value("${mailChimp.password}")
    char[] mailChimpPassword;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void addOrUpdateListMember(MailChimpMemberDTO memberDTO) {
        var apiURI = this.getAPIURL(memberDTO.getEmail());
        var headers = this.getHeaders();
        HttpEntity<MailChimpMemberDTO> entity = new HttpEntity<>(memberDTO, headers);
        LOGGER.debug("MailChimpClientImpl.addOrUpdateList - Update {} at apiURI={}", memberDTO.getEmail(), apiURI);
        ResponseEntity<String> response = restTemplate.exchange(apiURI, HttpMethod.PUT, entity, String.class);
        LOGGER.debug("MailChimpClientImpl.addOrUpdateList - Response for {}; Status={}; Reason={}; Body={}", memberDTO.getEmail(), response.getStatusCodeValue(), response.getStatusCode().getReasonPhrase(), response.getBody());
    }

    private String getAPIURL(String email) {
        return this.mailChimpBaseAPIEndpoint + "/lists/" + this.mailChimpListId + "/members/" + email;
    }

    private HttpHeaders getHeaders() {
        var headers = new HttpHeaders();
        headers.setBasicAuth(this.mailChimpUsername, String.valueOf(this.mailChimpPassword));
        return headers;
    }
}