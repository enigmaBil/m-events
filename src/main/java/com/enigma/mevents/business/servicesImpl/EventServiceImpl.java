package com.enigma.mevents.business.servicesImpl;

import com.enigma.mevents.exceptions.ResourceNotException;
import com.enigma.mevents.business.services.EventService;
import com.enigma.mevents.doa.dtos.EventDto;
import com.enigma.mevents.doa.dtos.UserDto;
import com.enigma.mevents.doa.entities.Event;
import com.enigma.mevents.doa.mappers.EventMapper;
import com.enigma.mevents.doa.mappers.UserMapper;
import com.enigma.mevents.doa.repositories.EventRepository;
import com.enigma.mevents.doa.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    @Override
    public List<EventDto> getAllEvents() {
        List<Event> eventList = eventRepository.findAll();
        return eventList.stream().map(EventMapper.INSTANCE::mapToEventDto).collect(Collectors.toList());
    }

    @Override
    public Optional<EventDto> getEventById(Long id) {
        if (id == null) return Optional.empty();

        var event = eventRepository.findById(id).orElseThrow(
                () -> new ResourceNotException("No Event found with id: "+id)
        );

        return Optional.ofNullable(EventMapper.INSTANCE.mapToEventDto(event));
    }

    @Override
    public List<EventDto> getEventsSortByDate(String order) {
        Sort.Direction direction = Sort.Direction.ASC;

        if ("desc".equalsIgnoreCase(order)) direction = Sort.Direction.DESC;

        var events = eventRepository.findAll(Sort.by(direction, "date"));

        return events.stream().map(EventMapper.INSTANCE::mapToEventDto).collect(Collectors.toList());
    }

    @Override
    public List<EventDto> getEventsSortByCategory(String order) {
        Sort.Direction direction = Sort.Direction.ASC;

        if ("desc".equalsIgnoreCase(order)) direction = Sort.Direction.DESC;

        var events = eventRepository.findAll(Sort.by(direction, "category"));

        return events.stream().map(EventMapper.INSTANCE::mapToEventDto).collect(Collectors.toList());
    }

    @Override
    public List<EventDto> getEventsSortByLocation(String order) {
        Sort.Direction direction = Sort.Direction.ASC;

        if ("desc".equalsIgnoreCase(order)) direction = Sort.Direction.DESC;

        var events = eventRepository.findAll(Sort.by(direction, "location"));

        return events.stream().map(EventMapper.INSTANCE::mapToEventDto).collect(Collectors.toList());
    }

    @Override
    public Page<EventDto> getAllEventsPagination(Pageable pageable) {
        if (pageable == null) throw new IllegalArgumentException("La pagination ne doit pas être nulle");

        var events = eventRepository.findAll(pageable);

        return events.map(EventMapper.INSTANCE::mapToEventDto);
    }

    @Override
    public Page<EventDto> getEventsSortedByDatePagination(String order, Pageable pageable) {
        if (pageable == null) throw new IllegalArgumentException("La pagination ne doit pas être nulle");

        Sort.Direction direction = Sort.Direction.ASC;

        if ("desc".equalsIgnoreCase(order)) direction = Sort.Direction.DESC;

        var sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(direction, "date"));

        var events = eventRepository.findAll(sortedPageable);

        return events.map(EventMapper.INSTANCE::mapToEventDto);
    }

    @Override
    public Page<EventDto> getEventsSortedByCategoryPagination(String order, Pageable pageable) {
        if (pageable == null) throw new IllegalArgumentException("La pagination ne doit pas être nulle");

        Sort.Direction direction = Sort.Direction.ASC;

        if ("desc".equalsIgnoreCase(order)) direction = Sort.Direction.DESC;

        var sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(direction, "category"));

        var events = eventRepository.findAll(sortedPageable);

        return events.map(EventMapper.INSTANCE::mapToEventDto);
    }

    @Override
    public Page<EventDto> getEventsSortedByLocationPagination(String order, Pageable pageable) {
        if (pageable == null) throw new IllegalArgumentException("La pagination ne doit pas être nulle");

        Sort.Direction direction = Sort.Direction.ASC;

        if ("desc".equalsIgnoreCase(order)) direction = Sort.Direction.DESC;

        var sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(direction, "location"));

        var events = eventRepository.findAll(sortedPageable);

        return events.map(EventMapper.INSTANCE::mapToEventDto);
    }

    @Override
    public EventDto createEvent(EventDto eventDto) {
        if (eventDto == null) throw new IllegalArgumentException("L'evenement ne doit pas être nulle");

        var event = EventMapper.INSTANCE.mapToEventEntity(eventDto);

        event.setCreatedAt(LocalDateTime.now());

        var savedEvent = eventRepository.save(event);

        return EventMapper.INSTANCE.mapToEventDto(savedEvent);
    }

    @Override
    public EventDto updateEvent(Long id, EventDto eventDto) {
        if (eventDto == null) throw new IllegalArgumentException("L'evenement ne doit pas être nulle");

        var event = eventRepository.findById(id).orElseThrow(
                () -> new ResourceNotException("No Event found with id: "+id)
        );

        event.setName(eventDto.name());
        event.setDescription(eventDto.description());
        event.setNumberOfSeat(eventDto.numberOfSeat());
        event.setCategory(eventDto.category());
        event.setPhone(eventDto.phone());
        event.setLocation(eventDto.location());
        event.setDate(eventDto.date());
        event.setStartAt(eventDto.startAt());
        event.setUpdatedAt(LocalDateTime.now());

        var updatedEvent = eventRepository.save(event);

        return EventMapper.INSTANCE.mapToEventDto(updatedEvent);
    }

    @Override
    public void deleteEvent(Long id) {
        if (id == null) return;

        var event = eventRepository.findById(id).orElseThrow(
                () -> new ResourceNotException("No Event found with id: "+id)
        );

        eventRepository.delete(event);
    }

    @Override
    public void registerUserToEvent(Long userId, Long eventId) {
        var user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotException("No user found with id: "+userId)
        );

        var event = eventRepository.findById(eventId).orElseThrow(
                () -> new ResourceNotException("No event found with id: "+eventId)
        );

        if (user.getEvents().contains(event)) throw new IllegalStateException(user.getName().toUpperCase() + " is already registered for this event");

        if (event.getUsers().size() >= event.getNumberOfSeat()) throw new IllegalStateException("No seat available for this event");

        user.getEvents().add(event);
        event.getUsers().add(user);

        userRepository.save(user);
    }

    @Override
    public List<UserDto> getEventParticipants(Long eventId) {
        var event = eventRepository.findById(eventId).orElseThrow(
                () -> new ResourceNotException("No event found with id: "+eventId)
        );
        return event.getUsers().stream().map(UserMapper.INSTANCE::mapTotoUserDto).collect(Collectors.toList());
    }
}
