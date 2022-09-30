package com.munhozra.takehome.controller.impl;

import com.munhozra.takehome.controller.ContactListController;
import com.munhozra.takehome.dto.ContactDTO;
import com.munhozra.takehome.service.SynchronizeContactsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;


@Controller
public class ContactListControllerImpl implements ContactListController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ContactListControllerImpl.class);
    @Autowired
    SynchronizeContactsService synchronizeContactsService;
    @Override
    public ResponseEntity<ContactDTO[]> syncContacts() {
        return this.synchronizeContactsService.syncContacts();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleError(HttpServletRequest req, Exception e) {
        LOGGER.error("ContactListControllerImpl.handleError - Request: {}", req.getRequestURL(), e);
        return ResponseEntity.internalServerError().body("Something unexpected happened");
    }
}