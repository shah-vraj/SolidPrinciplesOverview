package solid.good.o;

import solid.good.o.event_type.EventType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EventManager {

    private final List<Event> events = new ArrayList<>();
    private final EventValidator eventValidator = EventValidator.getInstance();

    // Region: Public methods
    /**
     * Add an event
     * @param eventType Type of the event
     * @param eventName Name of the event
     * @param start Start date and time of the event
     * @param end End date and time of the event
     * @param location Location of the event
     */
    public void addEvent(EventType eventType, String eventName, LocalDateTime start, LocalDateTime end, String location) {
        Event event = new Event(events.size() + 1, eventName, eventType, start, end, location);
        // Check if event start time and end time is valid or note
        if (!eventValidator.validateEventStartAndEndTime(event)) {
            return;
        }
        // Check if event is conflicting with other event by same location and time
        if (!eventValidator.validateEventLocationAndTimeConflict(event, events)) {
            return;
        }
        events.add(event);
        System.out.println("Event: \"" + event.name() + "\" added");
    }

    /**
     * Remove an event
     * @param eventId ID of the event
     */
    public void removeEvent(int eventId) {
        // Check if event ID is valid or not
        if (eventValidator.isEventIdNotAdded(eventId, events)) {
            return;
        }
        Optional<Event> event = events.stream().filter(e -> e.id() == eventId).findFirst();
        if (event.isEmpty()) {
            System.out.println("Event not found with id: " + eventId);
            return;
        }
        removeEvent(event.get());
    }

    /**
     * Starts an registered event with provided ID
     * @param eventId ID of the event
     */
    public void startEvent(int eventId) {
        // Check if event ID is valid or not
        if (eventValidator.isEventIdNotAdded(eventId, events)) {
            return;
        }
        Optional<Event> event = events.stream().filter(e -> e.id() == eventId).findFirst();
        if (event.isEmpty()) {
            System.out.println("Event not found with id: " + eventId);
            return;
        }
        event.get().type().setupEventEnvironment();
        removeEvent(eventId);
    }

    /**
     * Displays all the registered events
     */
    public void displayAllEvents() {
        System.out.println("All events:");
        if (events.isEmpty()) {
            System.out.println("No events found");
            return;
        }
        for (Event event : events) {
            System.out.println("Event id: " + event.id());
            System.out.println("name: " + event.name());
            System.out.println("EventType: " + event.type().getClass().getSimpleName());
            System.out.println("Start date and time: " + event.startDateAndTime());
            System.out.println("End date and time: " + event.endDateAndTime());
            System.out.println("Location: " + event.location());
            System.out.println();
        }
    }

    /**
     * Provides the event ID from the name of the event
     * @param name Name of the event
     * @return Integer representing ID of the event
     */
    public int getEventId(String name) {
        for (Event event : events) {
            if (event.name().equals(name)) {
                return event.id();
            }
        }
        return -1;
    }
    // End region

    // Region: Private methods
    /**
     * Remove an event through Event object
     * @param event Event object to remove
     */
    private void removeEvent(Event event) {
        if (!events.contains(event)) {
            System.out.println("Event does not exist: " + event.name());
            return;
        }
        events.remove(event);
        System.out.println("Event removed/completed: \"" + event.name() + "\"");
    }
    // End region
}
