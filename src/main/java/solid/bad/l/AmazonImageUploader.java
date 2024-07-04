package solid.bad.l;

import java.io.File;

public class AmazonImageUploader extends ImageUploader {

    public AmazonImageUploader(String baseUrl, String apiKey) {
        super(baseUrl, apiKey);
    }

    @Override
    public void setupConfiguration() {
        System.out.println("Setting up configuration for Amazon ImageUploader");
    }

    @Override
    public String uploadImage(File image) {
        if (image == null) {
            System.out.println("Image is null!");
            return null;
        }
        System.out.println("Uploading image " + image.getName() + " to " + baseUrl + " (Amazon)");
        // Suppose amazon does not return URL in return
        return null;
    }
}
