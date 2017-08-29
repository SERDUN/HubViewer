package dmitroserdun.com.ua.hubviewer.data.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.HashMap;

import dmitroserdun.com.ua.hubviewer.utils.Constance;

import static dmitroserdun.com.ua.hubviewer.data.provider.ContractClass.Overview.COLUMN_NAME_ID;

/**
 * Created by User on 21.08.2017.
 */

public class HubProvider extends ContentProvider {
    private static final int DATABASE_VERSION = 1;

    private static HashMap<String, String> userProjectionMap;
    private static HashMap<String, String> eventProjectionMap;

    private static final int USER = 1;
    private static final int USER_ID = 2;
    private static final int EVENT = 3;
    private static final int EVENT_ID = 4;

    private static final UriMatcher uriMatcher;
    private DatabaseHelper dbHelper;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(ContractClass.AUTHORITY, "user", USER);
        uriMatcher.addURI(ContractClass.AUTHORITY, "user/#", USER_ID);
        uriMatcher.addURI(ContractClass.AUTHORITY, "event", EVENT);
        uriMatcher.addURI(ContractClass.AUTHORITY, "event/#", EVENT_ID);


        userProjectionMap = new HashMap<String, String>();
        eventProjectionMap = new HashMap<String, String>();
        for (int i = 0; i < ContractClass.Overview.DEFAULT_PROJECTION.length; i++) {
            userProjectionMap.put(
                    ContractClass.Overview.DEFAULT_PROJECTION[i],
                    ContractClass.Overview.DEFAULT_PROJECTION[i]);
        }

        for (int i = 0; i < ContractClass.Event.DEFAULT_PROJECTION.length; i++) {
            eventProjectionMap.put(
                    ContractClass.Event.DEFAULT_PROJECTION[i],
                    ContractClass.Event.DEFAULT_PROJECTION[i]);
        }
    }

    @Override
    public boolean onCreate() {
        dbHelper = new DatabaseHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        switch (uriMatcher.match(uri)) {
            case USER:
                qb.setTables(ContractClass.Overview.TABLE_NAME);
                qb.setProjectionMap(userProjectionMap);
                break;
            case USER_ID:
                qb.setTables(ContractClass.Overview.TABLE_NAME);
                qb.setProjectionMap(userProjectionMap);
                qb.appendWhere(ContractClass.Overview._ID + "=" + uri.getPathSegments().get(ContractClass.Overview.NEWS_ID_PATH_POSITION));
                break;
            case EVENT:
                qb.setTables(ContractClass.Event.TABLE_NAME);
                qb.setProjectionMap(eventProjectionMap);
                break;
            case EVENT_ID:
                qb.setTables(ContractClass.Event.TABLE_NAME);
                qb.setProjectionMap(eventProjectionMap);
                qb.appendWhere(ContractClass.Event._ID + "=" + uri.getPathSegments().get(ContractClass.Event.ID_PATH_POSITION));
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = qb.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)) {
            case USER:
                return ContractClass.Overview.CONTENT_TYPE;
            case USER_ID:
                return ContractClass.Overview.CONTENT_ITEM_TYPE;
            case EVENT:
                return ContractClass.Event.CONTENT_TYPE;
            case EVENT_ID:
                return ContractClass.Event.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }


    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        if (uriMatcher.match(uri) != USER) {
            throw new IllegalArgumentException("Unknown URI " + uri);
        }
        int nrInserted = 0;
        long rowId = -1;
        Uri rowUri = Uri.EMPTY;


        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.beginTransaction();

        try {

            for (ContentValues cv : values) {

//                db.insertOrThrow(ContractClass.News.TABLE_NAME, null, cv);

                rowId = db.insertOrThrow(ContractClass.Overview.TABLE_NAME, null, cv);

                nrInserted++;
            }

            db.setTransactionSuccessful();
            db.endTransaction();


        } catch (SQLException ex) {

            ex.printStackTrace();

        } finally {

        }
        Intent broadcastIntent = new Intent(Constance.BROADCAST_ACTION);

