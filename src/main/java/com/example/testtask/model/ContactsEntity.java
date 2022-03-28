package com.example.testtask.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Objects;

/**
 * Сущность таблицы "contacts"
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "contacts", schema = "ttp", catalog = "postgres")
public class ContactsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "first_user")
    private int first_user;

    @Column(name = "second_user")
    private int second_user;

    public int getId() {
        return id;
    }

    public int getFirst_user() {
        return first_user;
    }

    public void setFirst_user(int first_user) {
        this.first_user = first_user;
    }

    public int getSecond_user() {
        return second_user;
    }

    public void setSecond_user(int second_user) {
        this.second_user = second_user;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactsEntity contacts = (ContactsEntity) o;
        return id == contacts.id && first_user == contacts.first_user && second_user == contacts.second_user;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, first_user, second_user);
    }
}
