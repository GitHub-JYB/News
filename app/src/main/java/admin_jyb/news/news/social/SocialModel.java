package admin_jyb.news.news.social;


import admin_jyb.news.data.News;
import admin_jyb.news.util.ApiClient;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Admin-JYB on 2016/8/27.
 */

public class SocialModel implements SocialContract.SocialModel {

    private SocialPresenter presenter;

    public SocialModel(SocialPresenter presenter){
        this.presenter = presenter;
    }


    @Override
    public void getNews() {
        ApiClient.getService()
                .getSocialResponse(10)
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
                        presenter.sendNewsToView(news);
                    }
                });

    }
}
