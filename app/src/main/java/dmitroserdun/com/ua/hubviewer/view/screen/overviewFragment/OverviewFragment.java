package dmitroserdun.com.ua.hubviewer.view.screen.overviewFragment;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import dmitroserdun.com.ua.hubviewer.R;
import dmitroserdun.com.ua.hubviewer.data.model.events.Event;
import dmitroserdun.com.ua.hubviewer.data.model.user.User;
import dmitroserdun.com.ua.hubviewer.view.adapter.EventsListAdapter;
import dmitroserdun.com.ua.hubviewer.view.customView.screenConnectionVerification.FragmentConnectionVerification;
import dmitroserdun.com.ua.hubviewer.view.customView.LoadingDialog;
import dmitroserdun.com.ua.hubviewer.view.screen.LoadingView;


public class OverviewFragment extends FragmentConnectionVerification implements OverviewContract.View{
    public static final String OVERVIEW_FRAGMENT_KEY = OverviewFragment.class.getName();

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ImageView ivAvatar;
    private TextView tvFullName;
    private TextView tvLogin;

    private TextView tvWork;
    private TextView tvLocation;
    private TextView tvEmail;

    private TextView tvCountRepository;
    private TextView tvCountFollowing;
    private TextView tvCountFollowers;


    private String mParam1;
    private String mParam2;

    private OverviewContract.Presenter presenter;
    private RecyclerView recyclerView;
    private LoadingView loadingView;

    private ActionBar toolbar;

    public OverviewFragment() {
        // Required empty public constructor
    }


    public static OverviewFragment newInstance(String param1, String param2) {
        OverviewFragment fragment = new OverviewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void restorationAccessInternet() {
        presenter.loadData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_overview, container, false);
        initView(view);
        presenter.loadData();
        return view;
    }



    private void initView(View view) {
        toolbar = ((AppCompatActivity) getActivity()).getSupportActionBar();

        loadingView = LoadingDialog.view(getChildFragmentManager());
        ivAvatar = (ImageView) view.findViewById(R.id.iv_avatar);
        tvFullName = (TextView) view.findViewById(R.id.tv_full_name);
        tvLogin = (TextView) view.findViewById(R.id.tv_login);

        tvEmail = (TextView) view.findViewById(R.id.tv_email);
        tvWork = (TextView) view.findViewById(R.id.tv_work);
        tvLocation = (TextView) view.findViewById(R.id.tv_location);

        tvCountRepository = (TextView) view.findViewById(R.id.tv_count_repository);
        tvCountFollowers = (TextView) view.findViewById(R.id.tv_count_followers);
        tvCountFollowing = (TextView) view.findViewById(R.id.tv_count_following);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_events);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        EventsListAdapter adapter = new EventsListAdapter(v -> {
        }, new ArrayList<>());
        recyclerView.setAdapter(adapter);

    }


    @Override
    public void showLoadingView(String msg) {
        loadingView.showLoadingView("Loading Overview");
    }

    @Override
    public void hideLoadingView() {
        loadingView.hideLoadingView();
    }

    @Override
    public void setPresenter(OverviewContract.Presenter presenter) {
        this.presenter = presenter;

    }

    @Override
    public void showLoginError() {

    }

    @Override
    public void showMessage(int msg) {

        Toast.makeText(getContext(), getString(msg), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showPasswordError() {

    }

    @Override
    public void showOverviewData(User user) {
        if (toolbar != null) toolbar.setTitle(user.getName());
        tvWork.setText(user.getCompany());
        tvEmail.setText(user.getEmail());
        tvLocation.setText(user.getLocation());

        tvCountRepository.setText(String.valueOf(user.getPublicRepos()));
        tvCountFollowers.setText(String.valueOf(user.getFollowers()));
        tvCountFollowing.setText(String.valueOf(user.getFollowing()));
        tvFullName.setFocusable(true);

        tvLogin.setText(user.getLogin());
        tvFullName.setText(user.getName());
        Picasso.with(getContext()).load(user.getAvatarUrl()).into(ivAvatar);


    }

    @Override
    public void showUserEvents(List<Event> events) {
        ((EventsListAdapter) recyclerView.getAdapter()).swapList((ArrayList<Event>) events);
    }






}
