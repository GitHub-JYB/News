package admin_jyb.news.social;

import admin_jyb.news.data.News;

/**
 * Created by Admin-JYB on 2016/8/27.
 */

public class SocialPresenter implements SocialContract.SocialPresenter {

    private final SocialContract.SocialModel socialModel;
    private final SocialContract.SocialView socialView;

    public SocialPresenter(SocialContract.SocialView socialView){
        this.socialView = socialView;
        this.socialModel = new SocialModel(this);
    }

    @Override
    public void getnewslistFromModel() {
        socialModel.getNews();
    }

    @Override
    public void sendNewsToView(News news) {
        socialView.shownewslist(news.getNewslist());
    }
}
