package dmitroserdun.com.ua.hubviewer.repository;

import java.util.List;

import dmitroserdun.com.ua.hubviewer.data.model.Authorization;
import dmitroserdun.com.ua.hubviewer.data.model.directory.Directory;
import dmitroserdun.com.ua.hubviewer.data.model.events.Event;
import dmitroserdun.com.ua.hubviewer.data.model.repository.Page;
import dmitroserdun.com.ua.hubviewer.data.model.repository.Repository;
import dmitroserdun.com.ua.hubviewer.data.model.repository.RepositoryDetails;
import dmitroserdun.com.ua.hubviewer.data.model.user.User;
import dmitroserdun.com.ua.hubviewer.repository.callback.Action0;
import dmitroserdun.com.ua.hubviewer.repository.callback.Action1;

/**
 * Created by User on 17.08.2017.
 */

public interface GitHubDataSource {


    interface CallbackError {
    }

    void authentication(String login, String password, Action1<Authorization> onSuccess, Action1 onFailure, Action0 onComplete);

    void getCurrentUser(String token,Action1<User> onSuccess, Action1 onFailure, Action0 onComplete);

    void getUser(String username, Action1<User> onSuccess, Action1 onFailure, Action0 onComplete);

    void getCurrentUserRepositories(String token,Action1<List<Repository>> onSuccess, Action1 onFailure, Action0 onComplete);

    void getRepositories(String username,Action1<List<Repository>> onSuccess, Action1 onFailure, Action0 onComplete);

    void getDetailsRepositories(String username,String reponame, Action1<RepositoryDetails> onSuccess, Action1 onFailure, Action0 onComplete);

    void searchRepository(String name, Action1<Page> onSuccess, Action1 onFailure, Action0 onComplete);

    void getContentForDirectory(String name, String reponame, String path, Action1<List<Directory>> onSuccess, Action1 onFailure, Action0 onComplete);

    void getUserEvents(String username, Action1<List<Event>> onSuccess, Action1 onFailure, Action0 onComplete);

    void refreshLocalData();
}

