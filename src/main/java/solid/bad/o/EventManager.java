package solid.bad.o;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

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
    public void addEvent(String eventType, String eventName, LocalDateTime start, LocalDateTime end, String location) {
        // Check if event type is valid or not
        if (!eventValidator.validateEventType(eventType)) {
            return;
        }
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
        String eventType = getEventType(eventId);
        // Check if event type is valid or not
        if (eventType == null) {
            System.out.println("Cannot get event type for event with id " + eventId + " not found");
            return;
        }
        switch (eventType) {
            case "GamesNight" -> startGamesNightEvent();
            case "MondayNightMeal" -> startMondayNightMealEvent();
            case "MovieNight" -> startMovieNightEvent();
            case "SnowballFight" -> startSnowballFightEvent();
            case "CanadaDayFireworks" -> startCanadaDayFireworksEvent();
            default -> System.out.println("Invalid event type to start");
        }
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
            System.out.println("EventType: " + event.type());
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

    /**
     * Provides the type of the event from its ID
     * @param eventId ID of the event
     * @return String representing type of the event
     */
    private String getEventType(int eventId) {
        AtomicReference<String> eventType = new AtomicReference<>();
        events.stream()
                .filter(event -> event.id() == eventId)
                .findFirst()
                .ifPresent(event -> eventType.set(event.type()));
        return eventType.get();
    }

    /**
     * Start event of type - GamesNight
     */
    private void startGamesNightEvent() {
        System.out.println("Setting up environment for the event GamingEvent");
    }

    /**
     * Start event of type - MondayNightMeal
     */
    private void startMondayNightMealEvent() {
        System.out.println("Setting up environment for the event MondayNightMeal");
    }

    /**
     * Start event of type - MovieNight
     */
    private void startMovieNightEvent() {
        System.out.println("Setting up environment for the event MovieNight");
    }

    /**
     * Start event of type - SnowballFight
     */
    private void startSnowballFightEvent() {
        System.out.println("Setting up environment for the event SnowballFight");
    }

    /**
     * Start event of type - CanadaDayFireworks
     */
    private void startCanadaDayFireworksEvent() {
        System.out.println("Setting up environment for the event CanadaDayFireworks");
    }
    // End region
}
