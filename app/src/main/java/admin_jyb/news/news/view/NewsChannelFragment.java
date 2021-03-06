package admin_jyb.news.news.view;

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

import admin_jyb.news.R;
import admin_jyb.news.news.NewsChannelRecycleAdapter;
import admin_jyb.news.data.News;
import admin_jyb.news.news.NewsFragment;
import admin_jyb.news.news.model.NewsModelImpi;
import admin_jyb.news.news.presenter.NewsPresenterImpl;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Admin-JYB on 2016/8/27.
 */

public class NewsChannelFragment extends Fragment implements NewsView {


    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recycle_view)
    RecyclerView recycleView;

    private NewsPresenterImpl presenter;
    private NewsChannelRecycleAdapter adapter;
    private int mtype = NewsModelImpi.NEWS_TYPE_WORLD;
    private int page = 1;
    private NewsFragment.OnReplaceFragmentListener listener;

    public NewsChannelFragment() {
        setPresenter();
    }

    public static NewsChannelFragment newInstence(int type) {
        Bundle bundle = new Bundle();
        NewsChannelFragment fragment = new NewsChannelFragment();
        bundle.putInt("type",type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void setPresenter() {
        presenter = new NewsPresenterImpl(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mtype = getArguments().getInt("type");

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof NewsFragment.OnReplaceFragmentListener) {
            listener = (NewsFragment.OnReplaceFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + "must implement OnMoveListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newschannle, container, false);
        ButterKnife.bind(this, view);
        initRefreshLayout();
        initRecycleView();
        return view;
    }

    private void initRecycleView() {
        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycleView.setItemAnimator(new DefaultItemAnimator());
        adapter = new NewsChannelRecycleAdapter(recycleView);
        adapter.setOnItemClickListener(new NewsChannelRecycleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                News.NewslistBean bean = adapter.getItem(position);
                listener.onReplaceNewsFragment(NewsChannelFragment.this,bean);

            }
        });
        adapter.setOnLoadMoreListener(new NewsChannelRecycleAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                page++ ;
                presenter.getMoreNewsFromModel(mtype,page);
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
                presenter.getNewsListFromModel(mtype,page);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                presenter.getNewsListFromModel(mtype,page);

            }
        });

    }

    @Override
    public void showNewsList(List<News.NewslistBean> newslistBean) {
        adapter.setDatas(newslistBean);
        cancelRefresh();
    }

    @Override
    public void showMoreNewsToView(List<News.NewslistBean> newslistBeen) {
        adapter.addDatas(newslistBeen);
    }

    @Override
    public void cancelRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }


}
