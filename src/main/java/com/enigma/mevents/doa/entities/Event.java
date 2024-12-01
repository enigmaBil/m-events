package com.enigma.mevents.doa.entities;


import com.enigma.mevents.doa.utils.UUIDGenerator;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "Event")
@Table(name = "events")
public class Event {
    @Setter(AccessLevel.NONE)
    @SequenceGenerator(
            sequenceName = "event_seq",
            name = "event_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "event_seq"
    )
    @Id
    private Long id;
    @Column(
            name = "uuid",
            columnDefinition = "UUID",
            length = 64
    )
    private UUID uuid;
    @PrePersist
    public void generateUUID() {
        if (uuid == null) {
            uuid = UUIDGenerator.generateUUID();
        }
    }
    @Column(nullable = false, columnDefinition = "VARCHAR")
    private String name;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;
    @Column(name = "number_of_seeat", nullable = false, columnDefinition = "INTEGER")
    private int numberOfSeat;
    @Column(nullable = false, columnDefinition = "VARCHAR")
    private String location;
    @Column(nullable = false, columnDefinition = "VARCHAR", length = 8)
    private String phone;
    @Enumerated(EnumType.STRING)
    private Category category;
    private LocalDate date;
    @Column(name = "start_at")
    private LocalTime startAt;
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at", insertable = false)
    private LocalDateTime updatedAt;
    @ManyToMany(mappedBy = "events")
    private Set<User> users = new HashSet<>();
}
