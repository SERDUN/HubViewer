package dmitroserdun.com.ua.hubviewer.screen;

/**
 * Created by User on 17.08.2017.
 */

public interface BaseView<T> extends LoadingView {
    void setPresenter(T presenter);

}
