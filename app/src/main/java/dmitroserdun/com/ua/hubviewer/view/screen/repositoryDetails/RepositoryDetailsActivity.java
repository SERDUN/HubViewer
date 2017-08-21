package dmitroserdun.com.ua.hubviewer.view.screen.repositoryDetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.util.List;

import dmitroserdun.com.ua.hubviewer.R;
import dmitroserdun.com.ua.hubviewer.data.model.directory.Directory;
import dmitroserdun.com.ua.hubviewer.data.model.repository.Repository;
import dmitroserdun.com.ua.hubviewer.data.model.repository.RepositoryDetails;
import dmitroserdun.com.ua.hubviewer.utils.Constance;
import dmitroserdun.com.ua.hubviewer.utils.Injection;
import dmitroserdun.com.ua.hubviewer.view.customView.LoadingDialog;
import dmitroserdun.com.ua.hubviewer.view.screen.LoadingView;
import dmitroserdun.com.ua.hubviewer.view.screen.containers.OtherUserDetailsActivity;
import dmitroserdun.com.ua.hubviewer.view.screen.contentRepository.ContentRepositoryFragment;

import static dmitroserdun.com.ua.hubviewer.utils.Constance.TOKEN_KEY;

public class RepositoryDetailsActivity extends AppCompatActivity implements RepositoryDetailsContract.View,
        ContentRepositoryFragment.CallbackOpenDir {

    public final String CONTENT_BACK_STACK_KEY = "fragment_back_content_key";

    private ImageView ivMinAva;
    private ImageView ivContentBack;
    private TextView tvLogin;
    private TextView tvPath;

    private TextView tvLanguage;
    private TextView tvIss;
    private TextView tvBranch;

    private TextView tvWatch;
    private TextView tvFork;
    private TextView tvProtect;
    private TextView tvDescription;

    private View cvRepoDetailUser;
    private LoadingView loadingView;


    private RepositoryDetailsContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository_details);
        initView();
        loadingView.showLoadingView("loading repo");
        initBackStack();
        Gson gson = new GsonBuilder().create();
        Repository repository = gson.fromJson(getIntent().getStringExtra(Constance.REPOSITORY_DETAILS), Repository.class);
        new RepositoryDetailsPresenter(this, Injection.provideTasksRepository(this),
                getSharedPreferences(TOKEN_KEY, Context.MODE_PRIVATE),
                repository);
        presenter.loadDetails();
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_repo_detail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        loadingView = LoadingDialog.view(getSupportFragmentManager());

        ivMinAva = (ImageView) findViewById(R.id.iv_user_in_repo);
        ivContentBack = (ImageView) findViewById(R.id.iv_content_back);
        tvLogin = (TextView) findViewById(R.id.tv_repo_details_login);
        tvPath = (TextView) findViewById(R.id.tv_path_repo_details);

        tvBranch = (TextView) findViewById(R.id.tv_def_branch);
        tvIss = (TextView) findViewById(R.id.tv_repo_details_issues);
        tvLanguage = (TextView) findViewById(R.id.tv_repo_details_language);

        cvRepoDetailUser = findViewById(R.id.cv_repo_details_user);
        tvWatch = (TextView) findViewById(R.id.tv_details_repo_watch);
        tvFork = (TextView) findViewById(R.id.tv_details_repo_fork);
        tvProtect = (TextView) findViewById(R.id.tv_details_repo_prot);
        tvDescription = (TextView) findViewById(R.id.tv_repo_details_description);

        cvRepoDetailUser.setOnClickListener(v -> {
            Intent intent = new Intent(getBaseContext(), OtherUserDetailsActivity.class);
            intent.putExtra(Constance.OTHER_USER_DETAILS, presenter.getOwner().getLogin());
            startActivity(intent);
        });
        ivContentBack.setOnClickListener(v -> {
            getSupportFragmentManager().popBackStack();

        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }

    private void initBackStack() {
        getSupportFragmentManager().addOnBackStackChangedListener(() -> {
                    if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
                        finish();
                    }
                    if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                        Fragment fragment = getSupportFragmentManager()
                                .findFragmentById(R.id.container_content_repo);

                        getSupportFragmentManager().beginTransaction()
                                .show(fragment)
                                .commit();
                    }

                }
        );
    }

    @Override
    public void showLoadingView(String msg) {
        loadingView.showLoadingView(msg);
    }

    @Override
    public void hideLoadingView() {
        loadingView.hideLoadingView();
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
    public void showContent(List<Directory> directory) {
        addFragment(ContentRepositoryFragment.newInstance(new GsonBuilder().create().toJson(directory)),
                ContentRepositoryFragment.CONTENT_REPOSITORY_FRAGMENT_KEY);

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
        presenter.loadRepositoryContent("");

    }

    private void addFragment(Fragment fragment, String fragmentKey) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container_content_repo, fragment, fragmentKey)
                .addToBackStack(CONTENT_BACK_STACK_KEY)
                .commit();
    }


    @Override
    public void openDirectory(Directory directory) {
        getSupportFragmentManager().beginTransaction().hide(getSupportFragmentManager()
                .findFragmentById(R.id.container_content_repo)).commit();


        presenter.loadRepositoryContent(directory.getPath());
    }
}
