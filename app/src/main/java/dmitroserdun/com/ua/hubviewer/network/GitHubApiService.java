package dmitroserdun.com.ua.hubviewer.network;

import com.google.gson.JsonObject;

import dmitroserdun.com.ua.hubviewer.data.Authorization;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by User on 17.08.2017.
 */

public interface GitHubApiService {

    @POST("/authorizations")
    public Call<Authorization> authorize(@Header("Authorization") String authorization,
                                         @Body JsonObject params);

//    @GET("/user/repos")
//    public Call<List<GitHubDataSource>> repositories();


}
