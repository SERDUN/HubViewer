package dmitroserdun.com.ua.hubviewer.repository.remote;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import dmitroserdun.com.ua.hubviewer.data.model.Authorization;
import dmitroserdun.com.ua.hubviewer.data.model.Page;
import dmitroserdun.com.ua.hubviewer.data.model.Repository;
import dmitroserdun.com.ua.hubviewer.data.model.RepositoryDetails;
import dmitroserdun.com.ua.hubviewer.data.model.User;
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

            }
        });

    }

    @Override
    public void getUser(String username, @NonNull Callback callback) {
        Call<User> user = GitGubNetworkFactory.getService().getUser(username);
        user.enqueue(new retrofit2.Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                callback.onLoaded(response.body());

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    @Override
    public void getCurrentUserRepositories(String token, @NonNull Callback<List<Repository>> callback) {

        Call<List<Repository>> repos = GitGubNetworkFactory.getService().getCurrentRepos(token);
        repos.enqueue(new retrofit2.Callback<List<Repository>>() {
            @Override
            public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
                callback.onLoaded(response.body());

            }

            @Override
            public void onFailure(Call<List<Repository>> call, Throwable t) {

            }
        });
    }

    @Override
    public void getRepositories(String username, @NonNull Callback<List<Repository>> callback) {
        Call<List<Repository>> repository = GitGubNetworkFactory.getService().getUserRepository(username);
        repository.enqueue(new retrofit2.Callback<List<Repository>>() {
            @Override
            public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
                callback.onLoaded(response.body());

            }

            @Override
            public void onFailure(Call<List<Repository>> call, Throwable t) {

            }
        });
    }

    @Override
    public void getDetailsRepositories(String username, String reponame, @NonNull Callback<RepositoryDetails> callback) {
        Call<RepositoryDetails> repositoryDetailsCall = GitGubNetworkFactory.getService().getDetailsRepository(username, reponame);
        repositoryDetailsCall.enqueue(new retrofit2.Callback<RepositoryDetails>() {
            @Override
            public void onResponse(Call<RepositoryDetails> call, Response<RepositoryDetails> response) {
                callback.onLoaded(response.body());
            }

            @Override
            public void onFailure(Call<RepositoryDetails> call, Throwable t) {

            }
        });
    }


    @Override
    public void searchRepository(String q, @NonNull Callback callback) {

        Call<Page> pageCall = GitGubNetworkFactory.getService().getSearchRepository(q);
        pageCall.enqueue(new retrofit2.Callback<Page>() {
            @Override
            public void onResponse(Call<Page> call, Response<Page> response) {
                callback.onLoaded(response.body());

                if (response.body() == null) {
                    Log.d("text_dd", "onResponse: " + response.message());
                }

            }

            @Override
            public void onFailure(Call<Page> call, Throwable t) {

            }
        });
    }

    @Override
    public void refreshLocalData() {

    }
}
