package admin_jyb.news.main.presenter;

import android.view.MenuItem;

import admin_jyb.news.R;
import admin_jyb.news.main.view.MainView;
import admin_jyb.news.news.NewsFragment;

/**
 * Created by Admin-JYB on 2016/8/30.
 */

public class MainPresenterImpl implements MainPresenter {

    private MainView mainView;

    public MainPresenterImpl(MainView mainView){
        this.mainView = mainView;
    }

    @Override
    public void switchNavigation(MenuItem item) {

            switch (item.getItemId()){
                case R.id.navigation_news:
                    if (!isItemChecked(item)){
                        mainView.replaceFragment(NewsFragment.newInstence());
                    }
                    break;
                case R.id.navigation_photo:
                    if (!isItemChecked(item)){
                        mainView.replaceFragment(NewsFragment.newInstence());
                    }
                    break;
            }
            item.setChecked(true);
            mainView.closeDrawers();

    }

    private boolean isItemChecked(MenuItem item) {
        return item.isChecked();
    }
}
