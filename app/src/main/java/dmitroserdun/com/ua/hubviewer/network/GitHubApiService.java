package dmitroserdun.com.ua.hubviewer.network;

import com.google.gson.JsonObject;

import java.util.List;

import dmitroserdun.com.ua.hubviewer.data.model.Authorization;
import dmitroserdun.com.ua.hubviewer.data.model.directory.Directory;
import dmitroserdun.com.ua.hubviewer.data.model.events.Event;
import dmitroserdun.com.ua.hubviewer.data.model.repository.Page;
import dmitroserdun.com.ua.hubviewer.data.model.repository.Repository;
import dmitroserdun.com.ua.hubviewer.data.model.repository.RepositoryDetails;
import dmitroserdun.com.ua.hubviewer.data.model.user.User;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by User on 17.08.2017.
 */

public interface GitHubApiService {

    @POST("/authorizations")
    public Call<Authorization> authorize(@Header("Authorization") String authorization,
                                         @Body JsonObject params);

    @GET("/user")
    public Call<User> getCurrentUser(@Query("access_token") String token);

    @GET("/user/repos")
    public Call<List<Repository>> getCurrentRepos(@Query("access_token") String token);

    @GET("/users/{username}")
    public Call<User> getUser(@Path("username") String username);

    @GET("/users/{username}/repos")
    public Call<List<Repository>> getUserRepository(@Path("username") String username);

    @GET("/search/repositories")
    public Call<Page> getSearchRepository(@Query("q") String q);

    @GET("/repos/{username}/{reponame}")
    public Call<RepositoryDetails> getDetailsRepository(@Path("username") String username,@Path("reponame") String reponame);

    @GET("/repos/{username}/{reponame}/contents/{dir}")
    public Call<List<Directory>> getDirectory(@Path("username") String username, @Path("reponame") String reponame,@Path("dir") String dir);


    @GET("/users/{username}/events")
    public Call<List<Event>> getUserEvent(@Path("username") String username);


    @GET
    public Call<ResponseBody> downloadFile(@Url String fileUrl);


}
