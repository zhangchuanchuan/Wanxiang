package com.stream.wangxiang.vo;

/**
 * 新闻列表条目的io
 * Created by 张川川 on 2016/4/23.
 */
public class NewsItem {

    // 新闻的id
    private String postid;
    // 原文
    private String url_3w;

    //评论数
    private int votecount;
    private int replyCount;

    private String skipID;

    // 副标题
    private String ltitle;

    // 摘要
    private String digest;
    private String skipType;

    private String url;
    private String speciallID;

    private String docid;
    // 标题
    private String title;
    private String source;
    // 优先级
    private int priority;

    private String lmodify;

    private String boardid;
    private String subtitle;

    private String imgsrc;
    private String ptime;

    private String defaultImgUrl = "http://stacdn201.ganjistatic1.com/src/image/house/noimg.gif";

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public String getUrl_3w() {
        return url_3w;
    }

    public void setUrl_3w(String url_3w) {
        this.url_3w = url_3w;
    }

    public int getVotecount() {
        return votecount;
    }

    public void setVotecount(int votecount) {
        this.votecount = votecount;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public String getSkipID() {
        return skipID;
    }

    public void setSkipID(String skipID) {
        this.skipID = skipID;
    }

    public String getLtitle() {
        return ltitle;
    }

    public void setLtitle(String ltitle) {
        this.ltitle = ltitle;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getSkipType() {
        return skipType;
    }

    public void setSkipType(String skipType) {
        this.skipType = skipType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSpeciallID() {
        return speciallID;
    }

    public void setSpeciallID(String speciallID) {
        this.speciallID = speciallID;
    }

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getLmodify() {
        return lmodify;
    }

    public void setLmodify(String lmodify) {
        this.lmodify = lmodify;
    }

    public String getBoardid() {
        return boardid;
    }

    public void setBoardid(String boardid) {
        this.boardid = boardid;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    public String getDefaultImgUrl(){
        return defaultImgUrl;
    }
}
