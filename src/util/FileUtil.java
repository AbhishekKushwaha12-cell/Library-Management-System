package util;

import java.io.File;
import java.io.IOException;

public class FileUtil {
    public static final String FILE_NAME = "books.txt";

    public static void initializeFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
        }
    }
}
