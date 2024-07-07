package solid.bad.l;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("UnusedReturnValue")
public class UserProfileManager {

    private final int id;
    private final List<String> hobbies = new ArrayList<>();
    private String name;
    private String email;
    private String profileImageUrl;

    public UserProfileManager(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        System.out.println("Setting name to " + name + " for " + id);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        System.out.println("Setting email to " + email + " for " + id);
    }

    public void addHobby(String hobby) {
        if (hobbies.contains(hobby)) {
            System.out.println("Hobby (" + hobby + ") already exist");
            return;
        }
        if (hobby.isBlank()) {
            System.out.println("Hobby is blank");
            return;
        }
        hobbies.add(hobby);
        System.out.println("Hobby: " + hobby + " added to hobbies for " + id);
    }

    public void removeHobby(String hobby) {
        if (!hobbies.contains(hobby)) {
            System.out.println("Hobby (" + hobby + ") does not exist");
            return;
        }
        if (hobby.isBlank()) {
            System.out.println("Hobby is blank");
            return;
        }
        hobbies.remove(hobby);
        System.out.println("Hobby: " + hobby + " removed from hobbies for " + id);
    }

    public String setProfileImage(File profileImage, ImageUploader imageUploader) {
        if (profileImage == null) {
            System.out.println("Profile image is null");
            return null;
        }
        // URL is expected in return
        profileImageUrl = imageUploader.uploadImage(profileImage);
        return profileImageUrl;
    }

    public File getProfileImage() {
        if (profileImageUrl == null) {
            System.out.println("Profile image URL is null");
            return null;
        }
        return downloadImage(profileImageUrl);
    }

    public void setCoverImage(File coverImage, ImageUploader imageUploader) {
        if (coverImage == null) {
            System.out.println("Cover image is null");
            return;
        }
        // URL is not expected in return
        imageUploader.uploadImage(coverImage);
    }

    private File downloadImage(String imageUrl) {
        System.out.println("Downloading image from " + imageUrl);
        // Assume image downloaded
        System.out.println("Download complete");
        return new File(imageUrl);
    }
}