//        if (rowId > 0) {
//
//            rowUri = ContentUris.withAppendedId(ContractClass.Overview.CONTENT_ID_URI_BASE, rowId);
//            getContext().getContentResolver().notifyChange(rowUri, null);
//            broadcastIntent.putExtra(Const.BroadcastConst.STATUS, Const.BroadcastConst.STATUS_OK);
//            getContext().sendBroadcast(broadcastIntent);
//
//        }else {
//            broadcastIntent.putExtra(Const.BroadcastConst.STATUS, Const.BroadcastConst.STATUS_NOT_UPDATE);
//            getContext().sendBroadcast(broadcastIntent);
//
//
//        }
        return nrInserted;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues newValues) {

//        if (uriMatcher.match(uri) != USER||uriMatcher.match(uri) !=EVENT) {
//            throw new IllegalArgumentException("Unknown URI " + uri);
//        }
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values;
        if (newValues != null) {
            values = new ContentValues(newValues);
        } else {
            values = new ContentValues();
        }
        long rowId = -1;
        Uri rowUri = Uri.EMPTY;

        switch (uriMatcher.match(uri)) {
            case USER:
                rowId = db.insert(ContractClass.Overview.TABLE_NAME, null, values);
                break;
            case EVENT:
                rowId = db.insert(ContractClass.Event.TABLE_NAME, null, values);
                break;
        }

        if (rowId > 0) {
            rowUri = ContentUris.withAppendedId(ContractClass.Overview.CONTENT_ID_URI_BASE, rowId);
            getContext().getContentResolver().notifyChange(rowUri, null);
        } else {
//                Intent broadcastIntent = new Intent(Constance.BROADCAST_ACTION);
//                broadcastIntent.putExtra(Const.BroadcastConst.STATUS, Const.BroadcastConst.STATUS_NOT_UPDATE);
//                getContext().sendBroadcast(broadcastIntent);


        }


        return rowUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[]
            selectionArgs) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String finalWhere;
        int count;
        switch (uriMatcher.match(uri)) {
            case USER:
                count = db.delete(ContractClass.Overview.TABLE_NAME, selection, selectionArgs);
                break;
            case USER_ID:
                finalWhere = ContractClass.Overview._ID + " = " + uri.getPathSegments().get(ContractClass.Overview.NEWS_ID_PATH_POSITION);
                if (selection != null) {
                    finalWhere = finalWhere + " AND " + selection;
                }
                count = db.delete(ContractClass.Overview.TABLE_NAME, finalWhere, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String where, String[] whereArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int count;
        String finalWhere;
        String id;
        switch (uriMatcher.match(uri)) {
            case USER:
                count = db.update(ContractClass.Overview.TABLE_NAME, values, where, whereArgs);
                break;
            case USER_ID:
                id = uri.getPathSegments().get(ContractClass.Overview.NEWS_ID_PATH_POSITION);
                finalWhere = ContractClass.Overview._ID + " = " + id;
                if (where != null) {
                    finalWhere = finalWhere + " AND " + where;
                }
                count = db.update(ContractClass.Overview.TABLE_NAME, values, finalWhere, whereArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        //// TODO: 09.08.2017 create const helper
        private static final String DATABASE_NAME = "NewsDatabase";
        public static final String DATABASE_TABLE_USERS = ContractClass.Overview.TABLE_NAME;
        public static final String DATABASE_TABLE_EVENTS = ContractClass.Event.TABLE_NAME;
        public static final String KEY_ROWID = "_id";
        private static final String DATABASE_CREATE_TABLE_USER =
                "create table " + DATABASE_TABLE_USERS + " ("
                        + KEY_ROWID + " integer primary key autoincrement, "
                        + ContractClass.Overview.COLUMN_NAME_LOGIN + " string , "
                        + COLUMN_NAME_ID + " integer , "
                        + ContractClass.Overview.COLUMN_NAME_AVATAR_URL + " string , "
                        + ContractClass.Overview.COLUMN_NAME_GRAWATAR_ID + " string , "
                        + ContractClass.Overview.COLUMN_NAME_URL + " string , "
                        + ContractClass.Overview.COLUMN_NAME_HTML_URL + " string , "
                        + ContractClass.Overview.COLUMN_NAME_FOLLOWERS_URL + " string , "
                        + ContractClass.Overview.COLUMN_NAME_FOLLOWING_URL + " string , "
                        + ContractClass.Overview.COLUMN_NAME_GISTS_URL + " string , "
                        + ContractClass.Overview.COLUMN_NAME_STARRED_URL + " string , "
                        + ContractClass.Overview.COLUMN_NAME_SUBSCRIPTIONS_URL + " string , "
                        + ContractClass.Overview.COLUMN_NAME_ORGANIZATIONS_URL + " string , "
                        + ContractClass.Overview.COLUMN_NAME_REPOS_URL + " string , "
                        + ContractClass.Overview.COLUMN_NAME_EVENTS_URL + " string , "
                        + ContractClass.Overview.COLUMN_NAME_RECEIVED_EVENTS_URL + " string , "
                        + ContractClass.Overview.COLUMN_NAME_TYPE + " string , "
                        + ContractClass.Overview.COLUMN_NAME_SITE_ADMIN + " string , "
                        + ContractClass.Overview.COLUMN_NAME_NAME + " string , "
                        + ContractClass.Overview.COLUMN_NAME_COMPANY + " string , "
                        + ContractClass.Overview.COLUMN_NAME_BLOG + " string , "
                        + ContractClass.Overview.COLUMN_NAME_LOCATION + " string , "
                        + ContractClass.Overview.COLUMN_NAME_EMAIL + " string , "
                        + ContractClass.Overview.COLUMN_NAME_HIREABLE + " string , "
                        + ContractClass.Overview.COLUMN_NAME_BIO + " string , "
                        + ContractClass.Overview.COLUMN_NAME_PUBLIC_REPOS + " integer , "
                        + ContractClass.Overview.COLUMN_NAME_PUBLIC_GISTS + " integer , "
                        + ContractClass.Overview.COLUMN_NAME_FOLLOWERS + " integer , "
                        + ContractClass.Overview.COLUMN_NAME_FOLLOWING + " integer , "
                        + ContractClass.Overview.COLUMN_NAME_CREATED_AT + " string , "
                        + ContractClass.Overview.COLUMN_NAME_UPDATE_AT + " string , "
                        + " UNIQUE ( " + COLUMN_NAME_ID + " ) ON CONFLICT IGNORE" + ");";

        private static final String DATABASE_CREATE_TABLE_EVENTS =
                "create table " + DATABASE_TABLE_EVENTS + " ("
                        + KEY_ROWID + " integer primary key autoincrement, "
                        + ContractClass.Event.COLUMN_NAME_EVENT + " string default '', "
                        + ContractClass.Event.COLUMN_NAME_ACTOR + " string default '', "
                        + ContractClass.Event.COLUMN_NAME_REPO + "  string default 0, "
                        + ContractClass.Event.KEY_FK_USER_ID + " integer default -1 "
                        //   + " foreign key (" + KEY_FK_USER_ID + ") references " + DATABASE_TABLE_USERS + "(" + COLUMN_NAME_ID + "));";
                        + ");";


        private Context ctx;

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            ctx = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE_TABLE_USER);
            db.execSQL(DATABASE_CREATE_TABLE_EVENTS);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_USERS);
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_EVENTS);
            onCreate(db);
        }
    }

}
