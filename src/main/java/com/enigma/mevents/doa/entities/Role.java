package com.enigma.mevents.doa.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static com.enigma.mevents.doa.entities.Permission.*;

@RequiredArgsConstructor
public enum Role {
    USER(Collections.emptySet()),
    ADMIN(
            Set.of(
                    ADMIN_CREATE,
                    ADMIN_UPDATE,
                    ADMIN_DELETE,
                    ADMIN_READ
            )
    ),
    EVENTPLANER(
            Set.of(
                    EVENTPLANER_CREATE,
                    EVENTPLANER_READ,
                    EVENTPLANER_UPDATE,
                    EVENTPLANER_DELETE
            )
    ) ;

    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities(){
       var authorities = new ArrayList<>(getPermissions()
               .stream()
               .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
               .toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
