package dmitroserdun.com.ua.hubviewer.view.screen.repositoryListy.presenter;

import android.content.SharedPreferences;

import java.util.List;

import dmitroserdun.com.ua.hubviewer.data.model.Repository;
import dmitroserdun.com.ua.hubviewer.repository.GitHubDataSource;
import dmitroserdun.com.ua.hubviewer.repository.ManagerGitHubDataSource;
import dmitroserdun.com.ua.hubviewer.view.screen.repositoryListy.RepositoryListContract;

import static dmitroserdun.com.ua.hubviewer.utils.Constance.CURRENT_USERNAME;

/**
 * Created by User on 19.08.2017.
 */

public class UserRepositoryListPresenter implements RepositoryListContract.Presenter {
    private RepositoryListContract.View view;
    private ManagerGitHubDataSource managerGitHubDataSource;
    private SharedPreferences pref;

    public UserRepositoryListPresenter(RepositoryListContract.View view,
                                       ManagerGitHubDataSource managerGitHubDataSource,
                                       SharedPreferences pref) {
        this.view = view;
        view.setPresenter(this);//register view
        this.managerGitHubDataSource = managerGitHubDataSource;
        this.pref = pref;
    }

    @Override
    public void loadRepository() {
        // TODO: 19.08.2017 Handle the Failure
        managerGitHubDataSource.refreshLocalData();
        managerGitHubDataSource.getRepositories(getLoginUserForRequest(), new GitHubDataSource.Callback<List<Repository>>() {
            @Override
            public void onLoaded(List<Repository> o) {
                view.showRepository(o);
            }

            @Override
            public void onFailure(String e) {

            }
        });

    }

    @Override
    public void search(String q) {

    }

    private String getLoginUserForRequest() {
        return pref.getString(CURRENT_USERNAME, "");

    }
}
