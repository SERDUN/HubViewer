package dmitroserdun.com.ua.hubviewer.repository.local;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import dmitroserdun.com.ua.hubviewer.data.model.Repository;
import dmitroserdun.com.ua.hubviewer.repository.GitHubDataSource;

/**
 * Created by User on 17.08.2017.
 */

public class LocalGitHubDataSource implements GitHubDataSource {
    private static LocalGitHubDataSource INSTANCE = null;

    public static LocalGitHubDataSource getInstance(Context context) {
        //use context for database
        if (INSTANCE == null) {
            INSTANCE = new LocalGitHubDataSource();
        }
        return INSTANCE;
    }

    @Deprecated
    @Override
    public void authentication(String login, String password, @NonNull Callback callback) {
//Only for the server part
    }

    @Override
    public void getCurrentUser(String token, @NonNull Callback callback) {

    }

    @Override
    public void getUser(String username, @NonNull Callback callback) {

    }

    @Override
    public void getCurrentUserRepositories(@NonNull Callback callback) {

    }

    @Override
    public void getRepositories(String username, @NonNull Callback<List<Repository>> callback) {

    }

    @Override
    public void searchRepository(String name, @NonNull Callback callback) {

    }

    @Override
    public void refreshLocalData() {

    }
}
