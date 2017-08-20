package dmitroserdun.com.ua.hubviewer.view.screen.repositoryListy;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import dmitroserdun.com.ua.hubviewer.R;
import dmitroserdun.com.ua.hubviewer.data.model.repository.Repository;
import dmitroserdun.com.ua.hubviewer.utils.Constance;
import dmitroserdun.com.ua.hubviewer.view.adapter.RecyclerListAdapter;
import dmitroserdun.com.ua.hubviewer.view.customView.RecyclerViewEmptySupport;
import dmitroserdun.com.ua.hubviewer.view.screen.repositoryDetails.RepositoryDetailsActivity;


public class RepositoryListFragment extends Fragment implements RepositoryListContract.View {
    public static final String REPOSITORY_LIST_FRAGMENT_KEY = RepositoryListFragment.class.getName();


    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;


    private RepositoryListContract.Presenter presenter;
    private RecyclerViewEmptySupport recyclerView;
    private View emptyView;


    public RepositoryListFragment() {
        // Required empty public constructor
    }


    public static RepositoryListFragment newInstance(String param1, String param2) {
        RepositoryListFragment fragment = new RepositoryListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repository_list, container, false);
        initView(view);
        presenter.loadRepository();
        return view;
    }

    //// TODO: 19.08.2017 rewrite us cursor
    private void initView(View view) {
        recyclerView = (RecyclerViewEmptySupport) view.findViewById(R.id.rv_repository);
        emptyView = view.findViewById(R.id.tv_empty);
        recyclerView.setEmptyView(emptyView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerListAdapter adapter= new RecyclerListAdapter(v -> {presenter.openRepositoryDetails(v);},
                new ArrayList<>());
        recyclerView.setAdapter(adapter);

    }


    @Override
    public void showLoadingView(String msg) {

    }

    @Override
    public void hideLoadingView() {

    }

    @Override
    public void setPresenter(RepositoryListContract.Presenter presenter) {
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
    public void showRepository(List<Repository> repositories) {
        ((RecyclerListAdapter) recyclerView.getAdapter()).swapList((ArrayList<Repository>) repositories);
    }

    @Override
    public void openRepositoryDetails(Repository repository) {
        Intent intent = new Intent(getContext(), RepositoryDetailsActivity.class);
        Gson gson = new GsonBuilder().create();
        intent.putExtra(Constance.REPOSITORY_DETAILS, gson.toJson(repository));
        startActivity(intent);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.navigation, menu);

        SearchManager manager = (SearchManager) getContext().getSystemService(Context.SEARCH_SERVICE);

        SearchView search = (SearchView) menu.findItem(R.id.action_search).getActionView();

//        search.setSearchableInfo(manager.getSearchableInfo(getComponentName()));

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("search_sss", "onQueryTextSubmit: " + query);
                return false;
            }

            // TODO: 20.08.2017 FIX bag search
            @Override
            public boolean onQueryTextChange(String newText) {
                presenter.search(newText);
                Log.d("search_sss", "onQueryTextSubmit: " + newText);

                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

}
