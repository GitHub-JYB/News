package admin_jyb.news.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import admin_jyb.news.Photo.view.PhotoFragment;
import admin_jyb.news.R;
import admin_jyb.news.data.News;
import admin_jyb.news.news.model.NewsModelImpi;
import admin_jyb.news.news.view.NewsChannelFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Admin-JYB on 2016/8/27.
 */

public class NewsFragment extends Fragment {


    @BindView(R.id.tablayout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    public NewsFragment() {

    }

    public static NewsFragment newInstence() {
        return new NewsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        View view = inflater.
                inflate(R.layout.fragment_news, container, false);
        ButterKnife.bind(this, view);
        initViewPage();
        tabLayout.setupWithViewPager(viewpager);
        return view;
    }

    private void initViewPage() {
        NewsPagerAdapter adapter = new NewsPagerAdapter(getChildFragmentManager());
        adapter.addFragment(NewsChannelFragment.newInstence(NewsModelImpi.NEWS_TYPE_WORLD),
                getString(R.string.world));
        adapter.addFragment(NewsChannelFragment.newInstence(NewsModelImpi.NEWS_TYPE_SPORTS),
                getString(R.string.sports));
        adapter.addFragment(NewsChannelFragment.newInstence(NewsModelImpi.NEWS_TYPE_SCIENCE),
                getString(R.string.science));
        adapter.addFragment(NewsChannelFragment.newInstence(NewsModelImpi.NEWS_TYPE_HEALTH),
                getString(R.string.health));
        viewpager.setAdapter(adapter);
        viewpager.setOffscreenPageLimit(3);
    }


    public interface OnReplaceFragmentListener{
        void onReplacePhotoFragment(PhotoFragment photoFragment, News.NewslistBean bean);
        void onReplaceNewsFragment(NewsChannelFragment newsFragment, News.NewslistBean bean);

    }
}
