package dmitroserdun.com.ua.hubviewer.network;

import dmitroserdun.com.ua.hubviewer.BuildConfig;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by User on 17.08.2017.
 */

public class GitGubNetworkFactory {
    private static GitHubApiService service;
    private static OkHttpClient okHttpClient;
    private static GitHubInterceptor interceptor;

    public static void setInterceptorAccessToken(String token) {
        interceptor = new GitHubInterceptor(token);
    }

    public static GitHubApiService getService() {
        GitHubApiService currentService = service;
        if (currentService == null) {
            synchronized (GitHubApiService.class) {
                if (currentService == null) {
                    currentService = service = getRetrofitBuilder().create(GitHubApiService.class);
                }
            }
        }
        return currentService;
    }


    private static Retrofit getRetrofitBuilder() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BuildConfig.URL)
                .addConverterFactory(GsonConverterFactory.create());
        if (interceptor != null) {
            synchronized (GitHubApiService.class) {
                if (interceptor != null) {
                    builder.client(getOkHttpClient());
                }
            }
        }
        return builder.build();

    }

    private static OkHttpClient getOkHttpClient() {
        OkHttpClient client = okHttpClient;
        if (client == null) {
            synchronized (GitHubApiService.class) {
                client = okHttpClient;
                if (client == null) {
                    client = okHttpClient = buildOkHttpClient();
                }
            }
        }
        return client;
    }

    private static OkHttpClient buildOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor).build();
    }
}
