package dmitroserdun.com.ua.hubviewer.view.customView;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import dmitroserdun.com.ua.hubviewer.network.NetworkStateReceiver;

/**
 * Created by User on 22.08.2017.
 */

public abstract class FragmentConnectionVerification extends Fragment implements NetworkStateReceiver.NetworkStateReceiverListener {


    private NetworkStateReceiver networkStateReceiver;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        initListens();
        super.onCreate(savedInstanceState);
    }

    private void initListens() {
        networkStateReceiver = new NetworkStateReceiver();
        networkStateReceiver.addListener(this);
        this.getContext().registerReceiver(networkStateReceiver,
                new IntentFilter(android.net.ConnectivityManager.CONNECTIVITY_ACTION));

    }

    private NoConnectionDialog noConnectionDialog;

    @Override
    public void networkAvailable() {
        if (noConnectionDialog != null)
            noConnectionDialog.dismiss();
        restorationAccessInternet();

    }

    @Override
    public void networkUnavailable() {
        noConnectionDialog = NoConnectionDialog.newInstance();
        noConnectionDialog.show(getChildFragmentManager(), "no_connection");

    }

    public abstract void restorationAccessInternet();

    @Override
    public void onDestroy() {
        networkStateReceiver.removeListener(this);
        super.onDestroy();
    }
}
