package dmitroserdun.com.ua.hubviewer.view.screen.usersDetailsTabFragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dmitroserdun.com.ua.hubviewer.R;
import dmitroserdun.com.ua.hubviewer.utils.Injection;
import dmitroserdun.com.ua.hubviewer.view.adapter.ViewPageAdapter;
import dmitroserdun.com.ua.hubviewer.view.screen.overviewFragment.OverviewFragment;
import dmitroserdun.com.ua.hubviewer.view.screen.overviewFragment.presenter.CurrentUserOverviewPresenter;
import dmitroserdun.com.ua.hubviewer.view.screen.overviewFragment.presenter.OtherUserOverviewPresenter;
import dmitroserdun.com.ua.hubviewer.view.screen.repositoryListy.RepositoryListFragment;
import dmitroserdun.com.ua.hubviewer.view.screen.repositoryListy.presenter.UserRepositoryListPresenter;

import static dmitroserdun.com.ua.hubviewer.utils.Constance.TOKEN_KEY;

public class UsersDetailsTabFragment extends Fragment {
    public static final String USER_DETAILS_FRAGMENT_KEY = UsersDetailsTabFragment.class.getName();


    private TabLayout tabLayout;
    private ViewPager viewPager;


    private static final String ARG_PARAM_USERNAME = "username_param";


    // TODO: Rename and change types of parameters
    private String username;


    public UsersDetailsTabFragment() {
        // Required empty public constructor
    }

    public static UsersDetailsTabFragment newInstance(String param_username) {
        UsersDetailsTabFragment fragment = new UsersDetailsTabFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM_USERNAME, param_username);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            username = getArguments().getString(ARG_PARAM_USERNAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_users_details_tab, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);


        setupViewPager(viewPager);
        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPageAdapter adapter = new ViewPageAdapter(getChildFragmentManager());
        OverviewFragment overviewFragment = OverviewFragment.newInstance("", "");
        RepositoryListFragment repositoryListFragment = RepositoryListFragment.newInstance("", "");
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(TOKEN_KEY, Context.MODE_PRIVATE);


        if (username.isEmpty()) {
            new CurrentUserOverviewPresenter(overviewFragment, Injection.provideTasksRepository(getContext()),
                    sharedPreferences);

        } else {
            new OtherUserOverviewPresenter(overviewFragment, Injection.provideTasksRepository(getContext()),
                    username);
        }
        //Appoint an view presenter to work with user repository
        new UserRepositoryListPresenter(repositoryListFragment,
                Injection.provideTasksRepository(getContext()), username, sharedPreferences);

        adapter.addFragment(overviewFragment, getResources().getString(R.string.overview));
        adapter.addFragment(repositoryListFragment, getResources().getString(R.string.repository));

        viewPager.setAdapter(adapter);
    }


}
