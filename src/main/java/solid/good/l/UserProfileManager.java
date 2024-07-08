package solid.good.l;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UserProfileManager {

    private final int id;
    private final List<String> hobbies = new ArrayList<>();
    private String name;
    private String email;
    private String profileImageUrl;

    public UserProfileManager(int id) {
        this.id = id;
    }

    /**
     * Provides name of the user
     * @return String representing name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the user
     * @param name Name of the user
     */
    public void setName(String name) {
        this.name = name;
        System.out.println("Setting name to " + name + " for " + id);
    }

    /**
     * Provides email of the user
     * @return String representing email of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user
     * @param email Email of the user
     */
    public void setEmail(String email) {
        this.email = email;
        System.out.println("Setting email to " + email + " for " + id);
    }

    /**
     * Adds a hobby
     * @param hobby String representing hobby
     */
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

    /**
     * Remove a hobby
     * @param hobby String representing hobby
     */
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

    /**
     * Set profile image via provided url uploading image uploader
     * @param profileImage Profile image to set
     * @param imageUploader UrlProvidingImageUploader instance
     * @return String representing URL of the uploaded image
     */
    public String setProfileImage(File profileImage, UrlProvidingImageUploader imageUploader) {
        if (profileImage == null) {
            System.out.println("Profile image is null");
            return null;
        }
        // URL is expected in return
        profileImageUrl = imageUploader.uploadImage(profileImage);
        return profileImageUrl;
    }

    /**
     * Set profile image via provided simple image uploader
     * @param profileImage Profile image to set
     * @param imageUploader SimpleImageUploader instance
     */
    public void setProfileImage(File profileImage, SimpleImageUploader imageUploader) {
        if (profileImage == null) {
            System.out.println("Profile image is null");
            return;
        }
        // URL is NOT expected in return
        imageUploader.uploadImage(profileImage);
        profileImageUrl = null;
    }

    /**
     * Provides profile image of the user
     * @return File object for the profile image
     */
    public File getProfileImage() {
        if (profileImageUrl == null) {
            System.out.println("Profile image URL is null");
            return null;
        }
        return downloadImage(profileImageUrl);
    }

    /**
     * sets the cover image of the user
     * @param coverImage Image to set
     * @param imageUploader ImageUploader instance
     */
    public void setCoverImage(File coverImage, SimpleImageUploader imageUploader) {
        if (coverImage == null) {
            System.out.println("Cover image is null");
            return;
        }
        // URL is not expected in return
        imageUploader.uploadImage(coverImage);
    }

    /**
     * Downloads the image
     * @param imageUrl URL of the image
     * @return File representing downloaded image
     */
    private File downloadImage(String imageUrl) {
        System.out.println("Downloading image from " + imageUrl);
        // Assume image downloaded
        System.out.println("Download complete");
        return new File(imageUrl);
    }
}
