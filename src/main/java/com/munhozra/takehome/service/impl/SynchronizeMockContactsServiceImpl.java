package com.munhozra.takehome.service.impl;

import com.munhozra.takehome.dto.ContactDTO;
import com.munhozra.takehome.dto.MailChimpMemberDTO;
import com.munhozra.takehome.dto.MailChimpMergeFieldsDTO;
import com.munhozra.takehome.factory.ContactsSourceClientFactory;
import com.munhozra.takehome.factory.MailAutomationClientFactory;
import com.munhozra.takehome.service.SynchronizeContactsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SynchronizeMockContactsServiceImpl implements SynchronizeContactsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SynchronizeMockContactsServiceImpl.class);
    public static final String SUBSCRIBED_STATUS = "subscribed";

    @Autowired
    ContactsSourceClientFactory contactsSourceClientFactory;
    @Autowired
    MailAutomationClientFactory mailAutomationClientFactory;

    @Override
    public ResponseEntity<ContactDTO[]> syncContacts() {
        LOGGER.debug("SynchronizeMockContactsServiceImpl.syncContacts - Get contacts");
        var contactsArray = this.getContactsFromSource();
        LOGGER.debug("SynchronizeMockContactsServiceImpl.syncContacts - Got {} contacts}", contactsArray.length);
        if (contactsArray.length < 1) return ResponseEntity.ok(new ContactDTO[0]);
        try {
            LOGGER.debug("SynchronizeMockContactsServiceImpl.syncContacts - Sync start at {}", LocalDateTime.now());
            this.synchronizeContacts(contactsArray);
            LOGGER.debug("SynchronizeMockContactsServiceImpl.syncContacts - Sync finish at {}", LocalDateTime.now());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(contactsArray);
    }

    private ContactDTO[] getContactsFromSource() {
        var mockAPIClient = this.contactsSourceClientFactory.createContactsSourceClient(MockContactsSourceClientImpl.IMPL_NAME);
        return mockAPIClient.getContacts();
    }

    private void synchronizeContacts(ContactDTO[] contacts) {
        var mailAutomationClient = this.mailAutomationClientFactory.createMailClient(MailChimpClientImpl.IMPL_NAME);
        for (ContactDTO contactDTO : contacts) {
            try {
                mailAutomationClient.addOrUpdateListMember(this.convertContactToMailChimpMember(contactDTO));
            } catch (Exception e) {
                LOGGER.error("SynchronizeMockContactsServiceImpl.synchronizeContacts - error on {}", contactDTO.getEmail(), e);
            }
        }
    }

    private MailChimpMemberDTO convertContactToMailChimpMember(ContactDTO contactDTO) {
        return MailChimpMemberDTO
                .builder()
                .email(contactDTO.getEmail())
                .statusIfNew(SUBSCRIBED_STATUS)
                .mergeFieldsDTO(MailChimpMergeFieldsDTO
                                .builder()
                                .firstName(contactDTO.getFirstName())
                                .lastName(contactDTO.getLastName())
                                .build())
                .build();
    }
}