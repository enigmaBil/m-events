package com.enigma.mevents.doa.mappers;

import com.enigma.mevents.doa.dtos.EventDto;
import com.enigma.mevents.doa.entities.Event;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EventMapper {
    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    @Mapping(source = "event_id", target = "uuid")
    Event mapToEventEntity(EventDto eventDto);

    @Mapping(source = "uuid", target = "event_id")
    EventDto mapToEventDto(Event event);

}