package admin_jyb.news.news.model;


import admin_jyb.news.data.News;
import admin_jyb.news.news.presenter.NewsPresenterImpl;
import admin_jyb.news.util.ApiClient;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Admin-JYB on 2016/8/27.
 */

public class NewsModelImpi implements NewsModel {

    public static final int NEWS_TYPE_WORLD = 0;
    public static final int NEWS_TYPE_SPORTS = 1;
    public static final int NEWS_TYPE_SCIENCE = 2;
    public static final int NEWS_TYPE_HEALTH = 3;
    private NewsPresenterImpl presenter;
    private String key = "06c503d66c5bb4385ae60e811c9e4507";
    private String channel = "world";
    private int num = 30;
    private int page = 1;

    public NewsModelImpi(NewsPresenterImpl presenter){
        this.presenter = presenter;
    }


    @Override
    public void getNews(int type, final int page) {

        switchType(type,page);

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
                            presenter.sendNewsToView(news);
                        }else {
                            presenter.sendMoreNewsToView(news);
                        }
                    }
                });

    }

    private void switchType(int type,int page) {
        switch (type){
            case NEWS_TYPE_WORLD:
                channel = "world/world";
                break;
            case NEWS_TYPE_SPORTS:
                channel = "tiyu/tiyu";
                break;
            case NEWS_TYPE_SCIENCE:
                channel = "keji/keji";
                break;
            case NEWS_TYPE_HEALTH:
                channel = "health/health";
                break;
            default:
                break;
        }

        this.page = page;
    }


}
