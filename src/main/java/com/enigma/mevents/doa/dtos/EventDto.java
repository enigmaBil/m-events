package com.enigma.mevents.doa.dtos;

import com.enigma.mevents.doa.entities.Category;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

/**
 * DTO for {@link com.enigma.mevents.doa.entities.Event}
 */

public record EventDto (
        UUID event_id,
        @NotBlank(message = "this field is mandatory")
        String name,
        @NotBlank(message = "this field is mandatory")
        String description,
        @Min(message = "This field is mandatory", value = 1)
        @Positive(message = "Can't be a negative value")
        int numberOfSeat,
        @NotBlank(message = "this field is mandatory")
        String location,
        @Pattern(message = "invalid phone number", regexp = "^(?:\\+216|00216)?[2459]\\d{7}$")
        @NotBlank(message = "this field is mandatory")
        String phone,
        @NotNull(message = "this field is mandatory")
        Category category,
        @NotNull(message = "this field is mandatory")
        @FutureOrPresent(message = "Must be actual or future date")
        LocalDate date,
        @NotNull(message = "this field is mandatory")
        LocalTime startAt,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) implements Serializable {}