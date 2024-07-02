package solid.good.s.user;

import solid.good.s.Room;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Teacher extends User {

    public record OnlineTest(String name, LocalDateTime dateTime) { }

    private final String name;
    private final ArrayList<OnlineTest> tests = new ArrayList<>();

    public Teacher(int id, String name) {
        super(id);
        this.name = name;
    }

    /**
     * Provides the name of the teacher
     * @return String representing name of the teacher
     */
    public String getName() {
        return name;
    }

    /**
     * Schedules a test with the provided data
     * @param testName Name of the test
     * @param dateTime Date and time of the test
     */
    public void scheduleTest(String testName, LocalDateTime dateTime) {
        OnlineTest onlineTest = new OnlineTest(testName, dateTime);
        if (tests.contains(onlineTest)) {
            System.out.println("Test already scheduled in the room");
            return;
        }
        tests.add(onlineTest);
        System.out.println("Test \"" + onlineTest.name + "\" scheduled at: " + onlineTest.dateTime + " by \"" + name + "\"");
    }

    /**
     * Cancel a test provided bye the teacher
     * @param testName Name of the test to cancel
     */
    public void cancelTest(String testName) {
        OnlineTest testToCancel = tests.stream()
                .filter(test -> Objects.equals(test.name(), testName))
                .findFirst()
                .orElse(null);
        if (testToCancel == null) {
            System.out.println("Test not found with name " + testName);
            return;
        }
        tests.remove(testToCancel);
        System.out.println("Test \"" + testToCancel.name + "\" cancelled");
    }

    /**
     * Provides all the test provided by the teacher
     * @return List of all OnlineTests
     */
    public List<OnlineTest> getAllTests() {
        return tests;
    }

    /**
     * Share a resource in a room
     * @param room Room object
     * @param resource Resource to share
     */
    public void shareResource(Room room, File resource) {
        room.addResource(resource);
    }
}
