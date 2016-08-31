package admin_jyb.news.Photo.model;

import admin_jyb.news.Photo.presenter.PhotoPresenter;
import admin_jyb.news.Photo.presenter.PhotoPresenterImpl;
import admin_jyb.news.data.News;
import admin_jyb.news.util.ApiClient;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Admin-JYB on 2016/8/30.
 */

public class PhotoModelImpl implements PhotoModel {

    private PhotoPresenter presenter;
    private int num = 30;
    private int page = 1;
    private String channel = "mvtp/meinv";

    public PhotoModelImpl(PhotoPresenterImpl presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getPhoto(final int page) {
        this.page = page;
        ApiClient.getService()
                .getSocialResponse(channel,num,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<News>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(News news) {
                        if (page == 1){
                            presenter.sendPhotoToView(news);
                        }else {
                            presenter.sendMorePhotoToView(news);
                        }
                    }
                });
    }
}
