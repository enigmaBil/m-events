package com.enigma.mevents.controllers.auth;


import com.enigma.mevents.doa.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterRequest {

    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private Role role;
}
