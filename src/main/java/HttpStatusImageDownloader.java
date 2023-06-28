import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class HttpStatusImageDownloader {
    private static final String FOLDER = "download_files";
    private static final String USER_FOLDER = System.getProperty("user.dir");

    public void downloadStatusImage(int code) throws Exception {
        String url = HttpStatusChecker.getStatusImage(code);
        Request request = HttpStatusChecker.REQUEST_BUILDER.get()
                        .url(url)
                        .build();
        Call call = HttpStatusChecker.HTTP_CLIENT.newCall(request);
        Response response = call.execute();
        String fileName = code + ".jpg";
        InputStream inputStream = response.body().byteStream();

        Path path = getPath();
        Files.createDirectories(path);
        File file = new File(path + File.separator + fileName);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(inputStream.readAllBytes());
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    private static Path getPath() {
        return Path.of(USER_FOLDER + File.separator + FOLDER);
    }
}