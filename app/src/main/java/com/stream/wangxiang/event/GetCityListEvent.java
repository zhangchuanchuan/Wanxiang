package com.stream.wangxiang.event;

import com.stream.wangxiang.vo.Cities;

import java.util.List;

/**
 * Created by 张川川 on 2016/4/24.
 */
public class GetCityListEvent extends  BaseEvent{
    private List<Cities> categoryCityList;

    public List<Cities> getCategoryCityList() {
        return categoryCityList;
    }

    public void setCategoryCityList(List<Cities> categoryCityList) {
        this.categoryCityList = categoryCityList;
    }
}
