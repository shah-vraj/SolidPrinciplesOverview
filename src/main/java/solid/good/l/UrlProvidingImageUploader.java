package solid.good.l;

public abstract class UrlProvidingImageUploader implements ImageUploader<String> {

    protected String baseUrl;
    protected String apiKey;

    public UrlProvidingImageUploader(String baseUrl, String apiKey) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
    }
}
