package com.munhozra.takehome.service;

import com.munhozra.takehome.dto.ContactDTO;
import org.springframework.http.ResponseEntity;

public interface SynchronizeContactsService {
    public ResponseEntity<ContactDTO[]> syncContacts();
}
