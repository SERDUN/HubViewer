package dmitroserdun.com.ua.hubviewer.activity.screen.repositoryDetailsFile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import dmitroserdun.com.ua.hubviewer.R;

public class DetailsFileFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    private String mParam1;
    private TextView tvFileDetails;

    public DetailsFileFragment() {
        // Required empty public constructor
    }

    public static DetailsFileFragment newInstance(String text) {
        DetailsFileFragment fragment = new DetailsFileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, text);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details_file, container, false);
        tvFileDetails = (TextView) view.findViewById(R.id.tv_file_details);
        tvFileDetails.setText(mParam1);
        return view;
    }


}
