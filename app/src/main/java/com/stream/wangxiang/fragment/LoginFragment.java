package com.stream.wangxiang.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.stream.wangxiang.event.BaseEvent;
import com.stream.wangxiang.event.GetSettingsListEvent;
import com.stream.wangxiang.event.GetUserListEvent;
import com.stream.wangxiang.event.LoginSuccessEvent;
import com.stream.wangxiang.event.RegisterEvent;
import com.stream.wangxiang.net.GetBmobData;
import com.stream.wangxiang.net.SetBmobData;
import com.stream.wangxiang.utils.AppUtils;
import com.stream.wangxiang.utils.LoginUtils;
import com.stream.wangxiang.utils.SettingUtils;
import com.stream.wangxiang.utils.SharedPreferenceUtils;
import com.stream.wangxiang.utils.StringUtils;
import com.stream.wangxiang.vo.Settings;
import com.stream.wangxiang.vo.User;
import com.stream.wanxiang.R;

import org.json.JSONArray;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindCallback;
import de.greenrobot.event.EventBus;

/**
 * 登录fragment
 * Created by 张川川 on 2016/5/4.
 */
public class LoginFragment extends BaseFragment implements View.OnClickListener{

    private EditText etUsername;
    private EditText etPassword;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        ImageView backImg = ((ImageView) view.findViewById(R.id.iv_back));
        backImg.setOnClickListener(this);

        etUsername = (EditText) view.findViewById(R.id.et_user_name);
        etUsername.setText(SharedPreferenceUtils.getString(SharedPreferenceUtils.KEY_FOR_USER_NAME, ""));

        etPassword = (EditText) view.findViewById(R.id.et_password);

        TextView login = (TextView) view.findViewById(R.id.login_button);
        TextView register = (TextView) view.findViewById(R.id.register_button);
        login.setOnClickListener(this);
        register.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                if(isAdded()){
                    getActivity().finish();
                }
                break;
            case R.id.login_button:
                doLogin();
                break;
            case R.id.register_button:
                doRegister();
                break;

        }
    }

    private void doLogin() {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        if(StringUtils.isNullOrEmpty(username) || StringUtils.isNullOrEmpty(password)){
            AppUtils.showShortToast("用户名或密码没有填写哦");
            return;
        }
        setOnBusy(true, false);
        GetBmobData.getUserList();

    }


    private void doRegister() {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        if(StringUtils.isNullOrEmpty(username) || StringUtils.isNullOrEmpty(password)){
            AppUtils.showShortToast("用户名或密码没有填写哦");
            return;
        }
        setOnBusy(true, false);
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        SetBmobData.saveUser(user);

    }


    public void onEventMainThread(GetUserListEvent event){
        if(event.isSucceed()){
            List<User> list = event.getUserList();
            for(User user : list){
                if(user.getUsername().equals(etUsername.getText().toString()) && user.getPassword().equals(etPassword.getText().toString())){
                    LoginUtils.isLogin = true;
                    LoginUtils.bmob_user = user;

                    // 保存到本地，用作自动登录
                    SettingUtils.saveUserToLocal(user.getUsername(), user.getPassword());

                    GetBmobData.getSettingList();
                    break;
                }
            }

            if(!LoginUtils.isLogin){
                setOnBusy(false);
                AppUtils.showShortToast("用户名或密码错误");
            }

        }else{
            setOnBusy(false);
        }
    }

    public void onEventMainThread(GetSettingsListEvent event){
        setOnBusy(false);
        List<Settings> settingsList =  event.getSettingsList();
        for(Settings setting : settingsList){
            // 存在设置
            if(setting.getUsername().equals(LoginUtils.bmob_user.getUsername())){
                // 导入设置
                SettingUtils.loadBmobSetting(setting);
                LoginUtils.bmob_settings = setting;
                break;
            }
        }

        if(LoginUtils.bmob_settings == null){
            // 保存设置到bmob
            Settings settings = new Settings();
            settings.setUsername(LoginUtils.bmob_user.getUsername());
            settings.setLocal_city(SharedPreferenceUtils.getString(SharedPreferenceUtils.KEY_FOR_LOCAL_CITY, SettingUtils.defaultCity));
            settings.setSubscribe_category(SettingUtils.getSubscribeCategoryList());
            SetBmobData.saveSettings(settings);
        }

        // 登录成功
        AppUtils.showShortToast("登录成功");
        EventBus.getDefault().post(new LoginSuccessEvent());
        getActivity().finish();

    }

    public void onEventMainThread(RegisterEvent event){
        setOnBusy(false);
        if(event.isSucceed()){
            AppUtils.showShortToast("注册成功，正在登录...");
            doLogin();
        }else{
            AppUtils.showShortToast("用户名已存在");
        }
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
