package com.example.testtask.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

/**
 * Сущность таблицы "users"
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users", schema = "ttp", catalog = "postgres")
public class UsersEntity{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "first_name")
    private String firstName;
    @Basic
    @Column(name = "last_name")
    private String lastName;
    @Basic
    @Column(name = "health_state")
    private String healthState;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getHealthState() {
        return healthState;
    }

    public void setHealthState(String healthState) {
        this.healthState = healthState;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "first_user")
    List<ContactsEntity> firstUsers;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersEntity that = (UsersEntity) o;
        return id == that.id && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(healthState, that.healthState) && Objects.equals(firstUsers, that.firstUsers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, healthState, firstUsers);
    }

    @Override
    public String toString() {
        return "\"" + firstName + ' ' + lastName + "\"" + ':'
                ;
    }
}
