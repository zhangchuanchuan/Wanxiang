package com.stream.wangxiang.event;

import com.stream.wangxiang.vo.Settings;

import java.util.List;

/**
 * Created by 张川川 on 2016/5/4.
 */
public class GetSettingsListEvent extends BaseEvent {
    private List<Settings> settingsList;

    public List<Settings> getSettingsList() {
        return settingsList;
    }

    public void setSettingsList(List<Settings> settingsList) {
        this.settingsList = settingsList;
    }
}
