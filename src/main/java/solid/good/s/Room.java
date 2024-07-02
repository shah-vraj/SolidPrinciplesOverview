package solid.good.s;

import solid.good.s.jitsi.ScreenSharingOptions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Room is online classroom where teacher teaches student remotely
 */
public class Room {

    private final String name;
    private final OnlineMeeting onlineMeeting;
    private final ArrayList<Integer> attendees = new ArrayList<>();
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
     * Admit a user into the room
     * @param userId ID of the user
     */
    public void admit(int userId) {
        if (attendees.contains(userId)) {
            System.out.println("User " + userId + " already in the room");
            return;
        }
        attendees.add(userId);
        onlineMeeting.admitUser(name, userId);
    }

    /**
     * Remove a user from the room
     * @param userId ID of the user
     */
    public void remove(int userId) {
        if (!attendees.contains(userId)) {
            System.out.println("User " + userId + " not in the room");
            return;
        }
        attendees.remove(Integer.valueOf(userId));
        onlineMeeting.removeUser(name, userId);
    }

    /**
     * Set the status of mic for a user in a room
     * @param userId ID fo the user
     * @param status True if mic should be on, otherwise false
     */
    public void setMicStatus(int userId, boolean status) {
        if (!attendees.contains(userId)) {
            System.out.println("User " + userId + " not in the room");
            return;
        }
        onlineMeeting.setMicStatusForUser(name, userId, status);
    }

    /**
     * Set the status of video for a user in a room
     * @param userId ID fo the user
     * @param status True if video should be on, otherwise false
     */
    public void setVideoStatus(int userId, boolean status) {
        if (!attendees.contains(userId)) {
            System.out.println("User " + userId + " not in the room");
            return;
        }
        onlineMeeting.setVideoStatusForUser(name, userId, status);
    }

    /**
     * Share the screen in the online meeting
     * @param userId ID of the user sharing the screen
     * @param screenShareOptions Options for sharing screen
     */
    public void shareScreen(int userId, ScreenSharingOptions screenShareOptions) {
        if (!attendees.contains(userId)) {
            System.out.println("User with id: " + userId + " not found");
        }
        onlineMeeting.shareScreen(name, userId, screenShareOptions);
    }

    /**
     * Add a resource in the class
     * @param resource Resource to share which can be an image/video/document
     */
    public void addResource(File resource) {
        Optional<File> file = resources.stream()
                .filter(r -> r.getName().equals(resource.getName()))
                .findFirst();
        if (file.isPresent()) {
            System.out.println("File already present with name: " + file.get().getName());
            return;
        }
        resources.add(resource);
        System.out.println("Resource \"" + resource.getName() + "\" added");
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
        removeAllUsers();
        onlineMeeting.stopRoom(name);
        onlineMeeting.deleteRoom(name);
    }

    /**
     * Removes all the users from the room
     */
    private void removeAllUsers() {
        attendees.forEach(userId -> onlineMeeting.removeUser(name, userId));
        attendees.clear();
    }
}
