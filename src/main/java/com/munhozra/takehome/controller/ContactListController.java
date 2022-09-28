package com.munhozra.takehome.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/contacts")
public interface ContactListController {

//    @GetMapping(value = "/sync", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(value = "/sync")
    @ResponseStatus(HttpStatus.OK) //Could use webflux, could return ACCEPTED
    public ResponseEntity<?> syncContacts();
}
