package dmitroserdun.com.ua.hubviewer.screen.overviewFragment;

import dmitroserdun.com.ua.hubviewer.data.User;
import dmitroserdun.com.ua.hubviewer.screen.BaseView;

/**
 * Created by User on 19.08.2017.
 */

public class OverviewContract {
    interface View extends BaseView<OverviewContract.Presenter> {
        void showLoginError();

        void showMessage(String s);

        void showPasswordError();

        void showOverviewData(User user);


    }

    interface Presenter {
        public void loadData();


    }
}
