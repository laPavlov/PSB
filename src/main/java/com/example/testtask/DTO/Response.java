package com.example.testtask.DTO;

import java.util.*;

/**
 * (DTO) Класс форма для ответа на запрос
 */
public class Response implements Comparable<Response> {

    private String firstName;
    private String lastName;
    private List<Response> response = new ArrayList<>();

    public Response() {
    }

    public Response(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public List<Response> getResponse() {
        return response;
    }

    public void setResponse(List<Response> response) {
        this.response = response;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Response response = (Response) o;
        return Objects.equals(firstName, response.firstName) && Objects.equals(lastName, response.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    @Override
    public int compareTo(Response other) {
        return getFirstName().compareTo(other.getFirstName());
    }


    @Override
    public String toString() {
        return "\"" + firstName + ' ' + lastName + "\"" + ':' +
                response;
    }
}
