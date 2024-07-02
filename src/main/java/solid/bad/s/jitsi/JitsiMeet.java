package solid.bad.s.jitsi;

import solid.bad.s.OnlineMeeting;

public class JitsiMeet implements OnlineMeeting {

    private final String serverUrl;

    public JitsiMeet(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    @Override
    public void createRoom(String roomName) {
        System.out.println("Setting up jitsi meet with url " + serverUrl + " for room " + roomName);
    }

    @Override
    public void startRoom(String roomName) {
        System.out.println("Starting room " + roomName);
    }

    @Override
    public void setVideoStatusForUser(String roomName, int userId, boolean isOn) {
        System.out.println("Setting video status to " + (isOn ? "ON" : "OFF") + " for user " + userId + " for room " + roomName);
    }

    @Override
    public void setMicStatusForUser(String roomName, int userId, boolean isOn) {
        System.out.println("Setting mic status to " + (isOn ? "ON" : "OFF") + " for user " + userId + " for room " + roomName);
    }

    @Override
    public void shareScreen(String roomName, int userId, ScreenSharingOptions screenSharingOptions) {
        System.out.println("Sharing screen " + screenSharingOptions + " for user " + userId + " for room " + roomName);
    }

    @Override
    public void stopRoom(String roomName) {
        System.out.println("Stopping room " + roomName);
    }

    @Override
    public void deleteRoom(String roomName) {
        System.out.println("Deleting room " + roomName);
    }
}
