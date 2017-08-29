package dmitroserdun.com.ua.hubviewer.data.provider;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by User on 21.08.2017.
 */

public class ContractClass {
    public static final String AUTHORITY = "dmitroserdun.com.ua.hubviewer.data.provider.ContractClass";

    private ContractClass() {
    }

    public static final class Overview implements BaseColumns {


        public static final String TABLE_NAME = "user";
        private static final String SCHEME = "content://";
        private static final String PATH_OVERVIEW = "/user";
        private static final String PATH_OVERVIEW_ID = "/user/";


        public static final int OVERVIEW_ID_PATH_POSITION = 1;
        public static final Uri CONTENT_URI = Uri.parse(SCHEME + AUTHORITY + PATH_OVERVIEW);
        public static final Uri CONTENT_URI_SERVER = Uri.parse(SCHEME + AUTHORITY + PATH_OVERVIEW);
        public static final Uri CONTENT_ID_URI_BASE = Uri.parse(SCHEME + AUTHORITY + PATH_OVERVIEW_ID);
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.google.overview";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.google.overview";
        public static final String DEFAULT_SORT_ORDER = "_id DESC limit 10 ";

        public static final String COLUMN_NAME_LOGIN = "login";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_AVATAR_URL = "avatar_url";
        public static final String COLUMN_NAME_GRAWATAR_ID = "gravatar_id";
        public static final String COLUMN_NAME_URL = "url";
        public static final String COLUMN_NAME_HTML_URL = "html_url";
        public static final String COLUMN_NAME_FOLLOWERS_URL = "followers_url";
        public static final String COLUMN_NAME_FOLLOWING_URL = "following_url";
        public static final String COLUMN_NAME_GISTS_URL = "gists_url";
        public static final String COLUMN_NAME_STARRED_URL = "starred_url";
        public static final String COLUMN_NAME_SUBSCRIPTIONS_URL = "subscriptions_url";
        public static final String COLUMN_NAME_ORGANIZATIONS_URL = "organizations_url";
        public static final String COLUMN_NAME_REPOS_URL = "repos_url";
        public static final String COLUMN_NAME_EVENTS_URL = "events_url";
        public static final String COLUMN_NAME_RECEIVED_EVENTS_URL = "received_events_url";
        public static final String COLUMN_NAME_TYPE = "type";
        public static final String COLUMN_NAME_SITE_ADMIN = "site_admin";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_COMPANY = "company";
        public static final String COLUMN_NAME_BLOG = "blog";
        public static final String COLUMN_NAME_LOCATION = "location";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_HIREABLE = "hireable";
        public static final String COLUMN_NAME_BIO = "bio";
        public static final String COLUMN_NAME_PUBLIC_REPOS = "public_repos";
        public static final String COLUMN_NAME_PUBLIC_GISTS = "public_gists";
        public static final String COLUMN_NAME_FOLLOWERS = "followers";
        public static final String COLUMN_NAME_FOLLOWING = "following";
        public static final String COLUMN_NAME_CREATED_AT = "created_at";
        public static final String COLUMN_NAME_UPDATE_AT = "updated_at";

        public static final int NEWS_ID_PATH_POSITION = 1;

        public static final String[] DEFAULT_PROJECTION = new String[]{
                ContractClass.Overview._ID,
                ContractClass.Overview.COLUMN_NAME_LOGIN,
                ContractClass.Overview.COLUMN_NAME_ID,
                ContractClass.Overview.COLUMN_NAME_AVATAR_URL,
                Overview.COLUMN_NAME_GRAWATAR_ID,
                ContractClass.Overview.COLUMN_NAME_URL,
                ContractClass.Overview.COLUMN_NAME_HTML_URL,
                ContractClass.Overview.COLUMN_NAME_FOLLOWERS_URL,
                ContractClass.Overview.COLUMN_NAME_FOLLOWING_URL,
                ContractClass.Overview.COLUMN_NAME_GISTS_URL,
                ContractClass.Overview.COLUMN_NAME_STARRED_URL,
                ContractClass.Overview.COLUMN_NAME_SUBSCRIPTIONS_URL,
                ContractClass.Overview.COLUMN_NAME_ORGANIZATIONS_URL,
                ContractClass.Overview.COLUMN_NAME_REPOS_URL,
                ContractClass.Overview.COLUMN_NAME_EVENTS_URL,
                ContractClass.Overview.COLUMN_NAME_RECEIVED_EVENTS_URL,
                ContractClass.Overview.COLUMN_NAME_TYPE,
                ContractClass.Overview.COLUMN_NAME_SITE_ADMIN,
                ContractClass.Overview.COLUMN_NAME_NAME,
                ContractClass.Overview.COLUMN_NAME_COMPANY,
                ContractClass.Overview.COLUMN_NAME_BLOG,
                ContractClass.Overview.COLUMN_NAME_LOCATION,
                ContractClass.Overview.COLUMN_NAME_EMAIL,
                ContractClass.Overview.COLUMN_NAME_HIREABLE,
                ContractClass.Overview.COLUMN_NAME_BIO,
                ContractClass.Overview.COLUMN_NAME_PUBLIC_REPOS,
                ContractClass.Overview.COLUMN_NAME_PUBLIC_GISTS,
                ContractClass.Overview.COLUMN_NAME_FOLLOWERS,
                ContractClass.Overview.COLUMN_NAME_FOLLOWING,
                ContractClass.Overview.COLUMN_NAME_CREATED_AT,
                ContractClass.Overview.COLUMN_NAME_UPDATE_AT
        };
    }


    public static final class Event implements BaseColumns {


        public static final String TABLE_NAME = "event";
        private static final String SCHEME = "content://";
        private static final String PATH_EVENT = "/event";
        private static final String PATH_EVENT_ID = "/event/";


        public static final Uri CONTENT_URI = Uri.parse(SCHEME + AUTHORITY + PATH_EVENT);
        public static final Uri CONTENT_ID_URI_BASE = Uri.parse(SCHEME + AUTHORITY + PATH_EVENT_ID);
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.google.event";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.google.event";
        public static final String DEFAULT_SORT_ORDER = "_id DESC limit 10 ";

        public static final String COLUMN_NAME_EVENT = "type";
        public static final String COLUMN_NAME_ACTOR = "login";
        public static final String COLUMN_NAME_REPO = "full_name";
        public static final String KEY_FK_USER_ID = "fk_user_id";


        public static final int ID_PATH_POSITION = 1;

        public static final String[] DEFAULT_PROJECTION = new String[]{
                ContractClass.Event._ID,
                ContractClass.Event.COLUMN_NAME_EVENT,
                ContractClass.Event.COLUMN_NAME_ACTOR,
                ContractClass.Event.COLUMN_NAME_REPO,
                ContractClass.Event.KEY_FK_USER_ID

        };


    }


}
