package solid.bad.l;

import java.io.File;

public class UserProfileManagement {

    private static final String BASE_URL = "image/uploader/base/url";
    private static final String API_KEY = "MyCustomApiKey";

    public static void main(String[] args) {
        int userId = 1;
        UserProfileManager manager = new UserProfileManager(userId);

        // Name
        manager.setName("Vraj Shah");
        System.out.println("Retrieved name from user id " + userId + ": " + manager.getName());
        System.out.println();

        // Email
        manager.setEmail("vraj@gmail.com");
        System.out.println("Retrieved email from user id " + userId + ": " + manager.getEmail());
        System.out.println();

        // Hobbies
        manager.addHobby("Cricket");
        manager.addHobby("Cricket");
        manager.addHobby("Football");
        manager.removeHobby("Football");
        manager.removeHobby("Football");
        System.out.println();

        // Image uploader

        // Amazon
        System.out.println("Uploading image to amazon which doesn't provide URL in return");
        AmazonImageUploader amazonImageUploader = new AmazonImageUploader(BASE_URL, API_KEY);
        amazonImageUploader.setupConfiguration();
        manager.setProfileImage(new File("AmazonUploadedImage.jpg"), amazonImageUploader);
        File amazonUploadedProfileImage = manager.getProfileImage();
        System.out.println("Retrieved profile image url: " + (amazonUploadedProfileImage != null ? amazonUploadedProfileImage.getName() : null));
        System.out.println();

        // Firebase
        System.out.println("Uploading image to firebase which does provide URL in return");
        FirebaseImageUploader firebaseImageUploader = new FirebaseImageUploader(BASE_URL, API_KEY);
        firebaseImageUploader.setupConfiguration();
        manager.setProfileImage(new File("FirebaseUploadedImage.jpg"), firebaseImageUploader);
        File firebaseUploadedProfileImage = manager.getProfileImage();
        System.out.println("Retrieved profile image url: " + (firebaseUploadedProfileImage != null ? firebaseUploadedProfileImage.getName() : null));
        System.out.println();

        // Google
        System.out.println("Uploading image to google which also provides URL in return");
        GoogleImageUploader googleImageUploader = new GoogleImageUploader(BASE_URL, API_KEY);
        googleImageUploader.setupConfiguration();
        manager.setProfileImage(new File("GoogleUploadedImage.jpg"), googleImageUploader);
        File googleUploadedProfileImage = manager.getProfileImage();
        System.out.println("Retrieved profile image url: " + (googleUploadedProfileImage != null ? googleUploadedProfileImage.getName() : null));
        System.out.println();

        // Cover image
        manager.setCoverImage(new File("CoverImageWhichDoesntRequireUrl.jpg"), amazonImageUploader);
    }
}
