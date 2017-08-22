package dmitroserdun.com.ua.hubviewer.activity.screen.overviewFragment.presenter;

import android.content.SharedPreferences;

import dmitroserdun.com.ua.hubviewer.R;
import dmitroserdun.com.ua.hubviewer.data.model.user.User;
import dmitroserdun.com.ua.hubviewer.repository.ManagerGitHubDataSource;
import dmitroserdun.com.ua.hubviewer.activity.screen.overviewFragment.OverviewContract;

import static dmitroserdun.com.ua.hubviewer.utils.Constance.CURRENT_FULL_NAME;
import static dmitroserdun.com.ua.hubviewer.utils.Constance.CURRENT_TOKEN_KEY;
import static dmitroserdun.com.ua.hubviewer.utils.Constance.CURRENT_USERNAME;

/**
 * Created by User on 19.08.2017.
 */

public class CurrentUserOverviewPresenter implements OverviewContract.Presenter {
    private OverviewContract.View view;
    private ManagerGitHubDataSource managerGitHubDataSource;
    private SharedPreferences pref;

    public CurrentUserOverviewPresenter(OverviewContract.View view,
                                        ManagerGitHubDataSource managerGitHubDataSource,
                                        SharedPreferences pref) {
        this.view = view;
        view.setPresenter(this);//register view
        this.managerGitHubDataSource = managerGitHubDataSource;
        this.pref = pref;
    }

    @Override
    public void loadData() {
        view.showLoadingView("");
        managerGitHubDataSource.refreshLocalData();
        managerGitHubDataSource.getCurrentUser(getToken(), user -> {
            view.showOverviewData(user);
            saveLoginUserForRequest(user);

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


    private String getToken() {
        return pref.getString(CURRENT_TOKEN_KEY, "");

    }

    private void saveLoginUserForRequest(User user) {
        pref.edit().putString(CURRENT_USERNAME, user.getLogin()).apply();
        pref.edit().putString(CURRENT_FULL_NAME, user.getName()).apply();
    }


}
