package com.munhozra.takehome.controller.impl;

import com.munhozra.takehome.config.MockAPIRestTemplate;
import com.munhozra.takehome.controller.ContactListController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class ContactListControllerImpl implements ContactListController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ContactListControllerImpl.class);
    @Autowired MockAPIRestTemplate mockAPIRestTemplate;

    @Override
    public ResponseEntity<?> syncContacts() {
        var result = mockAPIRestTemplate.getContacts();
        return ResponseEntity.ok(result);
    }
}