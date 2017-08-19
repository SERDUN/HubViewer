package dmitroserdun.com.ua.hubviewer.screen.usersDetailsTabFragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dmitroserdun.com.ua.hubviewer.R;
import dmitroserdun.com.ua.hubviewer.screen.overviewFragment.OverviewFragment;
import dmitroserdun.com.ua.hubviewer.screen.repositoryListy.RepositoryListFragment;

public class UsersDetailsTabFragment extends Fragment {


    private TabLayout tabLayout;
    private ViewPager viewPager;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public UsersDetailsTabFragment() {
        // Required empty public constructor
    }

    //
    // TODO: Rename and change types and number of parameters
    public static UsersDetailsTabFragment newInstance(String param1, String param2) {
        UsersDetailsTabFragment fragment = new UsersDetailsTabFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_users_details_tab, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        viewPager = (ViewPager)view.findViewById(R.id.viewpager);


        setupViewPager(viewPager);
        tabLayout = (TabLayout)view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPageAdapter adapter = new ViewPageAdapter(getChildFragmentManager());
        adapter.addFragment(new OverviewFragment(), getResources().getString(R.string.overview));
        adapter.addFragment(new RepositoryListFragment(),  getResources().getString(R.string.repository));
        viewPager.setAdapter(adapter);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}
