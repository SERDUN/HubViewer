package dmitroserdun.com.ua.hubviewer.repository.remote;

import android.support.annotation.NonNull;

import dmitroserdun.com.ua.hubviewer.data.Authorization;
import dmitroserdun.com.ua.hubviewer.data.User;
import dmitroserdun.com.ua.hubviewer.network.GitGubNetworkFactory;
import dmitroserdun.com.ua.hubviewer.repository.GitHubDataSource;
import dmitroserdun.com.ua.hubviewer.utils.BasicAuthUtils;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by User on 17.08.2017.
 */

public class RemoteGitHubDataSource implements GitHubDataSource {
    private static RemoteGitHubDataSource INSTANCE = null;

    public static RemoteGitHubDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RemoteGitHubDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void authentication(String login, String password, @NonNull Callback<Authorization> callback) {

        Call<Authorization> auth = GitGubNetworkFactory.getService()
                .authorize(BasicAuthUtils.generateAuthorizationString(login, password),
                        BasicAuthUtils.createAuthorizationParam());

        auth.enqueue(new retrofit2.Callback<Authorization>() {
            @Override
            public void onResponse(Call<Authorization> call, Response<Authorization> response) {
                callback.onLoaded(response.body());
            }

            @Override
            public void onFailure(Call<Authorization> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });

    }

    @Override
    public void getCurrentUser(String token, @NonNull Callback<User> callback) {
        Call<User> user = GitGubNetworkFactory.getService().getCurrentUser(token);
        user.enqueue(new retrofit2.Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                callback.onLoaded(response.body());

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                callback.onFailure(t.getMessage());

            }
        });

    }

    @Override
    public void getUser(String username, @NonNull Callback callback) {

    }

    @Override
    public void getCurrentUserRepositories(@NonNull Callback callback) {

    }

    @Override
    public void getRepositories(String username, @NonNull Callback callback) {

    }

    @Override
    public void searchRepository(String name, @NonNull Callback callback) {

    }

    @Override
    public void refreshLocalData() {

    }
}
