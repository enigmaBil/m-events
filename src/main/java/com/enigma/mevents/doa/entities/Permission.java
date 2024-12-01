package com.enigma.mevents.doa.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_CREATE("admin:create"),
    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_DELETE("admin:delete"),
    EVENTPLANER_CREATE("eventPlaner:create"),
    EVENTPLANER_READ("eventPlaner:read"),
    EVENTPLANER_UPDATE("eventPlaner:update"),
    EVENTPLANER_DELETE("eventPlaner:delete"),
    ;

    @Getter
    private final String permission;
}
