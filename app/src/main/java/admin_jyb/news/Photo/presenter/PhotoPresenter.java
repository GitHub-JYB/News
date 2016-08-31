package admin_jyb.news.Photo.presenter;

import admin_jyb.news.data.News;

/**
 * Created by Admin-JYB on 2016/8/30.
 */

public interface PhotoPresenter {
    void getPhotoListFromModel(int page);

    void sendPhotoToView(News news);

    void sendMorePhotoToView(News news);

    void getMorePhotoFromModel(int page);
}
