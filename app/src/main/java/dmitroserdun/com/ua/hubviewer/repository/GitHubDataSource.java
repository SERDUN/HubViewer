package dmitroserdun.com.ua.hubviewer.repository;

import android.support.annotation.NonNull;

import dmitroserdun.com.ua.hubviewer.data.Authorization;
import dmitroserdun.com.ua.hubviewer.data.User;

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

    void getCurrentUserRepositories(@NonNull Callback callback);

    void getRepositories(String username, @NonNull Callback callback);

    void searchRepository(String name, @NonNull Callback callback);

    void refreshLocalData();
}
