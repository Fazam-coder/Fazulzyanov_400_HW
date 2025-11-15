package ru.kpfu.itis.fazulzyanov.homework8;

import com.cloudinary.Cloudinary;
import ru.kpfu.itis.fazulzyanov.homework8.util.CloudinaryUtil;

import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.HashMap;

public class FileService {
    public static final String FILE_PREFIX = "\\tmp";
    public static final int DIRECTORIES_COUNT = 100;
    public static final Cloudinary cloudinary = CloudinaryUtil.getInstance();

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

        return cloudinary.uploader().upload(file, new HashMap()).get("url").toString();
    }
}
