package com.enigma.mevents.business.services;

import com.enigma.mevents.doa.dtos.EventDto;
import com.enigma.mevents.doa.dtos.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface EventService {
    List<EventDto> getAllEvents();
    Optional<EventDto> getEventById(Long id);
    List<EventDto> getEventsSortByDate(String order);
    List<EventDto> getEventsSortByCategory(String order);
    List<EventDto> getEventsSortByLocation(String order);
    Page<EventDto> getAllEventsPagination(Pageable pageable);
    Page<EventDto> getEventsSortedByDatePagination(String order, Pageable pageable);
    Page<EventDto> getEventsSortedByCategoryPagination(String order, Pageable pageable);
    Page<EventDto> getEventsSortedByLocationPagination(String order, Pageable pageable);

    EventDto createEvent(EventDto eventDto);

    EventDto updateEvent(Long id, EventDto eventDto);

    void deleteEvent(Long id);


    void registerUserToEvent(Long userId, Long eventId);
    List<UserDto> getEventParticipants(Long eventId);
}
