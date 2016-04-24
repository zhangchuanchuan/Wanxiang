package com.stream.wangxiang.event;

import com.stream.wangxiang.vo.NewsItem;

import java.util.List;

/**
 * Created by 张川川 on 2016/4/24.
 */
public class RefreshNewsListEvent extends BaseEvent {

    private List<NewsItem> newsItemList;

    public List<NewsItem> getNewsItemList() {
        return newsItemList;
    }

    public void setNewsItemList(List<NewsItem> newsItemList) {
        this.newsItemList = newsItemList;
    }


}
