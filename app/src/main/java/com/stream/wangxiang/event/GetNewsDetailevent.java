package com.stream.wangxiang.event;

import com.stream.wangxiang.vo.NewsDetailVo;

/**
 * 新闻详情的event
 * Created by 张川川 on 2016/4/28.
 */
public class GetNewsDetailEvent extends BaseEvent {
    private NewsDetailVo detailVo;

    public NewsDetailVo getDetailVo() {
        return detailVo;
    }

    public void setDetailVo(NewsDetailVo detailVo) {
        this.detailVo = detailVo;
    }
}
