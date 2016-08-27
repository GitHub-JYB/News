package admin_jyb.news.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import admin_jyb.news.R;
import admin_jyb.news.news.social.SocialFragment;

/**
 * Created by Admin-JYB on 2016/8/27.
 */

public class ContentFragment extends Fragment {


    public ContentFragment(){

    }

    public static ContentFragment newInstence(){
        return new ContentFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        ViewPager viewpager = (ViewPager) inflater.
                inflate(R.layout.fragment_content, container, false);
        NewsPagerAdapter adapter = new NewsPagerAdapter(getActivity()
                .getSupportFragmentManager());
        adapter.addFragment(SocialFragment.newInstence());
        viewpager.setAdapter(adapter);
        return viewpager;
    }
}
