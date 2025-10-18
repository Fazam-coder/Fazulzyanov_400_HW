package ru.kpfu.itis.fazulzyanov.homework7;

import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

public class FileService {
    public static final String FILE_PREFIX = "\\tmp";
    public static final int DIRECTORIES_COUNT = 100;

    public static String saveAndGetPathname(Part part) throws IOException {
        String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
        String pathname = FILE_PREFIX + File.separator
                + Math.abs(filename.hashCode() % DIRECTORIES_COUNT) + File.separator + filename;
        File file = new File(pathname);
        InputStream content = part.getInputStream();
        file.getParentFile().mkdirs();
        file.createNewFile();
        FileOutputStream outputStream = new FileOutputStream(file);
        byte[] buffer = new byte[content.available()];
        content.read(buffer);
        outputStream.write(buffer);
        outputStream.close();

        return pathname;
    }
}
