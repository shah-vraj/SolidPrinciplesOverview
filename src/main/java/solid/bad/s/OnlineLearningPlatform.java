package solid.bad.s;

import solid.bad.s.jitsi.JitsiMeet;
import solid.bad.s.jitsi.ScreenSharingOptions;

import java.io.File;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Map;

/**
 * Sample main java class to showcase dummy usage of all classes.
 * Note: Real world implementation/usage for these classes vary according to requirement,
 * this is just an example to make sure code is compiled successfully and ready to run.
 */
public class OnlineLearningPlatform {

    private static final Map<Integer, String> teachers = Map.ofEntries(
            Map.entry(1, "ShunTian Wang")
    );
    private static final Map<Integer, String> students = Map.ofEntries(
            Map.entry(2, "Vraj Shah"),
            Map.entry(3, "Shrey Monka"),
            Map.entry(4, "Shrey Patel"),
            Map.entry(5, "Rushil Borad")
    );

    public static void main(String[] args) {
        OnlineMeeting onlineMeeting = new JitsiMeet("https://server_url");
        Room room = new Room("CSCI5308: Advance Software Development Concepts", onlineMeeting);
        room.start();
        System.out.println();

        // Adding teacher
        teachers.forEach(room::addTeacher);

        // Adding student
        students.forEach(room::addStudent);

        // Mic status
        teachers.keySet().forEach(id -> room.setMicStatusForTeacher(id, true));
        students.keySet().forEach(id -> room.setMicStatusForStudent(id, false));
        System.out.println();

        // Video status
        teachers.keySet().forEach(id -> room.setVideoStatusForTeacher(id, true));
        students.keySet().forEach(id -> room.setVideoStatusForStudent(id, true));
        System.out.println();

        // Add a test
        room.scheduleTest(1, "Mid term exams", LocalDateTime.of(2024, Month.JUNE, 22, 10, 5));
        room.scheduleTest(1, "Surprise Test", LocalDateTime.now());
        System.out.println();

        // Share screen
        room.shareScreen(1, ScreenSharingOptions.SPECIFIC_WINDOW);
        System.out.println();

        // Cancel test
        room.cancelTest(1, "Surprise Test");
        System.out.println();

        // Sharing and retrieving resources
        room.addResource(1, new File("SOLID.pdf"));
        room.addResource(1, new File("CI-CD.pdf"));
        System.out.println("All resources shared in the room:");
        room.getResources().forEach(resource -> System.out.println("File: " + resource.getName()));
        System.out.println();

        // End
        System.out.println("Assume 1h20m has passed...");
        room.end();
    }
}
