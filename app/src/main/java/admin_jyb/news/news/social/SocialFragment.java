package admin_jyb.news.news.social;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import admin_jyb.news.data.News;

/**
 * Created by Admin-JYB on 2016/8/27.
 */

public class SocialFragment extends Fragment implements SocialContract.SocialView {


    private SocialPresenter presenter;
    private TextView textView;

    public SocialFragment(){
        setPresenter();
    }

    public static SocialFragment newInstence(){
        return new SocialFragment();
    }
    @Override
    public void setPresenter() {
        this.presenter = new SocialPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        textView = new TextView(getContext());
        presenter.getnewslistFromModel();
        return textView;
    }

    @Override
    public void shownewslist(List<News.NewslistBean> newslistBeen) {
        textView.setText(newslistBeen.toString());

    }


}
