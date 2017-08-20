package dmitroserdun.com.ua.hubviewer.view.screen.repositoryDetails;

import android.content.SharedPreferences;
import android.util.Log;

import dmitroserdun.com.ua.hubviewer.data.model.user.Owner;
import dmitroserdun.com.ua.hubviewer.data.model.repository.Repository;
import dmitroserdun.com.ua.hubviewer.repository.ManagerGitHubDataSource;

/**
 * Created by User on 20.08.2017.
 */

public class RepositoryDetailsPresenter implements RepositoryDetailsContract.Presenter {
    private RepositoryDetailsContract.View view;
    private ManagerGitHubDataSource managerGitHubDataSource;
    private SharedPreferences pref;
    private Repository repository;

    public RepositoryDetailsPresenter(RepositoryDetailsContract.View view,
                                      ManagerGitHubDataSource managerGitHubDataSource,
                                      SharedPreferences pref, Repository repository) {
        this.view = view;
        view.setPresenter(this);//register view
        this.managerGitHubDataSource = managerGitHubDataSource;
        this.pref = pref;
        this.repository = repository;
    }

    @Override
    public Owner getOwner() {
        return repository.getOwner();
    }

    @Override
    public void loadRepositoryContent(String path) {
        managerGitHubDataSource.getContentForDirectory(repository.getOwner().getLogin(), repository.getName(), path, content -> {
            view.showContent(content);
            Log.d("content", "loadRepositoryContent: "+content.toString());
        });
    }

    @Override
    public void loadDetails() {
        managerGitHubDataSource.getDetailsRepositories(repository.getOwner().getLogin(), repository.getName(), repository -> {
                    view.showRepoDetails(repository);
                }
        );

    }


}
