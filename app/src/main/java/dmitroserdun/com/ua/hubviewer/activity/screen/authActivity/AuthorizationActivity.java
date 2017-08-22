package dmitroserdun.com.ua.hubviewer.activity.screen.authActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import dmitroserdun.com.ua.hubviewer.R;
import dmitroserdun.com.ua.hubviewer.activity.screen.LoadingView;
import dmitroserdun.com.ua.hubviewer.activity.screen.authActivity.AuthorizationContract.View;
import dmitroserdun.com.ua.hubviewer.activity.screen.containers.NavigationActivity;
import dmitroserdun.com.ua.hubviewer.utils.Injection;
import dmitroserdun.com.ua.hubviewer.utils.OauthUtils;
import dmitroserdun.com.ua.hubviewer.activity.customView.LoadingDialog;

import static dmitroserdun.com.ua.hubviewer.utils.Constance.TOKEN_KEY;

public class AuthorizationActivity extends AppCompatActivity implements View {
    private final String TAG = "AuthActivity";
    private WebView webView;
    private AuthorizationContract.Presenter presenter;
    private LoadingView loadingView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);
        new AuthorizationPresenter(this, Injection.provideTasksRepository(this),
                getSharedPreferences(TOKEN_KEY, Context.MODE_PRIVATE));
        initView();
        presenter.checkAuthorization();
    }

    private void initView() {
        loadingView = LoadingDialog.view(getSupportFragmentManager());
        webView = (WebView) findViewById(R.id.wvOauth);
        webView.setWebViewClient(new MyCustomWebViewClient());
        webView.clearCache(true);
        webView.clearHistory();
        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);


    }

    @Override
    public void setPresenter(AuthorizationContract.Presenter presenter) {
        this.presenter = presenter;
    }


    @Override
    public void openOverview() {
        startActivity(new Intent(this, NavigationActivity.class));
        finish();
    }

    @Override
    public void showWebView() {
        webView.loadUrl(OauthUtils.getAuthUrl());

    }

    @Override
    public void showLoginError() {

    }

    @Override
    public void showMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showPasswordError() {

    }

    @Override
    public void showLoadingView(String msg) {
        loadingView.showLoadingView(msg);

    }

    @Override
    public void hideLoadingView() {
        loadingView.hideLoadingView();

    }

    private class MyCustomWebViewClient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            if (!url.contains("app://callback?code="))
                hideLoadingView();
            super.onPageFinished(view, url);
        }
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            showLoadingView("");
            if (url.contains(OauthUtils.ACCESS_CODE)) {
                String code = url.substring(url.indexOf(OauthUtils.ACCESS_CODE) + OauthUtils.ACCESS_CODE.length());
                presenter.applyTokenByCode(code);
                webView.stopLoading();

            }
        }


    }
}
