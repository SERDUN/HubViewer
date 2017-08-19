package dmitroserdun.com.ua.hubviewer.network;

import com.google.gson.JsonObject;

import java.util.List;

import dmitroserdun.com.ua.hubviewer.data.model.Authorization;
import dmitroserdun.com.ua.hubviewer.data.model.Page;
import dmitroserdun.com.ua.hubviewer.data.model.Repository;
import dmitroserdun.com.ua.hubviewer.data.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by User on 17.08.2017.
 */

public interface GitHubApiService {

    @POST("/authorizations")
    public Call<Authorization> authorize(@Header("Authorization") String authorization,
                                         @Body JsonObject params);

    @GET("/user")
    public Call<User> getCurrentUser(@Query("access_token") String token);

    @GET("/users/{username}/repos")
    public Call<List<Repository>> getUserRepository(@Path("username") String username);

    @GET("/search/repositories")
    public Call<Page> getSearchRepository(@Query("q") String q);

}
