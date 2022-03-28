package com.example.testtask.service;

import com.example.testtask.DTO.Response;
import java.util.List;

public interface ContactsService {
    /**
     * Метод для получения иерархии контактов людей из БД
     * @return - лист контактов людей
     */
    List<Response> getAllUsersToContact();
}
