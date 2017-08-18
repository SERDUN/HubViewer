package dmitroserdun.com.ua.hubviewer.utils;

import android.support.annotation.NonNull;
import android.util.Base64;

import com.google.gson.JsonObject;

import dmitroserdun.com.ua.hubviewer.BuildConfig;

/**
 * Created by User on 17.08.2017.
 */

public class BasicAuthUtils {
    private final static String BASIC_AUTHORIZATION = "Basic ";
    private final static String CLIENT_ID = "client_id";
    private final static String CLIENT_SECRET = "client_secret";

    @NonNull
    public static String generateAuthorizationString(@NonNull String login, @NonNull String password) {
        return BASIC_AUTHORIZATION + Base64.encodeToString((login + ":" + password).getBytes(), Base64.DEFAULT).trim();
    }

    @NonNull
    public static JsonObject createAuthorizationParam() {
        JsonObject param = new JsonObject();
        param.addProperty(CLIENT_ID, BuildConfig.CLIENT_ID);
        param.addProperty(CLIENT_SECRET, BuildConfig.CLIENT_SECRET);
        return param;
    }

}
