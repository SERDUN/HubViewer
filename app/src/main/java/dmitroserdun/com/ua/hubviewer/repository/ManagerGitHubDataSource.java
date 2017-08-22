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
import dmitroserdun.com.ua.hubviewer.repository.callback.Action0;
import dmitroserdun.com.ua.hubviewer.repository.callback.Action1;
import dmitroserdun.com.ua.hubviewer.repository.local.LocalGitHubDataSource;
import dmitroserdun.com.ua.hubviewer.repository.remote.RemoteGitHubDataSource;

/**
 * Created by User on 17.08.2017.
 */

public class ManagerGitHubDataSource implements GitHubDataSource {

    private static ManagerGitHubDataSource INSTANCE = null;
    private final LocalGitHubDataSource localRepository;
    private final RemoteGitHubDataSource remoteRepository;
    private boolean useRemoteDataSource = false;

    public ManagerGitHubDataSource(@NonNull LocalGitHubDataSource localRepository, @NonNull RemoteGitHubDataSource remoteRepository) {
        this.localRepository = localRepository;
        this.remoteRepository = remoteRepository;
    }

    public static ManagerGitHubDataSource getInstance(LocalGitHubDataSource localRepository,
                                                      RemoteGitHubDataSource remoteRepository) {
        if (INSTANCE == null) {
            INSTANCE = new ManagerGitHubDataSource(localRepository, remoteRepository);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }


    @Override
    public void authentication(String login, String password, Action1<Authorization> onSuccess, Action1 onFailure, Action0 onComplete) {
        remoteRepository.authentication(login, password, onSuccess, onFailure, onComplete);

    }

    @Override
    public void getCurrentUser(String token, Action1<User> onSuccess, Action1 onFailure, Action0 onComplete) {
        if (useRemoteDataSource) {
            remoteRepository.getCurrentUser(token, onSuccess, onFailure, onComplete);
            useRemoteDataSource = false;
        } else localRepository.getCurrentUser(token, onSuccess, onFailure, onComplete);
    }

    @Override
    public void getUser(String username, Action1<User> onSuccess, Action1 onFailure, Action0 onComplete) {
        remoteRepository.getUser(username, onSuccess, onFailure, onComplete);

    }

    @Override
    public void getCurrentUserRepositories(String token, Action1<List<Repository>> onSuccess, Action1 onFailure, Action0 onComplete) {
        remoteRepository.getCurrentUserRepositories(token, onSuccess, onFailure, onComplete);

    }

    @Override
    public void getRepositories(String username, Action1<List<Repository>> onSuccess, Action1 onFailure, Action0 onComplete) {
        if (useRemoteDataSource) {
            remoteRepository.getRepositories(username, onSuccess, onFailure, onComplete);
            useRemoteDataSource = false;
        } else localRepository.getRepositories(username, onSuccess, onFailure, onComplete);
    }

    @Override
    public void getDetailsRepositories(String username, String reponame, Action1<RepositoryDetails> onSuccess, Action1 onFailure, Action0 onComplete) {
        remoteRepository.getDetailsRepositories(username, reponame, onSuccess, onFailure, onComplete);

    }

    @Override
    public void searchRepository(String name, Action1<Page> onSuccess, Action1 onFailure, Action0 onComplete) {
        remoteRepository.searchRepository(name, onSuccess, onFailure, onComplete);

    }

    @Override
    public void getContentForDirectory(String name, String reponame, String path, Action1<List<Directory>> onSuccess, Action1 onFailure, Action0 onComplete) {
        remoteRepository.getContentForDirectory(name, reponame, path, onSuccess, onFailure, onComplete);

    }

    @Override
    public void getUserEvents(String username, Action1<List<Event>> onSuccess, Action1 onFailure, Action0 onComplete) {
        remoteRepository.getUserEvents(username, onSuccess, onFailure, onComplete);

    }

    @Override
    public void loadFile(String url, Action1<byte[]> onSuccess, Action1 onFailure, Action0 onComplete) {
        remoteRepository.loadFile(url, onSuccess, onFailure, onComplete);
    }

    @Override
    public void refreshLocalData() {
        useRemoteDataSource = true;

    }
}
