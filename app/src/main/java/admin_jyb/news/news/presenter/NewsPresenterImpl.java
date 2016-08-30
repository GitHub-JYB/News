package admin_jyb.news.news.presenter;

import admin_jyb.news.data.News;
import admin_jyb.news.news.model.NewsModel;
import admin_jyb.news.news.model.NewsModelImpi;
import admin_jyb.news.news.view.NewsView;

/**
 * Created by Admin-JYB on 2016/8/27.
 */

public class NewsPresenterImpl implements NewsPresenter {

    private final NewsModel socialModel;
    private final NewsView socialView;

    public NewsPresenterImpl(NewsView socialView){
        this.socialView = socialView;
        this.socialModel = new NewsModelImpi(this);
    }

    @Override
    public void getNewsListFromModel(int type,int page) {
        socialModel.getNews(type,page);
    }

    @Override
    public void getMoreNewsFromModel(int type,int page) {
        socialModel.getNews(type,page);
    }

    @Override
    public void sendNewsToView(News news) {
        socialView.showNewsList(news.getNewslist());
    }

    @Override
    public void sendMoreNewsToView(News news) {
        socialView.showMoreNewsToView(news.getNewslist());
    }
}
