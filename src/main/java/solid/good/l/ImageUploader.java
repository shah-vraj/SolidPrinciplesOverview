package solid.good.l;

import java.io.File;

public interface ImageUploader<T> {
    void setupConfiguration();
    T uploadImage(File image);
}
