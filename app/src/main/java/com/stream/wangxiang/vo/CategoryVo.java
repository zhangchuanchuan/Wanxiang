package com.stream.wangxiang.vo;

/**
 * 类别
 * Created by 张川川 on 2016/5/1.
 */
public class CategoryVo {
    private String name;

    private boolean isChcked;

    private String postId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChcked() {
        return isChcked;
    }

    public void setChcked(boolean chcked) {
        isChcked = chcked;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }
}
