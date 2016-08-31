package admin_jyb.news.Photo.view;

import java.util.List;

import admin_jyb.news.data.News;

/**
 * Created by Admin-JYB on 2016/8/30.
 */

public interface PhotoView {
    void setPresenter();

    void showPhotoList(List<News.NewslistBean> newslist);

    void cancelRefresh();

    void showMorePhotoList(List<News.NewslistBean> newslist);
}
