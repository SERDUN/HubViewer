package dmitroserdun.com.ua.hubviewer.screen.authActivity;

import android.content.SharedPreferences;

import java.io.IOException;

import dmitroserdun.com.ua.hubviewer.data.Authorization;
import dmitroserdun.com.ua.hubviewer.network.TokenConnector;
import dmitroserdun.com.ua.hubviewer.repository.GitHubDataSource;
import dmitroserdun.com.ua.hubviewer.repository.ManagerGitHubDataSource;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static dmitroserdun.com.ua.hubviewer.utils.Constance.CURRENT_TOKEN_KEY;

/**
 * Created by User on 17.08.2017.
 */

public class AuthorizationPresenter implements AuthorizationContract.Presenter {
    private AuthorizationContract.View view;
    private ManagerGitHubDataSource managerGitHubDataSource;
    private SharedPreferences pref;

    public AuthorizationPresenter(AuthorizationContract.View view,
                                  ManagerGitHubDataSource managerGitHubDataSource,
                                  SharedPreferences pref) {
        this.view = view;
        view.setPresenter(this);//register view
        this.managerGitHubDataSource = managerGitHubDataSource;
        this.pref = pref;
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
            view.showLoadingView("Authentification");
            managerGitHubDataSource.authentication(login, password, new GitHubDataSource.Callback<Authorization>() {
                @Override
                public void onLoaded(Authorization o) {
                    if (o != null) {
                        pref.edit().putString(CURRENT_TOKEN_KEY, o.getToken()).apply();
                        view.hideLoadingView();
                        view.openProfile();
                    } else {
                        notifyUser("Incorrect identification data");
                    }
                }

                @Override
                public void onFailure(String e) {
                    notifyUser("Network error");
                }
            });
        }

    }

    @Override
    public void logIn() {
        view.showWebView();
    }

    @Override
    public void applyTokenByCode(String code) {
        final String ACCESS_TOKEN = "access_token=";
        TokenConnector.getToken(code, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseString = response.body().string();
                String token = responseString.substring(ACCESS_TOKEN.length(),responseString.indexOf("&"));
                if (response != null) {
                    pref.edit().putString(CURRENT_TOKEN_KEY, token).apply();
                    view.hideLoadingView();
                    view.openProfile();
                }
            }
        });

    }

    private void notifyUser(String msg) {
        view.hideLoadingView();
        view.showMessage(msg);
    }
}

//
//    public void saveToken(String token) {
//    }
//
//    public String getSavedToken() {
//        return preferences.getString(CURRENT_TOKEN_KEY, "");
//
//    }