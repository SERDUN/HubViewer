package dmitroserdun.com.ua.hubviewer.network;

import dmitroserdun.com.ua.hubviewer.BuildConfig;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by User on 19.08.2017.
 */

public class TokenConnector {
    private static final String CLIENT_ID = "client_id";
    private static final String CLIENT_SECRET = "client_secret";
    private static final String CODE = "code";

    public static final void getToken(String code, Callback callback) {
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart(CLIENT_ID, BuildConfig.CLIENT_ID)
                .addFormDataPart(CLIENT_SECRET, BuildConfig.CLIENT_SECRET)
                .addFormDataPart(CODE, code)
                .build();

          String OAUTH_ACCESS_TOKEN_URL = "https://github.com/login/oauth/access_token";

        Request request = new Request.Builder()
                .url(OAUTH_ACCESS_TOKEN_URL)
                .method("POST", RequestBody.create(null, new byte[0]))
                .post(requestBody)
                .build();

        OkHttpClient client = new OkHttpClient();

        client.newCall(request).enqueue(callback);

    }
}
