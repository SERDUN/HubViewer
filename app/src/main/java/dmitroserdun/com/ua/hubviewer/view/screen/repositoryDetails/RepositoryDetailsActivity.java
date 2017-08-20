package dmitroserdun.com.ua.hubviewer.view.screen.repositoryDetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import dmitroserdun.com.ua.hubviewer.R;
import dmitroserdun.com.ua.hubviewer.data.model.Repository;
import dmitroserdun.com.ua.hubviewer.data.model.RepositoryDetails;
import dmitroserdun.com.ua.hubviewer.utils.Constance;
import dmitroserdun.com.ua.hubviewer.utils.Injection;
import dmitroserdun.com.ua.hubviewer.view.screen.usersDetailsTabFragment.OtherUserDetailsActivity;

import static dmitroserdun.com.ua.hubviewer.utils.Constance.TOKEN_KEY;

public class RepositoryDetailsActivity extends AppCompatActivity implements RepositoryDetailsContract.View {
    private ImageView ivMinAva;
    private TextView tvLogin;
    private TextView tvPath;

    private TextView tvLanguage;
    private TextView tvIss;
    private TextView tvBranch;

    private TextView tvWatch;
    private TextView tvFork;
    private TextView tvProtect;
    private TextView tvDescription;

    View cvRepoDetailUser;


    private RepositoryDetailsContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository_details);
        initView();

        Gson gson = new GsonBuilder().create();
        Repository repository = gson.fromJson(getIntent().getStringExtra(Constance.REPOSITORY_DETAILS), Repository.class);
        new RepositoryDetailsPresenter(this, Injection.provideTasksRepository(this),
                getSharedPreferences(TOKEN_KEY, Context.MODE_PRIVATE),
                repository);
        presenter.loadDetails();
    }

    private void initView() {
        ivMinAva = (ImageView) findViewById(R.id.iv_user_in_repo);
        tvLogin = (TextView) findViewById(R.id.tv_repo_details_login);
        tvPath = (TextView) findViewById(R.id.tv_path_repo_details);

        tvBranch = (TextView) findViewById(R.id.tv_def_branch);
        tvIss = (TextView) findViewById(R.id.tv_repo_details_issues);
        tvLanguage = (TextView) findViewById(R.id.tv_repo_details_language);

        cvRepoDetailUser=findViewById(R.id.cv_repo_details_user);
        tvWatch = (TextView) findViewById(R.id.tv_details_repo_watch);
        tvFork = (TextView) findViewById(R.id.tv_details_repo_fork);
        tvProtect = (TextView) findViewById(R.id.tv_details_repo_prot);
        tvDescription = (TextView) findViewById(R.id.tv_repo_details_description);

        cvRepoDetailUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(),OtherUserDetailsActivity.class);
                intent.putExtra(Constance.OTHER_USER_DETAILS,presenter.getOwner().getLogin());
                startActivity(intent);
            }
        });
    }

    @Override
    public void showLoadingView(String msg) {

    }

    @Override
    public void hideLoadingView() {

    }

    @Override
    public void setPresenter(RepositoryDetailsContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showLoginError() {

    }

    @Override
    public void showMessage(String s) {

    }

    @Override
    public void showPasswordError() {

    }

    @Override
    public void showRepoDetails(RepositoryDetails repositoryDetails) {
        tvLogin.setText(repositoryDetails.getOwner().getLogin());
        tvPath.setText(repositoryDetails.getFullName());

        tvFork.setText(String.valueOf(repositoryDetails.getForks()));
        tvProtect.setText(String.valueOf(repositoryDetails.getPrivate()));
        tvWatch.setText(String.valueOf(repositoryDetails.getWatchers()));
        tvDescription.setText(repositoryDetails.getDescription());

        tvLanguage.setText(String.valueOf(repositoryDetails.getLanguage()));
        tvIss.setText(String.valueOf(repositoryDetails.getOpenIssues()));
        tvBranch.setText(repositoryDetails.getDefaultBranch());

        Picasso.with(this).load(repositoryDetails.getOwner().getAvatarUrl()).into(ivMinAva);

    }
}
