package admin_jyb.news.Photo.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import admin_jyb.news.Photo.PhotoRecycleAdapter;

import admin_jyb.news.Photo.presenter.PhotoPresenterImpl;
import admin_jyb.news.R;
import admin_jyb.news.data.News;
import admin_jyb.news.news.DetailFragment;
import admin_jyb.news.news.NewsFragment;
import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Admin-JYB on 2016/8/30.
 */

public class PhotoFragment extends Fragment implements PhotoView {

    @BindView(R.id.recycle_view)
    RecyclerView recycleView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    private PhotoPresenterImpl presenter;
    private int page = 1;
    private PhotoRecycleAdapter adapter;
    private NewsFragment.OnReplaceFragmentListener listener;

    public PhotoFragment() {
        setPresenter();
    }

    @Override
    public void setPresenter() {
        presenter = new PhotoPresenterImpl(this);
    }

    @Override
    public void showPhotoList(List<News.NewslistBean> newslist) {
        adapter.setDatas(newslist);
        cancelRefresh();
    }

    @Override
    public void cancelRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showMorePhotoList(List<News.NewslistBean> newslist) {
        adapter.addDatas(newslist);
    }

    public static PhotoFragment newInstence() {
        return new PhotoFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof NewsFragment.OnReplaceFragmentListener) {
            listener = (NewsFragment.OnReplaceFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + "must implement OnReplaceFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_photo, container, false);
        ButterKnife.bind(this, view);
        initRefreshLayout();
        initRecycleView();
        return view;
    }

    private void initRecycleView() {
        recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycleView.setItemAnimator(new DefaultItemAnimator());
        recycleView.setHasFixedSize(true);
        adapter = new PhotoRecycleAdapter(recycleView);
        adapter.setOnItemClickListener(new PhotoRecycleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                News.NewslistBean Bean = adapter.getItem(position);
                listener.onReplacePhotoFragment(PhotoFragment.this, Bean);

            }
        });
        adapter.setOnLoadMoreListener(new PhotoRecycleAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                page++ ;
                presenter.getMorePhotoFromModel(page);
            }
        });
        recycleView.setAdapter(adapter);
    }

    private void initRefreshLayout() {
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                R.color.colorAccent,
                R.color.colorPrimaryDark);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                page = 1;
                presenter.getPhotoListFromModel(page);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                presenter.getPhotoListFromModel(page);

            }
        });

    }


}
