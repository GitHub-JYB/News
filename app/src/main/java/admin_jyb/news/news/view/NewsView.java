package admin_jyb.news.news.view;

import java.util.List;

import admin_jyb.news.data.News;

/**
 * Created by Admin-JYB on 2016/8/30.
 */

public interface NewsView {

    void setPresenter();

    void cancelRefresh();

    void showNewsList(List<News.NewslistBean> newslistBeen);

    void showMoreNewsToView(List<News.NewslistBean> newslistBeen);

}
