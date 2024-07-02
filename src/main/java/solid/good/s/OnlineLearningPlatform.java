package solid.good.s;

import solid.good.s.jitsi.JitsiMeet;
import solid.good.s.jitsi.ScreenSharingOptions;
import solid.good.s.user.Student;
import solid.good.s.user.Teacher;

import java.io.File;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

/**
 * Sample main java class to showcase dummy usage of all classes.
 * Note: Real world implementation/usage for these classes vary according to requirement,
 * this is just an example to make sure code is successfully compiled and ready to run.
 */
public class OnlineLearningPlatform {
    private static final Teacher teacher = new Teacher(1, "ShunTian Wang");
    private static final List<Student> students = List.of(
            new Student(2, "Vraj Shah"),
            new Student(3, "Shrey Monka"),
            new Student(4, "Shrey Patel"),
            new Student(5, "Rushil Borad")
    );

    public static void main(String[] args) {
        OnlineMeeting onlineMeeting = new JitsiMeet("https://server_url");
        Room room = new Room("CSCI5308: Advance Software Development Concepts", onlineMeeting);
        room.start();
        System.out.println();

        // Adding teacher
        teacher.joinRoom(room);

        // Adding student
        students.forEach(student -> student.joinRoom(room));
        System.out.println();

        // Mic status
        teacher.setMicStatus(room, true);
        students.forEach(student -> student.setMicStatus(room, false));
        System.out.println();

        // Video status
        teacher.setVideoStatus(room, true);
        students.forEach(student -> student.setVideoStatus(room, true));
        System.out.println();

        // Add a test
        teacher.scheduleTest("Mid term exams", LocalDateTime.of(2024, Month.JUNE, 22, 10, 5));
        teacher.scheduleTest("Surprise Test", LocalDateTime.now());
        System.out.println();

        // Share screen
        teacher.shareScreen(room, ScreenSharingOptions.SPECIFIC_WINDOW);
        System.out.println();

        // Cancel test
        teacher.cancelTest("Surprise Test");
        System.out.println();

        // All tests
        System.out.println("All tests provided by the teacher \"" + teacher.getName() + "\":");
        teacher.getAllTests().forEach(test -> System.out.println("Test: " + test.name() + " at " + test.dateTime()));
        System.out.println();

        // Sharing and retrieving resources
        teacher.shareResource(room, new File("SOLID.pdf"));
        teacher.shareResource(room, new File("CI-CD.pdf"));
        System.out.println("All resources shared in the room:");
        students.getFirst().getResources(room).forEach(resource -> System.out.println("File: " + resource.getName()));
        System.out.println();

        // Leave room
        System.out.println("Assume 1h20m has passed...");
        teacher.leaveRoom(room);
        students.forEach(student -> student.leaveRoom(room));

        // End
        room.end();
    }
}
