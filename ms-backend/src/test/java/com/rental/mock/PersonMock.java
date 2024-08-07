package com.rental.mock;

import com.rental.entity.Person;
import com.rental.security.Role;

import java.util.UUID;

/**
 * The type Person mock.
 */
public class PersonMock {

    public static Person PERSON_01 = new Person(
            UUID.randomUUID(),
            "Thais Guedes",
            "thais",
            "thais@email.com",
            "password",
            Role.USER
    );

    public static Person PERSON_02 = new Person(
            UUID.randomUUID(),
            "Ana Guedes",
            "ana",
            "ana@email.com",
            "password",
            Role.USER
    );

    public static Person PERSON_CREATION = new Person(
            "Rafael Guedes",
            "rafa",
            "rafa@email.com",
            "password",
            Role.MANAGER
    );

    public static Person PERSON_UPDATE = new Person(
            "André Guedes",
            "andre",
            "andre@email.com"
    );
}
