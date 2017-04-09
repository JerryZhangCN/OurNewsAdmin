package com.example.peter.newsadmin.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.peter.newsadmin.R;
import com.example.peter.newsadmin.base.BaseFragment;
import com.example.peter.newsadmin.model.NewsModel;
import com.example.peter.newsadmin.present.PageFragmentPresenter;
import com.example.peter.newsadmin.present.PageFragmentView;
import com.example.peter.newsadmin.utils.StringUtil;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2015/7/30.
 */
public class pageFragment extends BaseFragment implements PageFragmentView {

    @BindView(R.id.news_recycler)
    RecyclerView recyclerView;
    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;
    private List<NewsModel> news;
    private PageFragmentPresenter presenter = new PageFragmentPresenter(this);

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

    }

    @Override
    public void updateMsg(List<NewsModel> news) {
        this.news = news;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new HomeAdapter());
    }

    @Override
    protected void initCommonLogic(View view) {
        super.initCommonLogic(view);
        presenter.getNews(getArguments().getInt(ARG_PAGE));
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

            Glide.with(getActivity()).load(StringUtil.getPhotoUrl(news.get(position).getCover())).asBitmap().into(holder.imageView);
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
