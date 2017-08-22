package dmitroserdun.com.ua.hubviewer.repository.remote;

import java.io.IOException;
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
import dmitroserdun.com.ua.hubviewer.repository.callback.Action0;
import dmitroserdun.com.ua.hubviewer.repository.callback.Action1;
import dmitroserdun.com.ua.hubviewer.utils.BasicAuthUtils;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
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
    public void authentication(String login, String password, Action1<Authorization> onSuccess, Action1 onFailure, Action0 onComplete) {
        GitGubNetworkFactory.getService()
                .authorize(BasicAuthUtils.generateAuthorizationString(login, password),
                        BasicAuthUtils.createAuthorizationParam()).enqueue(new retrofit2.Callback<Authorization>() {
            @Override
            public void onResponse(Call<Authorization> call, Response<Authorization> response) {
                onSuccess.call(response.body());
                onComplete.call();
            }

            @Override
            public void onFailure(Call<Authorization> call, Throwable t) {
                onFailure.call(t);
                onComplete.call();

            }
        });
    }


    @Override
    public void getCurrentUser(String token, Action1<User> onSuccess, Action1 onFailure, Action0 onComplete) {
        GitGubNetworkFactory.getService().getCurrentUser(token).enqueue(new retrofit2.Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                onSuccess.call(response.body());
                onComplete.call();

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                onFailure.call(t);
                onComplete.call();
            }
        });
    }

    @Override
    public void getUser(String username, Action1<User> onSuccess, Action1 onFailure, Action0 onComplete) {
        GitGubNetworkFactory.getService().getUser(username).enqueue(new retrofit2.Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                onSuccess.call(response.body());
                onComplete.call();

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                onFailure.call(t);
                onComplete.call();

            }
        });
    }

    @Override
    public void getCurrentUserRepositories(String token, Action1<List<Repository>> onSuccess, Action1 onFailure, Action0 onComplete) {
        GitGubNetworkFactory.getService().getCurrentRepos(token).enqueue(new retrofit2.Callback<List<Repository>>() {
            @Override
            public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
                onSuccess.call(response.body());
                onComplete.call();

            }

            @Override
            public void onFailure(Call<List<Repository>> call, Throwable t) {
                onFailure.call(t);
                onComplete.call();
            }
        });
    }

    @Override
    public void getRepositories(String username, Action1<List<Repository>> onSuccess, Action1 onFailure, Action0 onComplete) {

        GitGubNetworkFactory.getService().getUserRepository(username).enqueue(new retrofit2.Callback<List<Repository>>() {
            @Override
            public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
                onSuccess.call(response.body());
                onComplete.call();

            }

            @Override
            public void onFailure(Call<List<Repository>> call, Throwable t) {
                onFailure.call(t);
                onComplete.call();
            }
        });
    }

    @Override
    public void getDetailsRepositories(String username, String reponame, Action1<RepositoryDetails> onSuccess, Action1 onFailure, Action0 onComplete) {
        GitGubNetworkFactory.getService().getDetailsRepository(username, reponame).enqueue(new retrofit2.Callback<RepositoryDetails>() {
            @Override
            public void onResponse(Call<RepositoryDetails> call, Response<RepositoryDetails> response) {
                onSuccess.call(response.body());
                onComplete.call();
            }

            @Override
            public void onFailure(Call<RepositoryDetails> call, Throwable t) {
                onFailure.call(t);
                onComplete.call();
            }
        });
    }

    @Override
    public void searchRepository(String name, Action1<Page> onSuccess, Action1 onFailure, Action0 onComplete) {
        GitGubNetworkFactory.getService().getSearchRepository(name).enqueue(new retrofit2.Callback<Page>() {
            @Override
            public void onResponse(Call<Page> call, Response<Page> response) {
                onSuccess.call(response.body());
                onComplete.call();

            }

            @Override
            public void onFailure(Call<Page> call, Throwable t) {
                onFailure.call(t);
                onComplete.call();
            }
        });
    }

    @Override
    public void getContentForDirectory(String name, String reponame, String path, Action1<List<Directory>> onSuccess, Action1 onFailure, Action0 onComplete) {
        GitGubNetworkFactory.getService().getDirectory(name, reponame, path).enqueue(new retrofit2.Callback<List<Directory>>() {
            @Override
            public void onResponse(Call<List<Directory>> call, Response<List<Directory>> response) {
                onSuccess.call(response.body());
                onComplete.call();

            }

            @Override
            public void onFailure(Call<List<Directory>> call, Throwable t) {
                onFailure.call(t);
                onComplete.call();
            }
        });
    }

    @Override
    public void getUserEvents(String username, Action1<List<Event>> onSuccess, Action1 onFailure, Action0 onComplete) {
        GitGubNetworkFactory.getService().getUserEvent(username).enqueue(new retrofit2.Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                onSuccess.call(response.body());
                onComplete.call();

            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                onFailure.call(t);
                onComplete.call();

            }
        });

    }

    @Override
    public void loadFile(String url, Action1<byte[]> onSuccess, Action1 onFailure, Action0 onComplete) {
        GitGubNetworkFactory.getService().downloadFile(url).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    onSuccess.call(response.body().bytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                onComplete.call();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                onFailure.call(t);
                onComplete.call();
            }
        });
    }

    @Override
    public void refreshLocalData() {

    }
}
