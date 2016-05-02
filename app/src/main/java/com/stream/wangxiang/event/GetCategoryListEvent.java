package com.stream.wangxiang.event;

import com.stream.wangxiang.vo.CategoryVo;

import java.util.List;

/**
 * 获取分类列表的event
 * Created by 张川川 on 2016/5/2.
 */
public class GetCategoryListEvent extends BaseEvent {
    private List<CategoryVo> categoryVoList;

    public List<CategoryVo> getCategoryVoList() {
        return categoryVoList;
    }

    public void setCategoryVoList(List<CategoryVo> categoryVoList) {
        this.categoryVoList = categoryVoList;
    }
}
