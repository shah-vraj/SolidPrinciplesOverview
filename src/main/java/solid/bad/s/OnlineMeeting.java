package solid.bad.s;

import solid.bad.s.jitsi.ScreenSharingOptions;

public interface OnlineMeeting {

    /**
     * Create a room for online meeting
     * @param roomName Name of the room
     */
    void createRoom(String roomName);

    /**
     * Start online meeting for a room
     * @param roomName Name of the room
     */
    void startRoom(String roomName);

    /**
     * Set the status of video for a user in a room
     * @param roomName Name of the room
     * @param userId ID fo the user
     * @param isOn True if video should be on, otherwise false
     */
    void setVideoStatusForUser(String roomName, int userId, boolean isOn);

    /**
     * Set the status of mic for a user in a room
     * @param roomName Name of the room
     * @param userId ID fo the user
     * @param isOn True if mic should be on, otherwise false
     */
    void setMicStatusForUser(String roomName, int userId, boolean isOn);

    /**
     * Share the screen inside a room
     * @param roomName Name of the room
     * @param userId ID of the user
     * @param screenSharingOptions Screen sharing options
     */
    void shareScreen(String roomName, int userId, ScreenSharingOptions screenSharingOptions);

    /**
     * Stop online meeting for a room
     * @param roomName Name of the room
     */
    void stopRoom(String roomName);

    /**
     * Delete a room
     * @param roomName Name of the room
     */
    void deleteRoom(String roomName);
}
