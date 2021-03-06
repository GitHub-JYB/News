package admin_jyb.news.news;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import admin_jyb.news.R;
import admin_jyb.news.data.News;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Admin-JYB on 2016/8/29.
 */
public class NewsChannelRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private OnItemClickListener onItemClickListeren;
    private OnLoadMoreListener loadMoreListener;
    private List<News.NewslistBean> newslistBean;
    private Context context;
    private boolean isLoading = false;


    public NewsChannelRecycleAdapter(RecyclerView recyclerView) {

        final LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItem = manager.findLastVisibleItemPosition();
                if (!isLoading && lastVisibleItem >= manager.getItemCount() -1){
                    if (loadMoreListener != null){
                        loadMoreListener.onLoadMore();
                    }
                    isLoading = true;
                }
            }
        });
    }

    public void setDatas(List<News.NewslistBean> newslistBean){
        this.newslistBean = newslistBean;
        notifyDataSetChanged();
    }

    public void addDatas(List<News.NewslistBean> newslistBeen) {
        this.newslistBean.addAll(newslistBeen);
        isLoading = false ;
        notifyDataSetChanged();
    }

    public News.NewslistBean getItem(int position){
        return newslistBean == null ? null : newslistBean.get(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news, parent, false);
        ItemHolder holder = new ItemHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        News.NewslistBean bean = newslistBean.get(position);
        ((ItemHolder)holder).tvTitle.setText(bean.getTitle());
        ((ItemHolder)holder).tvDesc.setText(bean.getDescription());
        ((ItemHolder)holder).tvDate.setText(bean.getCtime());
        if (bean.getPicUrl().trim().length() != 0){
            Picasso.with(context)
                .load(bean.getPicUrl())
                .placeholder(R.drawable.logo)
                .into(((ItemHolder)holder).ivNews);
        }
    }

    @Override
    public int getItemCount() {
        return newslistBean == null ? 0 : newslistBean.size();
    }



    public interface OnItemClickListener {
        void onItemClick(View view,int position);
    }

    public interface OnLoadMoreListener{
        void onLoadMore();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListeren = onItemClickListener;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener loadMoreListener){
        this.loadMoreListener = loadMoreListener;
    }

    public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.iv_news)
        ImageView ivNews;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_desc)
        TextView tvDesc;
        @BindView(R.id.tv_date)
        TextView tvDate;

        public ItemHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (onItemClickListeren != null){
                onItemClickListeren.onItemClick(view,this.getPosition());
            }
        }
    }

}
