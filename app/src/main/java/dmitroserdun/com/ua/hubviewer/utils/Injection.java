package dmitroserdun.com.ua.hubviewer.utils;

import android.content.Context;
import android.support.annotation.NonNull;

import dmitroserdun.com.ua.hubviewer.repository.GitHubDataSource;
import dmitroserdun.com.ua.hubviewer.repository.ManagerGitHubDataSource;
import dmitroserdun.com.ua.hubviewer.repository.local.LocalGitHubDataSource;
import dmitroserdun.com.ua.hubviewer.repository.remote.RemoteGitHubDataSource;

/**
 * Created by User on 18.08.2017.
 */

public class Injection {
    public static ManagerGitHubDataSource provideTasksRepository(@NonNull Context context) {
        return ManagerGitHubDataSource.getInstance(LocalGitHubDataSource.getInstance(context),
                RemoteGitHubDataSource.getInstance());
    }
}
