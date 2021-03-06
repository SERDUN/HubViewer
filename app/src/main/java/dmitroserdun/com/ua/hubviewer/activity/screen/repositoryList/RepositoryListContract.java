package dmitroserdun.com.ua.hubviewer.activity.screen.repositoryList;

import java.util.List;

import dmitroserdun.com.ua.hubviewer.data.model.repository.Repository;
import dmitroserdun.com.ua.hubviewer.activity.screen.BaseView;

/**
 * Created by User on 19.08.2017.
 */

public interface RepositoryListContract {
    interface View extends BaseView<RepositoryListContract.Presenter> {
        void showLoginError();

        void showMessage(String s);

        void showPasswordError();

        void showRepository(List<Repository> repositories);

        public void openRepositoryDetails(Repository repository);





    }

    interface Presenter {
        public void loadRepository();
        public void search(String q);
        public void openRepositoryDetails(Repository repository);

    }
}
