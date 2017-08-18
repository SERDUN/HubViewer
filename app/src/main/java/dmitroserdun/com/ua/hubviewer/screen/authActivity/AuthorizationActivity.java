package dmitroserdun.com.ua.hubviewer.screen.authActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import dmitroserdun.com.ua.hubviewer.R;
import dmitroserdun.com.ua.hubviewer.screen.authActivity.AuthorizationContract.View;
import dmitroserdun.com.ua.hubviewer.utils.Injection;

public class AuthorizationActivity extends AppCompatActivity implements View {
    private EditText etLogin;
    private EditText etPassword;
    private Button btnLogIn;
    private AuthorizationContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);
        new AuthorizationPresenter(this, Injection.provideTasksRepository(this));
        initView();
    }

    private void initView() {
        etLogin = (EditText) findViewById(R.id.et_login);
        etPassword = (EditText) findViewById(R.id.et_password);
        btnLogIn = (Button) findViewById(R.id.btn_login);
        btnLogIn.setOnClickListener(v -> {
            presenter.logIn(etLogin.getText().toString(), etPassword.getText().toString());
        });
    }

    @Override
    public void setPresenter(AuthorizationContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void openRepositories() {

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
}
