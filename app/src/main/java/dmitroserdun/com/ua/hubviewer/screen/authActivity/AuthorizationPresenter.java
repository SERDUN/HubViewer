package dmitroserdun.com.ua.hubviewer.screen.authActivity;

import dmitroserdun.com.ua.hubviewer.data.Authorization;
import dmitroserdun.com.ua.hubviewer.repository.GitHubDataSource;
import dmitroserdun.com.ua.hubviewer.repository.ManagerGitHubDataSource;

/**
 * Created by User on 17.08.2017.
 */

public class AuthorizationPresenter implements AuthorizationContract.Presenter {
    private AuthorizationContract.View view;
    private ManagerGitHubDataSource managerGitHubDataSource;

    public AuthorizationPresenter(AuthorizationContract.View view, ManagerGitHubDataSource managerGitHubDataSource) {
        this.view = view;
        view.setPresenter(this);//register view
        this.managerGitHubDataSource = managerGitHubDataSource;
    }

    @Override
    public void init() {

    }

    @Override
    public void logIn(String login, String password) {
        if (login.isEmpty()) {
            view.showLoginError();
        } else if (password.isEmpty()) {
            view.showPasswordError();
        } else {
            managerGitHubDataSource.authentication(login, password, new GitHubDataSource.Callback<Authorization>() {
                @Override
                public void onLoaded(Authorization o) {
                    Authorization authorization = o;
                    int d=4;
                }

                @Override
                public void onFailure(String e) {
                    String f = e;
                    int f3=3;
                }
            });
        }

    }
}
