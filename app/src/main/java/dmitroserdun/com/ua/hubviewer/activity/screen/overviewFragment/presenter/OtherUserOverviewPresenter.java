package dmitroserdun.com.ua.hubviewer.activity.screen.overviewFragment.presenter;

import dmitroserdun.com.ua.hubviewer.R;
import dmitroserdun.com.ua.hubviewer.repository.ManagerGitHubDataSource;
import dmitroserdun.com.ua.hubviewer.activity.screen.overviewFragment.OverviewContract;

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
        view.showLoadingView("");
        managerGitHubDataSource.refreshLocalData();
        managerGitHubDataSource.getUser(username, user -> {
            view.showOverviewData(user);

            managerGitHubDataSource.getUserEvents(user.getLogin(), events -> {
                view.showUserEvents(events);

            }, t -> {

            }, () -> {
            });

        }, t -> {
            view.showMessage(R.string.unknown_host);

        }, () -> {
            view.hideLoadingView();

        });

    }


}
