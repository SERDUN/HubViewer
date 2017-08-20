package dmitroserdun.com.ua.hubviewer.view.screen.repositoryListy.presenter;

import android.content.SharedPreferences;

import dmitroserdun.com.ua.hubviewer.data.model.Page;
import dmitroserdun.com.ua.hubviewer.data.model.Repository;
import dmitroserdun.com.ua.hubviewer.repository.GitHubDataSource;
import dmitroserdun.com.ua.hubviewer.repository.ManagerGitHubDataSource;
import dmitroserdun.com.ua.hubviewer.view.screen.repositoryListy.RepositoryListContract;

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
//        managerGitHubDataSource.searchRepository("google", new GitHubDataSource.Callback<Page>() {
//            @Override
//            public void onLoaded(Page o) {
//                view.showRepository(o.getItems());
//            }
//
//            @Override
//            public void onFailure(String e) {
//                int r=3;
//            }
//        });

    }

    @Override
    public void search(String q) {
        view.showLoadingView("");
        if (!q.isEmpty())
            managerGitHubDataSource.searchRepository(q, new GitHubDataSource.Callback<Page>() {
                @Override
                public void onLoaded(Page o) {
                    if (o != null)
                        view.showRepository(o.getItems());
                    view.hideLoadingView();
                }


            });

    }

    @Override
    public void openRepositoryDetails(Repository repository) {
        view.openRepositoryDetails(repository);
    }
}
