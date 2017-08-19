package dmitroserdun.com.ua.hubviewer.view.screen.overviewFragment;

import android.content.SharedPreferences;

import dmitroserdun.com.ua.hubviewer.data.model.User;
import dmitroserdun.com.ua.hubviewer.repository.GitHubDataSource;
import dmitroserdun.com.ua.hubviewer.repository.ManagerGitHubDataSource;

import static dmitroserdun.com.ua.hubviewer.utils.Constance.CURRENT_FULL_NAME;
import static dmitroserdun.com.ua.hubviewer.utils.Constance.CURRENT_TOKEN_KEY;
import static dmitroserdun.com.ua.hubviewer.utils.Constance.CURRENT_USERNAME;

/**
 * Created by User on 19.08.2017.
 */

public class OverviewPresenter implements OverviewContract.Presenter {
    private OverviewContract.View view;
    private ManagerGitHubDataSource managerGitHubDataSource;
    private SharedPreferences pref;

    public OverviewPresenter(OverviewContract.View view,
                             ManagerGitHubDataSource managerGitHubDataSource,
                             SharedPreferences pref) {
        this.view = view;
        view.setPresenter(this);//register view
        this.managerGitHubDataSource = managerGitHubDataSource;
        this.pref = pref;
    }

    @Override
    public void loadData() {
        managerGitHubDataSource.refreshLocalData();
        managerGitHubDataSource.getCurrentUser(getToken(), new GitHubDataSource.Callback<User>() {
            @Override
            public void onLoaded(User user) {
                view.showOverviewData(user);
                saveLoginUserForRequest(user);
            }

            @Override
            public void onFailure(String e) {

            }
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
