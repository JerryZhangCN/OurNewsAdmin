package com.example.peter.newsadmin.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
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
import com.coorchice.library.SuperTextView;
import com.example.peter.newsadmin.R;
import com.example.peter.newsadmin.activity.NewsActivity;
import com.example.peter.newsadmin.base.BaseFragment;
import com.example.peter.newsadmin.model.NewsModel;
import com.example.peter.newsadmin.model.User;
import com.example.peter.newsadmin.present.presentImpl.EditFragmentPresenter;
import com.example.peter.newsadmin.present.presentView.EditFragmentView;
import com.example.peter.newsadmin.utils.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class EditFragment extends BaseFragment implements EditFragmentView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.news_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.fragment_swipe)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.no_login)
    LinearLayout no_login;
    private boolean result = true;

    private List<NewsModel> news;
    private Map<Integer, String> map = new HashMap<>();
    private EditFragmentPresenter presenter = new EditFragmentPresenter(this);
    private static final int REFRESH_COMPLETE = 0X110;
    private int time = 60;
    private int count = 0;
    public EditFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit, container, false);
        initCommonLogic(view);
        initView();
        return view;
    }

    private void initMap() {
        map.put(1, "ACG");
        map.put(2, "游戏");
        map.put(3, "社会");
        map.put(4, "娱乐");
        map.put(5, "科技");

    }

    @Override
    protected void initCommonLogic(View view) {
        super.initCommonLogic(view);
        swipeRefresh.setOnRefreshListener(this);
    }

    private void initView() {

        if (StringUtil.isEmpty(User.getInstance().getId())) {
            no_login.setVisibility(View.VISIBLE);
            return;
        } else {
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
        if(swipeRefresh.isRefreshing())
            swipeRefresh.setRefreshing(false);
        this.news = news;
        HomeAdapter adapter = new HomeAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        adapter.notifyItemRangeChanged(0, news.size());
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case REFRESH_COMPLETE:
                    presenter.getNews();
                    break;

            }
        }

        ;
    };

    @Override
    public void onRefresh() {
        mHandler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 0);

    }

    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {


        @Override
        public HomeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            HomeAdapter.MyViewHolder holder = new HomeAdapter.MyViewHolder(LayoutInflater.from(
                    getActivity()).inflate(R.layout.public_own_item, parent,
                    false));
            holder.setIsRecyclable(false);
            return holder;
        }




        @Override
        public void onBindViewHolder(final HomeAdapter.MyViewHolder holder, final int position) {

            Glide.with(getActivity()).load(StringUtil.getPhotoUrl(news.get(position).getCover())).asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageView);
//            holder.imageView.setImageBitmap(news.get(position).getImage());
            holder.title.setText(news.get(position).getTitle());
            holder.type.setText(map.get(news.get(position).getType()));
            if(news.get(position).getState()==0)
                holder.online.setText("上线");
            else
                holder.online.setText("下线");
            holder.meeting_message.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), NewsActivity.class);
                    intent.putExtra("nid", String.valueOf(news.get(position).getId()));
                    startActivity(intent);
                }
            });
            holder.online.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    presenter.changeNewsState(news.get(position).getId(),news.get(position).getState()==0?1:0);
                    showTime(holder.online,news.get(position).getState()==0?"上线":"下线");
                }
            });
            holder.pull.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    presenter.pushNew(news.get(position).getId());
                    showTime(holder.pull,"推送");
                }
            });

        }

        @Override
        public int getItemCount() {
            return news.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            TextView title;
            TextView type;
            SuperTextView online;
            SuperTextView pull;
            LinearLayout meeting_message;

            public MyViewHolder(View view) {
                super(view);
                imageView = (ImageView) view.findViewById(R.id.news_image);
                title = (TextView) view.findViewById(R.id.news_title);
                type = (TextView) view.findViewById(R.id.news_type);
                online = (SuperTextView) view.findViewById(R.id.turn_online);
                pull = (SuperTextView) view.findViewById(R.id.turn_pull);
                meeting_message=(LinearLayout)view.findViewById(R.id.meeting_messagelayout);
            }

        }

    }
    public void showTime(final SuperTextView view, final String msg) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (result) {
                    time--;
                    try {
                        Thread.sleep(1000);
                        view.post(new Runnable() {
                            @Override
                            public void run() {
                                view.setText(time+"");
                                view.setClickable(false);
                                view.setSolid(Color.parseColor("#d9d9d9"));
                            }
                        });
                        if (time <= 1) {
                            count = 0;
                            result = false;
                            view.post(new Runnable() {
                                @Override
                                public void run() {
                                    view.setText(msg);
                                    view.setClickable(true);
                                    view.setSolid(Color.parseColor("#0099FF"));
                                }
                            });
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                result = true;
                time = 60;
            }
        }).start();
    }
}
