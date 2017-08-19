package dmitroserdun.com.ua.hubviewer.view.screen.repositoryListy;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import dmitroserdun.com.ua.hubviewer.R;
import dmitroserdun.com.ua.hubviewer.data.model.Repository;
import dmitroserdun.com.ua.hubviewer.utils.Injection;
import dmitroserdun.com.ua.hubviewer.view.adapter.RecyclerListAdapter;

import static dmitroserdun.com.ua.hubviewer.utils.Constance.TOKEN_KEY;


public class RepositoryListFragment extends Fragment implements RepositoryListContract.View {
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private RepositoryListContract.Presenter presenter;
    private RecyclerView recyclerView;


    private OnFragmentInteractionListener mListener;

    public RepositoryListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RepositoryListFragment.
     */
    // TODO: Rename and change types and number of parameters
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repository_list, container, false);
        initView(view);
        new RepositoryListPresenter(this, Injection.provideTasksRepository(getContext()),
                getContext().getSharedPreferences(TOKEN_KEY, Context.MODE_PRIVATE));
        presenter.loadRepository();
        return view;
    }

    //// TODO: 19.08.2017 rewrite us cursor
    private void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_repository);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new RecyclerListAdapter(v -> {
        }, new ArrayList<Repository>()));

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

        ( (RecyclerListAdapter)recyclerView.getAdapter()).swapList((ArrayList<Repository>) repositories);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
