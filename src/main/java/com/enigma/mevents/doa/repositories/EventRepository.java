package com.enigma.mevents.doa.repositories;

import com.enigma.mevents.doa.entities.Category;
import com.enigma.mevents.doa.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findEventsByLocation(String location);
    List<Event> findEventsByDate(LocalDate date);
    List<Event> findEventsByCategory(Category category);

}