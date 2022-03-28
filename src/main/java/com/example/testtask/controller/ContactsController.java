package com.example.testtask.controller;

import com.example.testtask.DTO.Response;
import com.example.testtask.service.ContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class ContactsController {

    private final ContactsService contactsService;

    @Autowired
    public ContactsController(ContactsService contactsService) {
        this.contactsService = contactsService;
    }

    /**
     * Веб метод получения людей и их контактов с другими
     * @return - возвращает иерархию сонтактов каждого человека
     */
    @GetMapping("/getUser")
    public String getUsers() {
        List<Response> users = contactsService.getAllUsersToContact();
        return users.toString();
    }
}
