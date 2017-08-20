package dmitroserdun.com.ua.hubviewer.view.screen.contentRepository;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import dmitroserdun.com.ua.hubviewer.R;
import dmitroserdun.com.ua.hubviewer.data.model.directory.Directory;
import dmitroserdun.com.ua.hubviewer.view.adapter.ContentListAdapter;


public class ContentRepositoryFragment extends Fragment implements CententRepositoryContract.View {


    public interface CallbackOpenDir {
        public void openDirectory(Directory directory);
    }

    public static final String CONTENT_REPOSITORY_FRAGMENT_KEY = ContentRepositoryFragment.class.getName();
    private CallbackOpenDir callback;
    private static final String ARG_PARAM_CONTENT = "arg_param_content";
    private RecyclerView recyclerView;


    private List<Directory> content;


    public ContentRepositoryFragment() {
    }


    public static ContentRepositoryFragment newInstance(String content) {
        ContentRepositoryFragment fragment = new ContentRepositoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM_CONTENT, content);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            content = new GsonBuilder().create().fromJson(getArguments().getString(ARG_PARAM_CONTENT),
                    new TypeToken<List<Directory>>() {
                    }.getType());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_struct_repository, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_content);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ContentListAdapter adapter = new ContentListAdapter(v ->
                callback.openDirectory(v), content);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CallbackOpenDir) {
            callback = (CallbackOpenDir) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement CallbackOpenDir");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }

    @Override
    public void showLoadingView(String msg) {

    }

    @Override
    public void hideLoadingView() {

    }

    @Override
    public void setPresenter(CententRepositoryContract.Presenter presenter) {

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
}






