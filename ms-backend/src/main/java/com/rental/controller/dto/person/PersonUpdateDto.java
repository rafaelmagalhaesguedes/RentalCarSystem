package com.rental.controller.dto.person;

import com.rental.entity.Person;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * The type Person update dto.
 */
public record PersonUpdateDto(
        @NotBlank(message = "Name cannot be blank")
        @Size(max = 255, message = "Name must be less than or equal to 255 characters")
        String fullName,

        @NotBlank(message = "Username cannot be blank")
        @Size(max = 255, message = "Username must be less than or equal to 255 characters")
        String username,

        @Email(message = "Invalid email")
        @NotBlank(message = "Email cannot be blank")
        String email
) {

    /**
     * To entity person.
     *
     * @return the person
     */
    public Person toEntity() {
        Person person = new Person();
        person.setFullName(fullName);
        person.setUsername(username);
        person.setEmail(email);
        return person;
    }
}

