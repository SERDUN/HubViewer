package dmitroserdun.com.ua.hubviewer.view.screen.overviewFragment;

import dmitroserdun.com.ua.hubviewer.data.model.user.User;
import dmitroserdun.com.ua.hubviewer.view.screen.BaseView;

/**
 * Created by User on 19.08.2017.
 */

public class OverviewContract {
    public interface View extends BaseView<OverviewContract.Presenter> {
        void showLoginError();

        void showMessage(String s);

        void showPasswordError();

        void showOverviewData(User user);


    }

    public interface Presenter {
        public void loadData();


    }
}
