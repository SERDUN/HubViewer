package dmitroserdun.com.ua.hubviewer.repository;

import android.support.annotation.NonNull;

import java.util.List;

import dmitroserdun.com.ua.hubviewer.data.model.Authorization;
import dmitroserdun.com.ua.hubviewer.data.model.Page;
import dmitroserdun.com.ua.hubviewer.data.model.Repository;
import dmitroserdun.com.ua.hubviewer.data.model.User;

/**
 * Created by User on 17.08.2017.
 */

public interface GitHubDataSource {

    interface Callback<T> {

        void onLoaded(T o);

        void onFailure(String e);
    }

    void authentication(String login, String password, @NonNull Callback<Authorization> callback);

    void getCurrentUser(String token, @NonNull Callback<User> callback);

    void getUser(String username, @NonNull Callback callback);

    // TODO: 19.08.2017 delete this method
    void getCurrentUserRepositories(@NonNull Callback callback);

    void getRepositories(String username, @NonNull Callback<List<Repository>> callback);

    void searchRepository(String name, @NonNull Callback<Page> callback);

    void refreshLocalData();
}

