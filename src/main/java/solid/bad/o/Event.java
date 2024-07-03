package solid.bad.o;

import java.time.LocalDateTime;

public record Event(
        int id,
        String name,
        String type,
        LocalDateTime startDateAndTime,
        LocalDateTime endDateAndTime,
        String location
) {
    // No-operation
}
