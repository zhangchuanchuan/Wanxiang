package com.stream.wangxiang.vo;

import java.util.List;

/**
 * Created by 张川川 on 2016/4/27.
 */
public class NewsDetailVo {
    private String body;

    private List<NewsImg> img;

    private String source_url;

    private String shareLink;

    private String digest;

    private String title;

    private String docid;

    // 责任编辑
    private String ec;

    private String source;

    private String ptime;

    //推荐新闻列表
    private List<RelationNews> relative;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<NewsImg> getImg() {
        return img;
    }

    public void setImg(List<NewsImg> img) {
        this.img = img;
    }

    public String getSource_url() {
        return source_url;
    }

    public void setSource_url(String source_url) {
        this.source_url = source_url;
    }

    public String getShareLink() {
        return shareLink;
    }

    public void setShareLink(String shareLink) {
        this.shareLink = shareLink;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public String getEc() {
        return ec;
    }

    public void setEc(String ec) {
        this.ec = ec;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    public List<RelationNews> getRelative() {
        return relative;
    }

    public void setRelative(List<RelationNews> relative) {
        this.relative = relative;
    }
}
