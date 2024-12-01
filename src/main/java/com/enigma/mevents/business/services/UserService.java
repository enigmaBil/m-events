package com.enigma.mevents.business.services;

import com.enigma.mevents.requests.ChangePasswordRequest;

import java.security.Principal;

public interface UserService {
    void changePassword(ChangePasswordRequest request, Principal connectedUser);
}
