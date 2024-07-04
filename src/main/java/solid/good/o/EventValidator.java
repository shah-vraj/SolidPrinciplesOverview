package solid.good.o;

import java.util.List;
import java.util.Objects;

public class EventValidator {

    private static EventValidator instance;

    private EventValidator() { }

    /**
     * Provides singleton instance for the EventValidator
     * @return EventValidator instance
     */
    public static EventValidator getInstance() {
        if (instance == null)
            instance = new EventValidator();
        return instance;
    }

    /**
     * Check if the event start and end time are valid or not
     * @param event Event object
     * @return True if event start and end time are valid, otherwise false
     */
    public boolean validateEventStartAndEndTime(Event event) {
        if (event.endDateAndTime().isBefore(event.startDateAndTime())) {
            System.out.println("Event end time should not be before start time");
            return false;
        }
        return true;
    }

    /**
     * Check if current event is conflicting with other event by location and/or time
     * @param event Event object
     * @param allEvents All events to check conflict with
     * @return True if passed event doesn't conflict with other, otherwise false
     */
    public boolean validateEventLocationAndTimeConflict(Event event, List<Event> allEvents) {
        Event conflictingLocationEvent = allEvents.stream()
                .filter(e -> Objects.equals(e.location(), event.location()))
                .findFirst()
                .orElse(null);

        // Check if the start and end time is conflicting or not if location conflicts
        if (conflictingLocationEvent != null) {
            // If start time (conflictingLocationEvent) is after end time (event) then
            // end time is also after end because of above validation
            boolean isStartTimeAfterEnd = conflictingLocationEvent.startDateAndTime().isAfter(event.endDateAndTime());
            // If end time (conflictingLocationEvent) is before start time (event) then
            // start time is also before start because of above validation
            boolean isEndTimeBeforeStart = conflictingLocationEvent.endDateAndTime().isBefore(event.startDateAndTime());
            if (!isStartTimeAfterEnd && !isEndTimeBeforeStart) {
                System.out.println("Event is conflicting with other event");
                return false;
            }
        }
        return true;
    }

    /**
     * Check if the event id is registered
     * @param eventId ID of the event
     * @param allEvents All events to check with
     * @return True if eventId is present, otherwise false
     */
    public boolean isEventIdNotAdded(int eventId, List<Event> allEvents) {
        boolean isEventAdded = allEvents.stream().anyMatch(event -> event.id() == eventId);
        if (!isEventAdded) {
            System.out.println("Event with id " + eventId + " not found");
            return true;
        }
        return false;
    }
}
