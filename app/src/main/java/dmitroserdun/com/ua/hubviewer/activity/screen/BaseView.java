package dmitroserdun.com.ua.hubviewer.activity.screen;

/**
 * Created by User on 17.08.2017.
 */

public interface BaseView<T> extends LoadingView {
    public  void setPresenter(T presenter);

}
