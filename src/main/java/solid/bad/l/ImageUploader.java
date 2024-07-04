package solid.bad.l;

import java.io.File;

public abstract class ImageUploader {

    protected String baseUrl;
    protected String apiKey;

    public ImageUploader(String baseUrl, String apiKey) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
    }

    public abstract void setupConfiguration();

    public abstract String uploadImage(File image);
}
