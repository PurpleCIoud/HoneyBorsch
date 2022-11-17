package net.honey;

import java.io.InputStream;

// This class is here for me to make use of resources from the resource folders
public class ResourceReader {
    public InputStream getFileAsIOStream(final String fileName) {
        InputStream ioStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream(fileName);

        if (ioStream == null) {
            throw new IllegalArgumentException(fileName + " is not found");
        }
        return ioStream;
    }
}
