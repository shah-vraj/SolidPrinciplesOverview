package solid.bad.s;

import solid.bad.s.jitsi.ScreenSharingOptions;

import java.io.File;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Room is online classroom where teacher teaches student remotely
 */
public class Room {

    record OnlineTest(String name, LocalDateTime dateTime) { }

    private final String name;
    private final OnlineMeeting onlineMeeting;

    private final Map<Integer, String> teachers = new HashMap<>();
    private final Map<Integer, String> students = new HashMap<>();
    private final Map<Integer, List<OnlineTest>> tests = new HashMap<>();
    private final List<File> resources = new ArrayList<>();

    public Room(String name, OnlineMeeting onlineMeeting) {
        this.name = name;
        this.onlineMeeting = onlineMeeting;
    }

    /**
     * Start online meeting for the room
     */
    public void start() {
        onlineMeeting.createRoom(name);
        onlineMeeting.startRoom(name);
    }

    /**
     * Add a teacher to the room
     * @param id ID of the teacher
     * @param name Name of the teacher
     */
    public void addTeacher(Integer id, String name) {
        if (teachers.containsKey(id)) {
            System.out.println("Teacher already exists!");
            return;
        }
        teachers.put(id, name);
    }

    /**
     * Add a student to the room
     * @param id ID of the student
     * @param name Name of the student
     */
    public void addStudent(Integer id, String name) {
        if (students.containsKey(id)) {
            System.out.println("Student already exists!");
            return;
        }
        students.put(id, name);
    }

    /**
     * Sets the status of mic for the teacher
     * @param teacherId ID of the teacher
     * @param status True if mic should be on, otherwise false
     */
    public void setMicStatusForTeacher(int teacherId, boolean status) {
        if (!teachers.containsKey(teacherId)) {
            System.out.println("Teacher " + teacherId + " not found");
            return;
        }
        onlineMeeting.setMicStatusForUser(name, teacherId, status);
    }

    /**
     * Sets the status of mic for the student
     * @param studentId ID of the student
     * @param status True if mic should be on, otherwise false
     */
    public void setMicStatusForStudent(int studentId, boolean status) {
        if (!students.containsKey(studentId)) {
            System.out.println("Student " + studentId + " not found");
            return;
        }
        onlineMeeting.setMicStatusForUser(name, studentId, status);
    }

    /**
     * Sets the status of video for the student
     * @param studentId ID of the student
     * @param status True if video should be on, otherwise false
     */
    public void setVideoStatusForStudent(int studentId, boolean status) {
        if (!students.containsKey(studentId)) {
            System.out.println("Student " + studentId + " not found");
            return;
        }
        onlineMeeting.setVideoStatusForUser(name, studentId, status);
    }

    /**
     * Sets the status of video for the teacher
     * @param teacherId ID of the teacher
     * @param status True if video should be on, otherwise false
     */
    public void setVideoStatusForTeacher(int teacherId, boolean status) {
        if (!teachers.containsKey(teacherId)) {
            System.out.println("Teacher " + teacherId + " not found");
            return;
        }
        onlineMeeting.setVideoStatusForUser(name, teacherId, status);
    }

    /**
     * Schedule a test in a room
     * @param teacherId ID of the teacher
     * @param testName Name of the test
     * @param localDateTime Date and time of the test
     */
    public void scheduleTest(int teacherId, String testName, LocalDateTime localDateTime) {
        if (!teachers.containsKey(teacherId)) {
            System.out.println("Teacher " + teacherId + " not found");
            return;
        }

        List<OnlineTest> currentTests = tests.containsKey(teacherId) ? tests.get(teacherId) : List.of();
        ArrayList<OnlineTest> newOnlineTests = new ArrayList<>(currentTests);
        newOnlineTests.add(new OnlineTest(testName, localDateTime));
        tests.put(teacherId, newOnlineTests);
        System.out.println("Test \"" + testName + "\" scheduled by teacher: " + teachers.get(teacherId));
    }

    /**
     * Share the screen in the online meeting
     * @param userId ID of the user sharing the screen
     * @param screenShareOptions Options for sharing screen
     */
    public void shareScreen(int userId, ScreenSharingOptions screenShareOptions) {
        if (!students.containsKey(userId) && !teachers.containsKey(userId)) {
            System.out.println("User with id: " + userId + " not found");
        }
        onlineMeeting.shareScreen(name, userId, screenShareOptions);
    }

    /**
     * Cancel a test from the room
     * @param teacherId ID of the teacher
     * @param testName Name of the test
     */
    public void cancelTest(int teacherId, String testName) {
        if (!teachers.containsKey(teacherId)) {
            System.out.println("Teacher " + teacherId + " not found");
            return;
        }

        List<OnlineTest> currentTests = tests.containsKey(teacherId) ? tests.get(teacherId) : List.of();
        Optional<OnlineTest> onlineTest = currentTests.stream()
                .filter(c -> c.name.equals(testName))
                .findFirst();
        if (onlineTest.isEmpty()) {
            System.out.println("Test \"" + testName + "\" not found");
            return;
        }

        currentTests.remove(onlineTest.get());
        tests.put(teacherId, currentTests);
        System.out.println("Test \"" + testName + "\" cancelled");
    }

    /**
     * Share resources in the class
     * @param teacherId ID of the teacher
     * @param resource Resource to share which can be an image/video/document
     */
    public void shareResources(int teacherId, File resource) {
        if (!teachers.containsKey(teacherId)) {
            System.out.println("Teacher " + teacherId + " not found");
            return;
        }
        resources.add(resource);
        System.out.println("Resource " + resource.getName() + " added by teacher: " + teachers.get(teacherId));
    }

    /**
     * Provides the resources shared in the room
     * @return List of File's
     */
    public List<File> getResources() {
        return resources;
    }

    /**
     * End online meeting for the room
     */
    public void end() {
        onlineMeeting.stopRoom(name);
        onlineMeeting.deleteRoom(name);
        removeAllUsers();
    }

    /**
     * Remove a teacher from the room
     * @param id ID of the teacher
     */
    public void removeTeacher(Integer id) {
        if (!teachers.containsKey(id)) {
            System.out.println("Teacher does not exists!");
            return;
        }
        teachers.remove(id);
    }

    /**
     * Remove a student from the room
     * @param id ID of the student
     */
    public void removeStudent(Integer id) {
        if (!students.containsKey(id)) {
            System.out.println("Student does not exists!");
            return;
        }
        students.remove(id);
    }

    private void removeAllUsers() {
        HashSet<Integer> teachers = new HashSet<>(this.teachers.keySet());
        teachers.forEach(this::removeTeacher);
        HashSet<Integer> students = new HashSet<>(this.teachers.keySet());
        students.forEach(this::removeStudent);
    }
}
