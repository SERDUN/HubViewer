package dmitroserdun.com.ua.hubviewer.repository.remote;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import dmitroserdun.com.ua.hubviewer.data.model.Authorization;
import dmitroserdun.com.ua.hubviewer.data.model.directory.Directory;
import dmitroserdun.com.ua.hubviewer.data.model.events.Event;
import dmitroserdun.com.ua.hubviewer.data.model.repository.Page;
import dmitroserdun.com.ua.hubviewer.data.model.repository.Repository;
import dmitroserdun.com.ua.hubviewer.data.model.repository.RepositoryDetails;
import dmitroserdun.com.ua.hubviewer.data.model.user.User;
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
    private CallbackError callbackError;

    public static RemoteGitHubDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RemoteGitHubDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void onFailureDetect(CallbackError callbackError) {
        this.callbackError = callbackError;
    }

    @Override
    public void authentication(String login, String password, @NonNull Callback<Authorization> callback) {

        GitGubNetworkFactory.getService()
                .authorize(BasicAuthUtils.generateAuthorizationString(login, password),
                        BasicAuthUtils.createAuthorizationParam()).enqueue(new retrofit2.Callback<Authorization>() {
            @Override
            public void onResponse(Call<Authorization> call, Response<Authorization> response) {
                callback.onLoaded(response.body());
            }

            @Override
            public void onFailure(Call<Authorization> call, Throwable t) {
                callbackError.onFailure(t.getMessage());
            }
        });

    }

    @Override
    public void getCurrentUser(String token, @NonNull Callback<User> callback) {
        GitGubNetworkFactory.getService().getCurrentUser(token).enqueue(new retrofit2.Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                callback.onLoaded(response.body());

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                callbackError.onFailure(t.getMessage());

            }
        });

    }

    @Override
    public void getUser(String username, @NonNull Callback callback) {
        GitGubNetworkFactory.getService().getUser(username).enqueue(new retrofit2.Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                callback.onLoaded(response.body());

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                callbackError.onFailure(t.getMessage());

            }
        });
    }

    @Override
    public void getCurrentUserRepositories(String token, @NonNull Callback<List<Repository>> callback) {
        GitGubNetworkFactory.getService().getCurrentRepos(token).enqueue(new retrofit2.Callback<List<Repository>>() {
            @Override
            public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
                callback.onLoaded(response.body());

            }

            @Override
            public void onFailure(Call<List<Repository>> call, Throwable t) {
                callbackError.onFailure(t.getMessage());

            }
        });
    }

    @Override
    public void getRepositories(String username, @NonNull Callback<List<Repository>> callback) {
        GitGubNetworkFactory.getService().getUserRepository(username).enqueue(new retrofit2.Callback<List<Repository>>() {
            @Override
            public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
                callback.onLoaded(response.body());

            }

            @Override
            public void onFailure(Call<List<Repository>> call, Throwable t) {
                callbackError.onFailure(t.getMessage());

            }
        });
    }

    @Override
    public void getDetailsRepositories(String username, String reponame, @NonNull Callback<RepositoryDetails> callback) {
        GitGubNetworkFactory.getService().getDetailsRepository(username, reponame).enqueue(new retrofit2.Callback<RepositoryDetails>() {
            @Override
            public void onResponse(Call<RepositoryDetails> call, Response<RepositoryDetails> response) {
                callback.onLoaded(response.body());
            }

            @Override
            public void onFailure(Call<RepositoryDetails> call, Throwable t) {
                callbackError.onFailure(t.getMessage());

            }
        });
    }

    @Override
    public void getContentForDirectory(String name, String reponame, String path, @NonNull Callback<List<Directory>> callback) {
        GitGubNetworkFactory.getService().getDirectory(name, reponame, path).enqueue(new retrofit2.Callback<List<Directory>>() {
            @Override
            public void onResponse(Call<List<Directory>> call, Response<List<Directory>> response) {
                callback.onLoaded(response.body());

            }

            @Override
            public void onFailure(Call<List<Directory>> call, Throwable t) {
                callbackError.onFailure(t.getMessage());

            }
        });
    }

    @Override
    public void getUserEvents(String username, Callback<List<Event>> callback) {
        GitGubNetworkFactory.getService().getUserEvent(username).enqueue(new retrofit2.Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                callback.onLoaded(response.body());

            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                callbackError.onFailure(t.getMessage());

            }
        });

    }


    @Override
    public void searchRepository(String q, @NonNull Callback callback) {
        GitGubNetworkFactory.getService().getSearchRepository(q).enqueue(new retrofit2.Callback<Page>() {
            @Override
            public void onResponse(Call<Page> call, Response<Page> response) {
                callback.onLoaded(response.body());

                if (response.body() == null) {
                    Log.d("text_dd", "onResponse: " + response.message());
                }

            }

            @Override
            public void onFailure(Call<Page> call, Throwable t) {
                callbackError.onFailure(t.getMessage());

            }
        });
    }

    @Override
    public void refreshLocalData() {

    }
}
