package com.example.peter.newsadmin.present;

import com.example.peter.newsadmin.base.BaseView;
import com.example.peter.newsadmin.model.NewsModel;

import java.util.List;

/**
 * Created by cdxy_ on 2017/4/8.
 */

public interface PageFragmentView extends BaseView {
       void updateMsg(List <NewsModel> news);

}
