package dmitroserdun.com.ua.hubviewer.view.customView;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;

import dmitroserdun.com.ua.hubviewer.R;

/**
 * Created by User on 22.08.2017.
 */

public class NoConnectionDialog extends DialogFragment {
    public static NoConnectionDialog newInstance() {
        NoConnectionDialog fragment = new NoConnectionDialog();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, getTheme());
        setCancelable(false);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.dialog_no_connection, null);
        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .create();
    }
}
