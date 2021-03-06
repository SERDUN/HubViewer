package dmitroserdun.com.ua.hubviewer.activity.screen.overviewFragment;

import java.util.List;

import dmitroserdun.com.ua.hubviewer.data.model.events.Event;
import dmitroserdun.com.ua.hubviewer.data.model.user.User;
import dmitroserdun.com.ua.hubviewer.activity.screen.BaseView;

/**
 * Created by User on 19.08.2017.
 */

public class OverviewContract {
    public interface View extends BaseView<OverviewContract.Presenter> {
        void showLoginError();

        void showMessage(int msg);

        void showPasswordError();

        void showOverviewData(User user);

        void showUserEvents(List<Event> events);


    }

    public interface Presenter {
        public void loadData();


    }
}
