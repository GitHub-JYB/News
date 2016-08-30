package admin_jyb.news.news.presenter;

import admin_jyb.news.data.News;

/**
 * Created by Admin-JYB on 2016/8/30.
 */

public interface NewsPresenter {

    void getNewsListFromModel(int type,int page);
    void getMoreNewsFromModel(int type,int page);
    void sendNewsToView(News news);
    void sendMoreNewsToView(News news);

}
