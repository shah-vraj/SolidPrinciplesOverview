package solid.good.l;

import java.io.File;

public interface ImageUploader<T> {

    /**
     * Setup the configuration for the image uploader
     */
    void setupConfiguration();

    /**
     * Upload the image
     * @param image Image file to upload
     * @return T generic type
     */
    T uploadImage(File image);
}
