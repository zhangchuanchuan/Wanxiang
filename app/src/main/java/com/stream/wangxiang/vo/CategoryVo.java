package com.stream.wangxiang.vo;

/**
 * 类别
 * Created by 张川川 on 2016/5/1.
 */
public class CategoryVo {

    public static final String CAN_SHOW_CATEGORY = "manual";


    // 订阅了还是没有订阅
    private boolean isChecked;

    // 当前选择的
    private boolean isSelected;


    private String template;
    private String tname;
    private String ename;
    private String tid;



    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }


    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
