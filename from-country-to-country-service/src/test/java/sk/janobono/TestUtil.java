package sk.janobono;

import java.io.*;

public class TestUtil {

    public static byte[] getFileData(File file) {
        try (
                InputStream is = new BufferedInputStream(new FileInputStream(file));
                ByteArrayOutputStream os = new ByteArrayOutputStream()
        ) {
            read(is, os);
            return os.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void read(InputStream is, ByteArrayOutputStream os) throws IOException {
        byte[] buffer = new byte[1024];
        int bytesRead = 0;
        while (bytesRead != -1) {
            bytesRead = is.read(buffer);
            if (bytesRead > 0) {
                os.write(buffer, 0, bytesRead);
            }
        }
    }
}
