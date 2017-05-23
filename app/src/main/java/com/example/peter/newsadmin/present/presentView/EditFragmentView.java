package com.example.peter.newsadmin.present.presentView;

import com.example.peter.newsadmin.base.BaseView;
import com.example.peter.newsadmin.model.NewsModel;

import java.util.List;

/**
 * Created by cdxy_ on 2017/4/8.
 */

public interface EditFragmentView extends BaseView {
       void updateMsg(List<NewsModel> news);

}
