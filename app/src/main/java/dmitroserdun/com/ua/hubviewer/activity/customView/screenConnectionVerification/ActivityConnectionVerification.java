package dmitroserdun.com.ua.hubviewer.activity.customView.screenConnectionVerification;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import dmitroserdun.com.ua.hubviewer.network.NetworkStateReceiver;
import dmitroserdun.com.ua.hubviewer.activity.customView.NoConnectionDialog;

/**
 * Created by User on 22.08.2017.
 */

public abstract  class ActivityConnectionVerification extends AppCompatActivity implements NetworkStateReceiver.NetworkStateReceiverListener {
    private NetworkStateReceiver networkStateReceiver;
    private final String TAG_FRAGMENT_DIALOG="no_connection";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        initListens();
        super.onCreate(savedInstanceState);
    }

    private void initListens() {
        networkStateReceiver = new NetworkStateReceiver();
        networkStateReceiver.addListener(this);
        registerReceiver(networkStateReceiver,
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
        noConnectionDialog.show(getSupportFragmentManager(), TAG_FRAGMENT_DIALOG);

    }

    public abstract void restorationAccessInternet();


    @Override
    public void onPause() {
        networkStateReceiver.removeListener(this);
        super.onPause();
    }

}
