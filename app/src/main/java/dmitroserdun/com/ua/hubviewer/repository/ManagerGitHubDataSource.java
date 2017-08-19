package dmitroserdun.com.ua.hubviewer.repository;

import android.support.annotation.NonNull;

import java.util.List;

import dmitroserdun.com.ua.hubviewer.data.model.Authorization;
import dmitroserdun.com.ua.hubviewer.data.model.Repository;
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
    public void authentication(String login, String password, @NonNull Callback<Authorization> callback) {
        remoteRepository.authentication(login, password, callback);
    }

    @Override
    public void getCurrentUser(String token, @NonNull Callback callback) {
        if (useRemoteDataSource) {
            remoteRepository.getCurrentUser(token, callback);
            useRemoteDataSource = false;
        } else localRepository.getCurrentUser(token, callback);

    }

    @Override
    public void getUser(String username, @NonNull Callback callback) {

    }

    @Override
    public void getCurrentUserRepositories(@NonNull Callback callback) {

    }

    @Override
    public void getRepositories(String username, @NonNull Callback<List<Repository>> callback) {
        if (useRemoteDataSource) {
            remoteRepository.getRepositories(username, callback);
            useRemoteDataSource = false;
        } else localRepository.getRepositories(username, callback);


    }

    @Override
    public void searchRepository(String name, @NonNull Callback callback) {

    }

    @Override
    public void refreshLocalData() {
        useRemoteDataSource = true;

    }
}
