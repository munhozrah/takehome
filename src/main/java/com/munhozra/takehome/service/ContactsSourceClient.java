package com.munhozra.takehome.service;

import com.munhozra.takehome.dto.ContactDTO;

public interface ContactsSourceClient {
    public ContactDTO[] getContacts();
}
