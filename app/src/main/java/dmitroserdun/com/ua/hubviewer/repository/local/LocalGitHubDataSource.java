package dmitroserdun.com.ua.hubviewer.repository.local;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.List;

import dmitroserdun.com.ua.hubviewer.data.model.Authorization;
import dmitroserdun.com.ua.hubviewer.data.model.directory.Directory;
import dmitroserdun.com.ua.hubviewer.data.model.events.Event;
import dmitroserdun.com.ua.hubviewer.data.model.repository.Page;
import dmitroserdun.com.ua.hubviewer.data.model.repository.Repository;
import dmitroserdun.com.ua.hubviewer.data.model.repository.RepositoryDetails;
import dmitroserdun.com.ua.hubviewer.data.model.user.User;
import dmitroserdun.com.ua.hubviewer.data.provider.ContractClass;
import dmitroserdun.com.ua.hubviewer.repository.GitHubDataSource;
import dmitroserdun.com.ua.hubviewer.repository.callback.Action0;
import dmitroserdun.com.ua.hubviewer.repository.callback.Action1;

/**
 * Created by User on 17.08.2017.
 */

public class LocalGitHubDataSource implements GitHubDataSource {
    private static LocalGitHubDataSource INSTANCE = null;
    private ContentResolver contentResolver;
    public static LocalGitHubDataSource getInstance(Context context) {
        //use context for database
        if (INSTANCE == null) {
            INSTANCE = new LocalGitHubDataSource(context.getContentResolver());

        }

        return INSTANCE;
    }

    public LocalGitHubDataSource(ContentResolver contentResolver) {
        this.contentResolver = contentResolver;
    }

    @Override
    public void authentication(String login, String password, Action1<Authorization> onSuccess, Action1 onFailure, Action0 onComplete) {

    }

    @Override
    public void getCurrentUser(String token, Action1<User> onSuccess, Action1 onFailure, Action0 onComplete) {
        Cursor cur = contentResolver.query(
                ContractClass.Event.CONTENT_URI,
                ContractClass.Event.DEFAULT_PROJECTION,
                null,
                null,
                null);


    }

    @Override
    public void getUser(String username, Action1<User> onSuccess, Action1 onFailure, Action0 onComplete) {

    }

    @Override
    public void getCurrentUserRepositories(String token, Action1<List<Repository>> onSuccess, Action1 onFailure, Action0 onComplete) {

    }

    @Override
    public void getRepositories(String username, Action1<List<Repository>> onSuccess, Action1 onFailure, Action0 onComplete) {

    }

    @Override
    public void getDetailsRepositories(String username, String reponame, Action1<RepositoryDetails> onSuccess, Action1 onFailure, Action0 onComplete) {

    }

    @Override
    public void searchRepository(String name, Action1<Page> onSuccess, Action1 onFailure, Action0 onComplete) {

    }

    @Override
    public void getContentForDirectory(String name, String reponame, String path, Action1<List<Directory>> onSuccess, Action1 onFailure, Action0 onComplete) {

    }

    @Override
    public void getUserEvents(String username, Action1<List<Event>> onSuccess, Action1 onFailure, Action0 onComplete) {

    }

    @Override
    public void loadFile(String url, Action1<byte[]> onSuccess, Action1 onFailure, Action0 onComplete) {

    }

    @Override
    public void refreshLocalData() {

    }
}
