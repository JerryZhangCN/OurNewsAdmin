package com.example.peter.newsadmin.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.peter.newsadmin.R;
import com.example.peter.newsadmin.base.BaseFragment;
import com.example.peter.newsadmin.model.NewsModel;
import com.example.peter.newsadmin.model.User;
import com.example.peter.newsadmin.present.presentImpl.EditFragmentPresenter;
import com.example.peter.newsadmin.present.presentView.EditFragmentView;
import com.example.peter.newsadmin.utils.StringUtil;
import com.example.peter.newsadmin.view.MyItemClickListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindString;
import butterknife.BindView;

public class EditFragment extends BaseFragment implements EditFragmentView,SwipeRefreshLayout.OnRefreshListener,MyItemClickListener {

    @BindView(R.id.news_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.fragment_swipe)SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.no_login)
    LinearLayout no_login;

    private List<NewsModel> news;
    private Map<Integer,String> map=new HashMap<>();
    private EditFragmentPresenter presenter=new EditFragmentPresenter(this);
    private static final int REFRESH_COMPLETE = 0X110;
    public EditFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.fragment_edit, container, false);
        initCommonLogic(view);
        initView();
        return view;
    }

    private void initMap(){
        map.put(1,"ACG");
        map.put(2,"游戏");
        map.put(3,"社会");
        map.put(4,"娱乐");
        map.put(5,"科技");

    }

    @Override
    protected void initCommonLogic(View view) {
        super.initCommonLogic(view);
        swipeRefresh.setOnRefreshListener(this);
    }

    private void initView(){

        if(StringUtil.isEmpty(User.getInstance().getId())){
            no_login.setVisibility(View.VISIBLE);
            return;
        }
        else {
            no_login.setVisibility(View.GONE);
        }
        initMap();

        presenter.getNews();
    }
    @Override
    public void renderFragment() {
       initView();
    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void dismissLoadingDialog() {

    }

    @Override
    public void showError(int type, String errorMsg) {

    }

    @Override
    public void updateMsg(List<NewsModel> news) {
        this.news = news;
        HomeAdapter adapter=new HomeAdapter();
        adapter.setOnItemClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        adapter.notifyItemRangeChanged(0,news.size());
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

    @Override
    public void onItemClick(View view, int postion) {

    }

    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

        private MyItemClickListener mItemClickListener;

        @Override
        public HomeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            HomeAdapter.MyViewHolder holder = new HomeAdapter.MyViewHolder(LayoutInflater.from(
                    getActivity()).inflate(R.layout.public_news_item, parent,
                    false),this.mItemClickListener);
            holder.setIsRecyclable(false);
            return holder;
        }



        public void setOnItemClickListener(MyItemClickListener listener) {
            this.mItemClickListener = listener;
        }

        @Override
        public void onBindViewHolder(HomeAdapter.MyViewHolder holder, int position) {

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
