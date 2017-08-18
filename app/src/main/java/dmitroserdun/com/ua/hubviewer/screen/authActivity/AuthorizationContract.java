package dmitroserdun.com.ua.hubviewer.screen.authActivity;

import dmitroserdun.com.ua.hubviewer.screen.BaseView;

/**
 * Created by User on 17.08.2017.
 */

public class AuthorizationContract {
    interface View extends BaseView<Presenter> {
        void openProfile();

        void showWebView();

        void showLoginError();

        void showMessage(String s);

        void showPasswordError();


    }

    interface Presenter {
        public void init();

        /**
         * Use with basic authorization
         *
         * @param login
         * @param password
         */
        public void logIn(String login, String password);

        /**
         * Use with Oauth authorization
         */
        public void logIn();

        public void applyTokenByCode(String code);


    }

}
