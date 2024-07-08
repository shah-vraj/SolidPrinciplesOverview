package solid.good.l;

import java.io.File;

public class UserProfileManagement {

    private static final String BASE_URL = "image/uploader/base/url";
    private static final String API_KEY = "MyCustomApiKey";
    private static final SimpleImageUploader amazonImageUploader = new AmazonImageUploader(BASE_URL, API_KEY);
    private static final UrlProvidingImageUploader firebaseImageUploader = new FirebaseImageUploader(BASE_URL, API_KEY);
    private static final UrlProvidingImageUploader googleImageUploader = new GoogleImageUploader(BASE_URL, API_KEY);

    public static void main(String[] args) {
        int userId = 1;
        UserProfileManager manager = new UserProfileManager(userId);

        // Setup image uploader
        amazonImageUploader.setupConfiguration();
        firebaseImageUploader.setupConfiguration();
        googleImageUploader.setupConfiguration();

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
        System.out.println("--- Uploading image to amazon which doesn't provide URL in return ---");
        manager.setProfileImage(new File("D:\\images\\AmazonUploadedImage.jpg"), amazonImageUploader);
        System.out.println();

        // Firebase
        System.out.println("--- Uploading image to firebase which does provide URL in return ---");
        String profileImage1 = manager.setProfileImage(new File("D:\\images\\FirebaseUploadedImage.jpg"), firebaseImageUploader);
        System.out.println("Saved profile image: " + profileImage1);
        File firebaseUploadedProfileImage = manager.getProfileImage();
        System.out.println("Retrieved profile image url: " + (firebaseUploadedProfileImage != null ? firebaseUploadedProfileImage.getPath() : null));
        System.out.println();

        // Google
        System.out.println("--- Uploading image to google which also provides URL in return ---");
        String profileImage2 = manager.setProfileImage(new File("D:\\images\\GoogleUploadedImage.jpg"), googleImageUploader);
        System.out.println("Saved profile image: " + profileImage2);
        File googleUploadedProfileImage = manager.getProfileImage();
        System.out.println("Retrieved profile image url: " + (googleUploadedProfileImage != null ? googleUploadedProfileImage.getAbsolutePath() : null));
        System.out.println();

        // Cover image
        manager.setCoverImage(new File("CoverImageWhichDoesntRequireUrl.jpg"), amazonImageUploader);
    }
}
