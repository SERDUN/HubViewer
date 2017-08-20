package dmitroserdun.com.ua.hubviewer.view.screen.repositoryDetails;

import java.util.List;

import dmitroserdun.com.ua.hubviewer.data.model.directory.Directory;
import dmitroserdun.com.ua.hubviewer.data.model.user.Owner;
import dmitroserdun.com.ua.hubviewer.data.model.repository.RepositoryDetails;
import dmitroserdun.com.ua.hubviewer.view.screen.BaseView;

/**
 * Created by User on 20.08.2017.
 */

public class RepositoryDetailsContract {
    interface View extends BaseView<RepositoryDetailsContract.Presenter> {
        void showLoginError();

        void showMessage(String s);

        void showPasswordError();

        void showContent(List<Directory> directory);

        void showRepoDetails(RepositoryDetails repositoryDetails);


    }

    interface Presenter {
        public Owner getOwner();

        public void loadRepositoryContent(String path);

        public void loadDetails();

    }
}
