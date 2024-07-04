package solid.good.o;

import solid.good.o.event_type.EventType;

import java.time.LocalDateTime;

public record Event(
        int id,
        String name,
        EventType type,
        LocalDateTime startDateAndTime,
        LocalDateTime endDateAndTime,
        String location
) {
    // No-operation
}
