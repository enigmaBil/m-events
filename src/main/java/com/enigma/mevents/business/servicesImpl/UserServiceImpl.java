package com.enigma.mevents.business.servicesImpl;

import com.enigma.mevents.business.services.UserService;
import com.enigma.mevents.doa.entities.User;
import com.enigma.mevents.doa.repositories.UserRepository;
import com.enigma.mevents.requests.ChangePasswordRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    @Override
    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {

        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        //tester le mot de passe actuel
        if(!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())){
            throw new IllegalStateException("Mot de passe incorrect");
        }
        //tester la correspondance des nouveaux mots de passe
        if (!request.getNewPassword().equals(request.getConfirmationPassword())){
            throw new IllegalStateException("Les mots de passe ne correspondent pas");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        userRepository.save(user);
    }
}
