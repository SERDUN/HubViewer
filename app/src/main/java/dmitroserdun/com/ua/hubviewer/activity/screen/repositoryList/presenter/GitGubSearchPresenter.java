package dmitroserdun.com.ua.hubviewer.activity.screen.repositoryList.presenter;

import android.content.SharedPreferences;

import dmitroserdun.com.ua.hubviewer.data.model.repository.Repository;
import dmitroserdun.com.ua.hubviewer.repository.ManagerGitHubDataSource;
import dmitroserdun.com.ua.hubviewer.activity.screen.repositoryList.RepositoryListContract;

/**
 * Created by User on 19.08.2017.
 */

public class GitGubSearchPresenter implements RepositoryListContract.Presenter {
    private RepositoryListContract.View view;
    private ManagerGitHubDataSource managerGitHubDataSource;
    private SharedPreferences pref;


    public GitGubSearchPresenter(RepositoryListContract.View view,
                                 ManagerGitHubDataSource managerGitHubDataSource,
                                 SharedPreferences pref) {
        this.view = view;
        view.setPresenter(this);//register view
        this.managerGitHubDataSource = managerGitHubDataSource;
        this.pref = pref;
    }

    @Override
    public void loadRepository() {
    }

    @Override
    public void search(String q) {
        view.showLoadingView("");
        if (!q.isEmpty()) {
            managerGitHubDataSource.searchRepository(q, repo -> {
                if (repo != null)
                    view.showRepository(repo.getItems());
            }, t -> {

            }, () -> {
                view.hideLoadingView();

            });
        }
    }

    @Override
    public void openRepositoryDetails(Repository repository) {
        view.openRepositoryDetails(repository);
    }
}
