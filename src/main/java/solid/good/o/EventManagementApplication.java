package solid.good.o;

import solid.good.o.event_type.GamingNight;
import solid.good.o.event_type.MondayNightMeal;
import solid.good.o.event_type.SnowballFight;

import java.time.LocalDateTime;
import java.time.Month;

/**
 * Sample main java class to showcase dummy usage of all classes.
 * Note: Real world implementation/usage for these classes vary according to requirement,
 * this is just an example to make sure code is compiled successfully and ready to run.
 */
public class EventManagementApplication {

    public static void main(String[] args) {
        EventManager eventManager = new EventManager();

        // Add event
        eventManager.addEvent(new GamingNight(),
                "Dalhousie games night",
                LocalDateTime.of(2024, Month.JULY, 7, 22, 0),
                LocalDateTime.of(2024, Month.JULY, 8, 4, 0),
                "Goldburg department");
        eventManager.addEvent(new MondayNightMeal(),
                "Dalhousie monday night meal",
                LocalDateTime.of(2024, Month.JULY, 8, 19, 0),
                LocalDateTime.of(2024, Month.JULY, 8, 21, 0),
                "International center");
        eventManager.addEvent(new SnowballFight(),
                "Dalhousie snowball fight",
                LocalDateTime.of(2024, Month.JULY, 12, 7, 0),
                LocalDateTime.of(2024, Month.JULY, 12, 12, 0),
                "Public garden");
        System.out.println();

        // Display events
        eventManager.displayAllEvents();

        // Cancel event
        int dalhousieMondayNightMealEventId = eventManager.getEventId("Dalhousie monday night meal");
        eventManager.removeEvent(dalhousieMondayNightMealEventId);
        System.out.println();

        // Display events
        eventManager.displayAllEvents();

        // Start event
        eventManager.startEvent(1);
        eventManager.startEvent(3);

        // Display events
        eventManager.displayAllEvents();
    }
}
