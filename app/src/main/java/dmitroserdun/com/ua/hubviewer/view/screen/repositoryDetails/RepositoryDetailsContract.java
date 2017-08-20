package dmitroserdun.com.ua.hubviewer.view.screen.repositoryDetails;

import dmitroserdun.com.ua.hubviewer.data.model.Owner;
import dmitroserdun.com.ua.hubviewer.data.model.RepositoryDetails;
import dmitroserdun.com.ua.hubviewer.view.screen.BaseView;

/**
 * Created by User on 20.08.2017.
 */

public class RepositoryDetailsContract {
    interface View extends BaseView<RepositoryDetailsContract.Presenter> {
        void showLoginError();

        void showMessage(String s);

        void showPasswordError();

        void showRepoDetails(RepositoryDetails repositoryDetails);


    }

    interface Presenter {
        public Owner getOwner();
        public void loadDetails();

    }
}
