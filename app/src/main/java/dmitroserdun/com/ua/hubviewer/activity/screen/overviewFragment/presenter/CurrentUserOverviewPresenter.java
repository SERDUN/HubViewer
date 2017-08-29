package dmitroserdun.com.ua.hubviewer.activity.screen.overviewFragment.presenter;

import android.content.ContentResolver;
import android.content.SharedPreferences;

import dmitroserdun.com.ua.hubviewer.R;
import dmitroserdun.com.ua.hubviewer.activity.screen.overviewFragment.OverviewContract;
import dmitroserdun.com.ua.hubviewer.data.model.user.User;
import dmitroserdun.com.ua.hubviewer.repository.ManagerGitHubDataSource;

import static dmitroserdun.com.ua.hubviewer.utils.Constance.CURRENT_FULL_NAME;
import static dmitroserdun.com.ua.hubviewer.utils.Constance.CURRENT_TOKEN_KEY;
import static dmitroserdun.com.ua.hubviewer.utils.Constance.CURRENT_USERNAME;

/**
 * Created by User on 19.08.2017.
 */

public class CurrentUserOverviewPresenter implements OverviewContract.Presenter {
    private OverviewContract.View view;
    private ManagerGitHubDataSource managerGitHubDataSource;
    private SharedPreferences pref;
    private ContentResolver resolver;

    public CurrentUserOverviewPresenter(OverviewContract.View view,
                                        ManagerGitHubDataSource managerGitHubDataSource,
                                        SharedPreferences pref, ContentResolver resolver) {
        this.view = view;
        view.setPresenter(this);//register view
        this.managerGitHubDataSource = managerGitHubDataSource;
        this.pref = pref;
        this.resolver = resolver;
    }

    @Override
    public void loadData() {
        view.showLoadingView("");
        managerGitHubDataSource.refreshLocalData();
        managerGitHubDataSource.getCurrentUser(getToken(), user -> {
            view.showOverviewData(user);
            saveLoginUserForRequest(user);

            managerGitHubDataSource.getUserEvents(user.getLogin(), events -> {
                view.showUserEvents(events);
//                test(user, events);
            }, t -> {
            }, () -> {
            });

        }, t -> {
            view.showMessage(R.string.unknown_host);

        }, () -> {
            view.hideLoadingView();

        });

    }


    private String getToken() {
        return pref.getString(CURRENT_TOKEN_KEY, "");

    }

    private void saveLoginUserForRequest(User user) {
        pref.edit().putString(CURRENT_USERNAME, user.getLogin()).apply();
        pref.edit().putString(CURRENT_FULL_NAME, user.getName()).apply();
    }

    ///TEST

//    private void test(User user, List<Event> events) {
//        resolver.insert(ContractClass.Overview.CONTENT_URI, createContentValues(user));
//
//        for (Event event : events) {
//            resolver.insert(ContractClass.Event.CONTENT_URI, createContentValuesEvent(event, user));
//
//        }
//
//    }
//
//    public ContentValues createContentValues(User user) {
//        ContentValues value = new ContentValues();
//
//        value.put(ContractClass.Overview.COLUMN_NAME_LOGIN, (String) user.getLogin());
//        value.put(ContractClass.Overview.COLUMN_NAME_ID, user.getId());
//        value.put(ContractClass.Overview.COLUMN_NAME_AVATAR_URL, (String) user.getAvatarUrl());
//        value.put(ContractClass.Overview.COLUMN_NAME_GRAWATAR_ID, (String) user.getGravatarId());
//        value.put(ContractClass.Overview.COLUMN_NAME_URL, (String) user.getUrl());
//        value.put(ContractClass.Overview.COLUMN_NAME_HTML_URL, (String) user.getHtmlUrl());
//        value.put(ContractClass.Overview.COLUMN_NAME_FOLLOWERS_URL, (String) user.getFollowersUrl());
//        value.put(ContractClass.Overview.COLUMN_NAME_FOLLOWING_URL, (String) user.getFollowingUrl());
//        value.put(ContractClass.Overview.COLUMN_NAME_GISTS_URL, (String) user.getGistsUrl());
//        value.put(ContractClass.Overview.COLUMN_NAME_STARRED_URL, (String) user.getStarredUrl());
//        value.put(ContractClass.Overview.COLUMN_NAME_SUBSCRIPTIONS_URL, (String) user.getSubscriptionsUrl());
//        value.put(ContractClass.Overview.COLUMN_NAME_ORGANIZATIONS_URL, (String) user.getOrganizationsUrl());
//        value.put(ContractClass.Overview.COLUMN_NAME_REPOS_URL, (String) user.getReposUrl());
//        value.put(ContractClass.Overview.COLUMN_NAME_EVENTS_URL, (String) user.getEventsUrl());
//        value.put(ContractClass.Overview.COLUMN_NAME_RECEIVED_EVENTS_URL, (String) user.getReceivedEventsUrl());
//        value.put(ContractClass.Overview.COLUMN_NAME_TYPE, (String) user.getType());
//        value.put(ContractClass.Overview.COLUMN_NAME_SITE_ADMIN, String.valueOf(user.getSiteAdmin()));
//        value.put(ContractClass.Overview.COLUMN_NAME_NAME, (String) user.getName());
//        value.put(ContractClass.Overview.COLUMN_NAME_COMPANY, (String) user.getCompany());
//        value.put(ContractClass.Overview.COLUMN_NAME_BLOG, (String) user.getBlog());
//        value.put(ContractClass.Overview.COLUMN_NAME_LOCATION, (String) user.getLocation());
//        value.put(ContractClass.Overview.COLUMN_NAME_EMAIL, (String) user.getEmail());
//        value.put(ContractClass.Overview.COLUMN_NAME_HIREABLE, (String) user.getHireable());
//        value.put(ContractClass.Overview.COLUMN_NAME_BIO, (String) user.getBio());
//        value.put(ContractClass.Overview.COLUMN_NAME_PUBLIC_REPOS, user.getPublicRepos());
//        value.put(ContractClass.Overview.COLUMN_NAME_PUBLIC_GISTS, user.getPublicGists());
//        value.put(ContractClass.Overview.COLUMN_NAME_FOLLOWERS, user.getFollowers());
//        value.put(ContractClass.Overview.COLUMN_NAME_FOLLOWING, user.getFollowing());
//        value.put(ContractClass.Overview.COLUMN_NAME_CREATED_AT, (String) user.getCreatedAt());
//        value.put(ContractClass.Overview.COLUMN_NAME_UPDATE_AT, (String) user.getUpdatedAt());
//
//        return value;
//    }
//
//
//    public ContentValues createContentValuesEvent(Event event, User user) {
//        ContentValues value = new ContentValues();
//        value.put(ContractClass.Event.COLUMN_NAME_EVENT, (String) event.getType());
//        value.put(ContractClass.Event.COLUMN_NAME_ACTOR, event.getActor().getLogin());
//        value.put(ContractClass.Event.COLUMN_NAME_REPO, (String) event.getRepo().getName());
//        value.put(ContractClass.Event.KEY_FK_USER_ID, user.getId());
//
//
//        return value;
//    }


}
