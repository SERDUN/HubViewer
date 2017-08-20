package dmitroserdun.com.ua.hubviewer.repository;

import android.support.annotation.NonNull;

import java.util.List;

import dmitroserdun.com.ua.hubviewer.data.model.Authorization;
import dmitroserdun.com.ua.hubviewer.data.model.directory.Directory;
import dmitroserdun.com.ua.hubviewer.data.model.repository.Repository;
import dmitroserdun.com.ua.hubviewer.data.model.repository.RepositoryDetails;
import dmitroserdun.com.ua.hubviewer.data.model.user.User;
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
    public void getCurrentUser(String token, @NonNull Callback<User> callback) {
        if (useRemoteDataSource) {
            remoteRepository.getCurrentUser(token, callback);
            useRemoteDataSource = false;
        } else localRepository.getCurrentUser(token, callback);

    }

    @Override
    public void getUser(String username, @NonNull Callback<User> callback) {
        remoteRepository.getUser(username, callback);
    }

    @Override
    public void getCurrentUserRepositories(String token, @NonNull Callback<List<Repository>> callback) {
remoteRepository.getCurrentUserRepositories(token,callback);
    }

    @Override
    public void getRepositories(String username, @NonNull Callback<List<Repository>> callback) {
        if (useRemoteDataSource) {
            remoteRepository.getRepositories(username, callback);
            useRemoteDataSource = false;
        } else localRepository.getRepositories(username, callback);


    }

    @Override
    public void getDetailsRepositories(String username, String reponame, @NonNull Callback<RepositoryDetails> callback) {
        remoteRepository.getDetailsRepositories(username,reponame,callback);
    }

    @Override
    public void getContentForDirectory(String name, String reponame, String path, @NonNull Callback<List<Directory>> call) {
        remoteRepository.getContentForDirectory(name,reponame,path,call);
    }

    @Override
    public void searchRepository(String q, @NonNull Callback callback) {
        remoteRepository.searchRepository(q, callback);
    }

    @Override
    public void refreshLocalData() {
        useRemoteDataSource = true;

    }
}
