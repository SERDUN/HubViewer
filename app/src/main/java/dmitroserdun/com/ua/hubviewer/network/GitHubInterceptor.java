package dmitroserdun.com.ua.hubviewer.network;

import android.util.Log;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by User on 17.08.2017.
 */

public class GitHubInterceptor implements Interceptor {
    String token;
    private final String TAG = "GitHubInterceptor";

    public GitHubInterceptor(String token) {
        this.token = token;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl url = request.url().newBuilder().addQueryParameter("access_token", token).build();
        Log.d(TAG, "intercept: " + url.toString());
        request = request.newBuilder().url(url).build();
        return chain.proceed(request);
    }
}
