package dmitroserdun.com.ua.hubviewer.activity.screen.repositoryDetailsContent;

import android.content.SharedPreferences;
import android.util.Log;

import java.util.List;

import dmitroserdun.com.ua.hubviewer.data.model.directory.Directory;
import dmitroserdun.com.ua.hubviewer.data.model.repository.Repository;
import dmitroserdun.com.ua.hubviewer.data.model.user.Owner;
import dmitroserdun.com.ua.hubviewer.repository.ManagerGitHubDataSource;

/**
 * Created by User on 20.08.2017.
 */

public class RepositoryDetailsPresenter implements RepositoryDetailsContract.Presenter {
    private RepositoryDetailsContract.View view;
    private ManagerGitHubDataSource managerGitHubDataSource;
    private SharedPreferences pref;
    private Repository repository;
    private List<Directory> content;

    private String TYPE_DIR = "dir";
    private String TYPE_FILE = "file";

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
    public void loadRepositoryContent(Directory currentContent) {
        if (currentContent == null) {
            loadDirectory("");
            return;
        }

        if (currentContent.getType().equals(TYPE_DIR)) {
            Log.d("clic_next_content", "DIR: ");

            loadDirectory(currentContent.getPath());
        } else {
            Log.d("clic_next_content", "FILE->"+currentContent.getDownloadUrl());
            loadFile(currentContent.getDownloadUrl());
        }

    }

    @Override
    public void loadDetails() {
        managerGitHubDataSource.getDetailsRepositories(repository.getOwner().getLogin(), repository.getName(), repoDetails -> {
                    view.showRepoDetails(repoDetails);
                }, t -> {
                }, () -> {
                }
        );


    }

    @Override
    public List<Directory> getContent() {
        return content;
    }

    private void loadDirectory(String path) {
        managerGitHubDataSource.getContentForDirectory(repository.getOwner().getLogin(), repository.getName(), path, content -> {
            this.content = content;
            view.showContent(content);

        }, t -> {
        }, () -> {
            view.hideLoadingView();

        });
    }

    private void loadFile(String url) {
        managerGitHubDataSource.loadFile(url, data -> {
            Log.d("clic_next_content", "FILE-> good"+data);
            view.showFileDetails(new String(data));
        }, t -> {
        }, () -> {
        });
    }


}
