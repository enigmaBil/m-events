package com.enigma.mevents.doa.dtos;

import com.enigma.mevents.doa.entities.Role;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.enigma.mevents.doa.entities.User}
 */

public record UserDto(
        String name,
        String email,
        String phoneNumber
        ) implements Serializable { }