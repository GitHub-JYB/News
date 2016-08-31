package admin_jyb.news.Photo.presenter;

import admin_jyb.news.Photo.model.PhotoModelImpl;
import admin_jyb.news.Photo.view.PhotoView;
import admin_jyb.news.data.News;

/**
 * Created by Admin-JYB on 2016/8/30.
 */

public class PhotoPresenterImpl implements PhotoPresenter {
    private PhotoModelImpl photoModel;
    private PhotoView photoView;

    public PhotoPresenterImpl(PhotoView photoView){
        this.photoView = photoView;
        this.photoModel = new PhotoModelImpl(this);
    }

    @Override
    public void getPhotoListFromModel(int page) {
        photoModel.getPhoto(page);
    }

    @Override
    public void sendPhotoToView(News news) {
        photoView.showPhotoList(news.getNewslist());
    }

    @Override
    public void sendMorePhotoToView(News news) {
        photoView.showMorePhotoList(news.getNewslist());
    }

    @Override
    public void getMorePhotoFromModel(int page) {
        photoModel.getPhoto(page);
    }
}
