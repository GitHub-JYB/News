package admin_jyb.news.news.social;

import java.util.List;

import admin_jyb.news.data.News;

/**
 * Created by Admin-JYB on 2016/8/27.
 */

public interface SocialContract {

    interface SocialModel{
        void getNews();
    }

    interface SocialView{

        void setPresenter();
        void shownewslist(List<News.NewslistBean> newslistBeen);
    }

    interface SocialPresenter{
        void getnewslistFromModel();
        void sendNewsToView(News news);
    }
}
