package dmitroserdun.com.ua.hubviewer.utils;

import dmitroserdun.com.ua.hubviewer.BuildConfig;

/**
 * Created by User on 18.08.2017.
 */

public class OauthUtils {
    public static final String CALL_URI = "app://callback";
    public static final String PATH_AUTHORIZE = "/login/oauth/authorize";
    public static final String PATH_TOKEN = "/login/oauth/access_token";
    private static final String CLIENT_ID = "client_id=";
    private static final String CLIENT_SECRET = "client_secret=";
    private static final String REDIRECT_URI = "redirect_uri=";
    public static final String ACCESS_CODE = "code=";
    private static final String SCOPE = "scope=";
    private static final String AND_PARAMETRS = "?";
    private static final String AND = "&";


    public static String getAuthUrl() {
        StringBuilder baseUrl = new StringBuilder(BuildConfig.BASE_URL + PATH_AUTHORIZE);
        baseUrl.append(AND_PARAMETRS)
                .append(CLIENT_ID)
                .append(BuildConfig.CLIENT_ID)
                .append(AND)
                .append(REDIRECT_URI)
                .append(CALL_URI)
                .append(AND)
                .append(SCOPE)
                .append("repo")
                .append(AND)
                .append("allow_signup=")
                .append("false");
        return baseUrl.toString();
    }

    public static String getUrlForToken() {
        return BuildConfig.BASE_URL + PATH_TOKEN;
    }

    public static String getParameterForToken(String code) {
        StringBuilder url = new StringBuilder();
        url.append(CLIENT_SECRET)
                .append(BuildConfig.CLIENT_SECRET)
                .append(AND)
                .append(CLIENT_ID)
                .append(BuildConfig.CLIENT_ID)
                .append(AND)
                .append(REDIRECT_URI)
                .append(REDIRECT_URI)
                .append(AND)
                .append(code);

        return url.toString().trim();
    }
}
