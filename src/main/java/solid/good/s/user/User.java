package solid.good.s.user;

import solid.good.s.Room;
import solid.good.s.jitsi.ScreenSharingOptions;

public abstract class User {

    protected int userId;

    public User(int userId) {
        this.userId = userId;
    }

    /**
     * Join a room
     * @param room Room object
     */
    public void joinRoom(Room room) {
        room.admit(userId);
    }

    /**
     * Leave a room
     * @param room Room object
     */
    public void leaveRoom(Room room) {
        room.remove(userId);
    }

    /**
     * Set mic status foe the provided user
     * @param room Room object
     * @param status True if mic should be on, otherwise false
     */
    public void setMicStatus(Room room, boolean status) {
        room.setMicStatus(userId, status);
    }

    /**
     * Set video status foe the provided user
     * @param room Room object
     * @param status True if video should be on, otherwise false
     */
    public void setVideoStatus(Room room, boolean status) {
        room.setVideoStatus(userId, status);
    }

    /**
     * Share the screen in a room
     * @param room Room object
     * @param screenSharingOptions Screen sharing options
     */
    public void shareScreen(Room room, ScreenSharingOptions screenSharingOptions) {
        room.shareScreen(userId, screenSharingOptions);
    }
}
