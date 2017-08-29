package dmitroserdun.com.ua.hubviewer.activity.screen.containers;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import dmitroserdun.com.ua.hubviewer.R;
import dmitroserdun.com.ua.hubviewer.network.GitGubNetworkFactory;
import dmitroserdun.com.ua.hubviewer.utils.Constance;
import dmitroserdun.com.ua.hubviewer.utils.Injection;
import dmitroserdun.com.ua.hubviewer.activity.screen.repositoryList.RepositoryListFragment;
import dmitroserdun.com.ua.hubviewer.activity.screen.repositoryList.presenter.GitGubSearchPresenter;

import static dmitroserdun.com.ua.hubviewer.utils.Constance.TOKEN_KEY;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
    public static final String TAG = "NavigationActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: " + getSharedPreferences(Constance.TOKEN_KEY, Context.MODE_PRIVATE).getString(Constance.CURRENT_TOKEN_KEY, ""));
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SharedPreferences pref = getSharedPreferences(TOKEN_KEY, Context.MODE_PRIVATE);
        getSupportActionBar().setTitle(pref.getString(Constance.CURRENT_FULL_NAME, ""));

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = LayoutInflater.from(this).inflate(R.layout.nav_header_navigation, null);
        navigationView.addHeaderView(headerView);
        navigationView.getHeaderView(0).setVisibility(View.GONE);

        addFragment(UsersDetailsTabFragment.newInstance(""), UsersDetailsTabFragment.USER_DETAILS_FRAGMENT_KEY);

        String token = getSharedPreferences(Constance.TOKEN_KEY, Context.MODE_PRIVATE).getString(Constance.CURRENT_TOKEN_KEY, "");
        if (!token.isEmpty()) {
            GitGubNetworkFactory.setInterceptorAccessToken(token);
        }

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // TODO: 19.08.2017 clean code
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.nav_overview:
                addFragment(UsersDetailsTabFragment.newInstance(""), UsersDetailsTabFragment.USER_DETAILS_FRAGMENT_KEY);
                break;
            case R.id.nav_search:
                fragment = RepositoryListFragment.newInstance("", "");
                new GitGubSearchPresenter((RepositoryListFragment) fragment,
                        Injection.provideTasksRepository(this), getSharedPreferences(TOKEN_KEY, Context.MODE_PRIVATE));
                addFragment(fragment, RepositoryListFragment.REPOSITORY_LIST_FRAGMENT_KEY);
                break;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void addFragment(Fragment fragment, String fragmentKey) {
        if (getSupportFragmentManager().findFragmentByTag(fragmentKey) == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fragment, fragmentKey)
                    .commit();
        }
    }

}
