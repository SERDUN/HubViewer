package dmitroserdun.com.ua.hubviewer.activity.screen.contentRepository;

import dmitroserdun.com.ua.hubviewer.activity.screen.BaseView;

/**
 * Created by User on 20.08.2017.
 */

public class CententRepositoryContract {
    interface View extends BaseView<CententRepositoryContract.Presenter> {
        void showLoginError();

        void showMessage(String s);

        void showPasswordError();



    }

    interface Presenter {


    }
}
