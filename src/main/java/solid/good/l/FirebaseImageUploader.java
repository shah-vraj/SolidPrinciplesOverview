package solid.good.l;

import java.io.File;

public class FirebaseImageUploader extends UrlProvidingImageUploader {

    public FirebaseImageUploader(String baseUrl, String apiKey) {
        super(baseUrl, apiKey);
    }

    @Override
    public void setupConfiguration() {
        System.out.println("Setting up configuration for Firebase ImageUploader");
    }

    @Override
    public String uploadImage(File image) {
        if (image == null) {
            System.out.println("Image is null!");
            return null;
        }
        System.out.println("Uploading image " + image.getName() + " to " + baseUrl + " (Firebase)");
        return image.getName();
    }
}
