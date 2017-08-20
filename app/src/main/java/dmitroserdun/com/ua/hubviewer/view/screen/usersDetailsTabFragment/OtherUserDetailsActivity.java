package dmitroserdun.com.ua.hubviewer.view.screen.usersDetailsTabFragment;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dmitroserdun.com.ua.hubviewer.R;
import dmitroserdun.com.ua.hubviewer.utils.Constance;

public class OtherUserDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_user_details);
        UsersDetailsTabFragment usersDetailsTabFragment = UsersDetailsTabFragment.newInstance(getIntent().getStringExtra(Constance.OTHER_USER_DETAILS));
        addFragment(usersDetailsTabFragment, UsersDetailsTabFragment.USER_DETAILS_FRAGMENT_KEY);
    }

    private void addFragment(Fragment fragment, String fragmentKey) {
        if (getSupportFragmentManager().findFragmentByTag(fragmentKey) == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.other_user_container, fragment, fragmentKey)
                    .commit();
        }
    }

}
