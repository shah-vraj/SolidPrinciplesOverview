package solid.bad.l;

import java.io.File;

public abstract class ImageUploader {

    protected String baseUrl;
    protected String apiKey;

    public ImageUploader(String baseUrl, String apiKey) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
    }

    /**
     * Setup the configuration for the image uploader
     */
    public abstract void setupConfiguration();

    /**
     * Upload the image
     * @param image Image file to upload
     * @return String representing URL of the uploaded image
     */
    public abstract String uploadImage(File image);
}
