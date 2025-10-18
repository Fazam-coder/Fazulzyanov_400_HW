package ru.kpfu.itis.fazulzyanov.homework7;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/files/*")
public class FileDownloadServlet extends HttpServlet {
    private static final String FILE_PREFIX = "/tmp";
    private static final int DIRECTORIES_COUNT = 100;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String filename = req.getPathInfo();
        filename = filename.substring(1);
        File file = new File(FILE_PREFIX, Math.abs(filename.hashCode() % DIRECTORIES_COUNT) + File.separator + filename);

        String mimeType = getServletContext().getMimeType(file.getAbsolutePath());
        if (mimeType == null) {
            mimeType = "application/octet-stream"; // fallback
        }
        resp.setContentType(mimeType);
        resp.setContentLength((int) file.length());
        try (FileInputStream fis = new FileInputStream(file);
             OutputStream os = resp.getOutputStream()) {
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        }
    }
}
