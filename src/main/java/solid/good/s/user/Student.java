package solid.good.s.user;

import solid.good.s.Room;

import java.io.File;
import java.util.List;

public class Student extends User {

    public Student(int userId, String name) {
        super(userId, name);
    }

    /**
     * Provides all the resources that are shared in the room
     * @param room Room object
     * @return List of all resources shared
     */
    public List<File> getResources(Room room) {
        return room.getResources();
    }
}
