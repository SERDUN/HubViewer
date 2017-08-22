package dmitroserdun.com.ua.hubviewer.activity.screen.repositoryList.presenter;

import android.content.SharedPreferences;

import dmitroserdun.com.ua.hubviewer.data.model.repository.Repository;
import dmitroserdun.com.ua.hubviewer.repository.ManagerGitHubDataSource;
import dmitroserdun.com.ua.hubviewer.activity.screen.repositoryList.RepositoryListContract;

import static dmitroserdun.com.ua.hubviewer.utils.Constance.CURRENT_TOKEN_KEY;

/**
 * Created by User on 19.08.2017.
 */

public class UserRepositoryListPresenter implements RepositoryListContract.Presenter {
    private RepositoryListContract.View view;
    private ManagerGitHubDataSource managerGitHubDataSource;
    private String username;
    private SharedPreferences pref;


    public UserRepositoryListPresenter(RepositoryListContract.View view,
                                       ManagerGitHubDataSource managerGitHubDataSource,
                                       String username, SharedPreferences pref) {
        this.view = view;
        view.setPresenter(this);//register view
        this.managerGitHubDataSource = managerGitHubDataSource;
        this.username = username;
        this.pref = pref;
    }

    @Override
    public void loadRepository() {
        // TODO: 19.08.2017 Handle the Failure
        if (username.isEmpty()) {
            managerGitHubDataSource.getCurrentUserRepositories(getToken(), repo -> {
                view.showRepository(repo);
            }, t -> {
            }, () -> {
            });
        } else {
            managerGitHubDataSource.refreshLocalData();
            managerGitHubDataSource.getRepositories(username, repo -> {
                view.showRepository(repo);
            }, t -> {
            }, () -> {
            });
        }

    }

    @Override
    public void search(String q) {

    }

    @Override
    public void openRepositoryDetails(Repository repository) {
        view.openRepositoryDetails(repository);

    }


    private String getToken() {
        return pref.getString(CURRENT_TOKEN_KEY, "");

    }

}
