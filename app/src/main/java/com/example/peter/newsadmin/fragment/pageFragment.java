package com.example.peter.newsadmin.fragment;

import android.content.Intent;
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
import com.example.peter.newsadmin.activity.NewsActivity;
import com.example.peter.newsadmin.base.BaseFragment;
import com.example.peter.newsadmin.model.NewsModel;
import com.example.peter.newsadmin.present.presentImpl.PageFragmentPresenter;
import com.example.peter.newsadmin.present.presentView.PageFragmentView;
import com.example.peter.newsadmin.utils.StringUtil;
import com.example.peter.newsadmin.view.MyItemClickListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by Administrator on 2015/7/30.
 */
public class pageFragment extends BaseFragment implements PageFragmentView,SwipeRefreshLayout.OnRefreshListener,MyItemClickListener{

    @BindView(R.id.news_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.fragment_swipe)SwipeRefreshLayout swipeRefresh;
    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;
    private List<NewsModel> news;
    private PageFragmentPresenter presenter = new PageFragmentPresenter(this);
    private static final int REFRESH_COMPLETE = 0X110;
    private Map<Integer,String> map=new HashMap<>();
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
        adapter.setOnItemClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        adapter.notifyItemRangeChanged(0,news.size());



    }


    @Override
    protected void initCommonLogic(View view) {
        super.initCommonLogic(view);
        initMap();
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

    private void initMap(){
        map.put(1,"ACG");
        map.put(2,"游戏");
        map.put(3,"社会");
        map.put(4,"娱乐");
        map.put(5,"科技");

    }
    @Override
    public void onRefresh() {
        mHandler.sendEmptyMessageDelayed(REFRESH_COMPLETE,2000);

    }

    @Override
    public void onItemClick(View view, int postion) {
        showInfo("点击事件响应"+news.get(postion).getTitle());
        Intent intent=new Intent(getActivity(),NewsActivity.class);
        intent.putExtra("nid",String.valueOf(news.get(postion).getId()));
        startActivity(intent);
    }


    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

        private MyItemClickListener mItemClickListener;

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    getActivity()).inflate(R.layout.public_news_item, parent,
                    false),this.mItemClickListener);
            holder.setIsRecyclable(false);
            return holder;
        }

        public void setOnItemClickListener(MyItemClickListener listener) {
            this.mItemClickListener = listener;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {

            Glide.with(getActivity()).load(StringUtil.getPhotoUrl(news.get(position).getCover())).asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageView);
//            holder.imageView.setImageBitmap(news.get(position).getImage());
            holder.title.setText(news.get(position).getTitle());
            holder.type.setText(map.get(news.get(position).getType()));
        }

        @Override
        public int getItemCount() {
            return news.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            ImageView imageView;
            TextView title;
            TextView type;
            private MyItemClickListener mListener;

            public MyViewHolder(View view,MyItemClickListener listener) {
                super(view);
                imageView = (ImageView) view.findViewById(R.id.news_image);
                title = (TextView) view.findViewById(R.id.news_title);
                type = (TextView) view.findViewById(R.id.news_type);
                view.setOnClickListener(this);
                this.mListener=listener;
            }


            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(v, getPosition());
                }
            }
        }

    }
}

