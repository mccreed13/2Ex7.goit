import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpStatusChecker {
    private static final String URL_CAT = "https://http.cat/";
    public static final OkHttpClient HTTP_CLIENT = new OkHttpClient();
    public static final Request.Builder REQUEST_BUILDER = new Request.Builder();


    public static String getStatusImage(int code) throws Exception {
        String url = URL_CAT+code;
        Request request = REQUEST_BUILDER.get()
                .url(url)
                .build();
        Call call = HTTP_CLIENT.newCall(request);
        Response response = call.execute();
        if(response.isSuccessful()){
           return url;
        }
        throw new Exception("There is not image for HTTP status " + code);
    }
}
