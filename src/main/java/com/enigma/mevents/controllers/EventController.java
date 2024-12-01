package com.enigma.mevents.controllers;


import com.enigma.mevents.business.services.EventService;
import com.enigma.mevents.doa.dtos.EventDto;
import com.enigma.mevents.doa.dtos.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
@PreAuthorize("hasAnyRole('EVENTPLANER')")
@Tag(name = "Events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @Operation(
            description = "Get endpoint for event",
            summary = "Get all events",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403"
                    )
            }
    )
    @GetMapping
    @PreAuthorize("hasAnyAuthority('eventPlaner:read', 'admin:read')")
    public ResponseEntity<List<EventDto>> getAllEvents() {
        List<EventDto> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDto> getEventById(@PathVariable Long id) {
        return eventService.getEventById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/sort/date")
    public ResponseEntity<List<EventDto>> getEventsSortedByDate(@RequestParam(defaultValue = "asc") String order) {
        List<EventDto> events = eventService.getEventsSortByDate(order);
        return ResponseEntity.ok(events);
    }

    @GetMapping("/sort/category")
    public ResponseEntity<List<EventDto>> getEventsSortedByCategory(@RequestParam(defaultValue = "asc") String order) {
        List<EventDto> events = eventService.getEventsSortByCategory(order);
        return ResponseEntity.ok(events);
    }

    @GetMapping("/sort/location")
    public ResponseEntity<List<EventDto>> getEventsSortedByLocation(@RequestParam(defaultValue = "asc") String order) {
        List<EventDto> events = eventService.getEventsSortByLocation(order);
        return ResponseEntity.ok(events);
    }

    @GetMapping("/pagination/sort/date")
    public ResponseEntity<Page<EventDto>> getEventsSortedByDatePagination(
            @RequestParam(defaultValue = "asc") String order,
            Pageable pageable) {
        Page<EventDto> events = eventService.getEventsSortedByDatePagination(order, pageable);
        return ResponseEntity.ok(events);
    }

    @GetMapping("/pagination/sort/category")
    public ResponseEntity<Page<EventDto>> getEventsSortedByCategoryPagination(
            @RequestParam(defaultValue = "asc") String order,
            Pageable pageable) {
        Page<EventDto> events = eventService.getEventsSortedByCategoryPagination(order, pageable);
        return ResponseEntity.ok(events);
    }

    @GetMapping("/pagination/sort/location")
    public ResponseEntity<Page<EventDto>> getEventsSortedByLocationPagination(
            @RequestParam(defaultValue = "asc") String order,
            Pageable pageable) {
        Page<EventDto> events = eventService.getEventsSortedByLocationPagination(order, pageable);
        return ResponseEntity.ok(events);
    }

    @PostMapping
    public ResponseEntity<EventDto> createEvent(@RequestBody EventDto eventDto) {
        EventDto createdEvent = eventService.createEvent(eventDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEvent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventDto> updateEvent(@PathVariable Long id, @RequestBody EventDto eventDto) {
        EventDto updatedEvent = eventService.updateEvent(id, eventDto);
        return ResponseEntity.ok(updatedEvent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{eventId}/register/{userId}")
    public ResponseEntity<String> registerUserToEvent(
            @PathVariable Long userId,
            @PathVariable Long eventId
    ) {
        eventService.registerUserToEvent(userId, eventId);
        return ResponseEntity.ok("User successfully registered to the event");
    }

    @GetMapping("/{eventId}/participants")
    public ResponseEntity<List<UserDto>> getEventParticipants(@PathVariable Long eventId) {
        return ResponseEntity.ok(eventService.getEventParticipants(eventId));
    }
}
