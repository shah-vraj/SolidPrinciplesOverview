package solid.good.l;

public abstract class SimpleImageUploader implements ImageUploader<Void> {

    protected String baseUrl;
    protected String apiKey;

    public SimpleImageUploader(String baseUrl, String apiKey) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
    }
}
