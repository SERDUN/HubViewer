package dmitroserdun.com.ua.hubviewer.view.screen.overviewFragment.presenter;

import dmitroserdun.com.ua.hubviewer.data.model.User;
import dmitroserdun.com.ua.hubviewer.repository.GitHubDataSource;
import dmitroserdun.com.ua.hubviewer.repository.ManagerGitHubDataSource;
import dmitroserdun.com.ua.hubviewer.view.screen.overviewFragment.OverviewContract;

/**
 * Created by User on 20.08.2017.
 */

public class OtherUserOverviewPresenter implements OverviewContract.Presenter {
    private OverviewContract.View view;
    private ManagerGitHubDataSource managerGitHubDataSource;
    private String username;

    public OtherUserOverviewPresenter(OverviewContract.View view,
                                      ManagerGitHubDataSource managerGitHubDataSource,
                                      String username) {
        this.view = view;
        view.setPresenter(this);//register view
        this.managerGitHubDataSource = managerGitHubDataSource;
        this.username = username;
    }

    @Override
    public void loadData() {
        managerGitHubDataSource.getUser(username, user-> {
                view.showOverviewData(user);
            });

    }


}
