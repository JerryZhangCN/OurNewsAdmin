package com.example.peter.newsadmin.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.peter.newsadmin.R;
import com.example.peter.newsadmin.base.BaseFragment;
import com.example.peter.newsadmin.model.NewsModel;
import com.example.peter.newsadmin.present.presentImpl.PageFragmentPresenter;
import com.example.peter.newsadmin.present.presentView.PageFragmentView;
import com.example.peter.newsadmin.utils.StringUtil;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2015/7/30.
 */
public class pageFragment extends BaseFragment implements PageFragmentView,SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.news_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.fragment_swipe)SwipeRefreshLayout swipeRefresh;
    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;
    private List<NewsModel> news;
    private PageFragmentPresenter presenter = new PageFragmentPresenter(this);
    private static final int REFRESH_COMPLETE = 0X110;
//    private HomeAdapter adapter=new HomeAdapter();

    public static pageFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        pageFragment pageFragment = new pageFragment();
        pageFragment.setArguments(args);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page, container, false);
        initCommonLogic(view);
        return view;
    }

    @Override
    public void renderFragment() {
//        presenter.getNews(getArguments().getInt(ARG_PAGE));
    }

    @Override
    public void updateMsg(List<NewsModel> news) {
        swipeRefresh.setOnRefreshListener(this);
        this.news = news;
        HomeAdapter adapter=new HomeAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        adapter.notifyItemRangeChanged(0,news.size());

    }


    @Override
    protected void initCommonLogic(View view) {
        super.initCommonLogic(view);
        presenter.getNews(getArguments().getInt(ARG_PAGE));

    }


    private Handler mHandler = new Handler()
    {
        public void handleMessage(android.os.Message msg)
        {
            switch (msg.what)
            {
                case REFRESH_COMPLETE:
                    showInfo("刷新");
                    swipeRefresh.setRefreshing(false);
                    break;

            }
        };
    };

    @Override
    public void onRefresh() {
        mHandler.sendEmptyMessageDelayed(REFRESH_COMPLETE,2000);

    }

    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    getActivity()).inflate(R.layout.public_news_item, parent,
                    false));
            holder.setIsRecyclable(false);
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {

            Glide.with(getActivity()).load(StringUtil.getPhotoUrl(news.get(position).getCover())).asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageView);
//            holder.imageView.setImageBitmap(news.get(position).getImage());
            holder.title.setText(news.get(position).getTitle());
            holder.type.setText(String.valueOf(news.get(position).getType()));
        }

        @Override
        public int getItemCount() {
            return news.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            TextView title;
            TextView type;

            public MyViewHolder(View view) {
                super(view);
                imageView = (ImageView) view.findViewById(R.id.news_image);
                title = (TextView) view.findViewById(R.id.news_title);
                type = (TextView) view.findViewById(R.id.news_type);
            }
        }
    }


}
