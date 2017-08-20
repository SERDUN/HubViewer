package dmitroserdun.com.ua.hubviewer.repository;

import android.support.annotation.NonNull;

import java.util.List;

import dmitroserdun.com.ua.hubviewer.data.model.Authorization;
import dmitroserdun.com.ua.hubviewer.data.model.directory.Directory;
import dmitroserdun.com.ua.hubviewer.data.model.events.Event;
import dmitroserdun.com.ua.hubviewer.data.model.repository.Page;
import dmitroserdun.com.ua.hubviewer.data.model.repository.Repository;
import dmitroserdun.com.ua.hubviewer.data.model.repository.RepositoryDetails;
import dmitroserdun.com.ua.hubviewer.data.model.user.User;

/**
 * Created by User on 17.08.2017.
 */

public interface GitHubDataSource {

    interface Callback<T> {

        void onLoaded(T o);

    }

    void authentication(String login, String password, @NonNull Callback<Authorization> callback);

    void getCurrentUser(String token, @NonNull Callback<User> callback);

    void getUser(String username, @NonNull Callback<User> callback);

    void getCurrentUserRepositories(String token, @NonNull Callback<List<Repository>> callback);

    void getRepositories(String username, @NonNull Callback<List<Repository>> callback);

    void getDetailsRepositories(String username, String reponame, @NonNull Callback<RepositoryDetails> callback);

    void searchRepository(String name, @NonNull Callback<Page> callback);

    void getContentForDirectory(String name, String reponame, String path, @NonNull Callback<List<Directory>> call);

    void getUserEvents(String username, @NonNull Callback<List<Event>> callback);

    void refreshLocalData();
}

