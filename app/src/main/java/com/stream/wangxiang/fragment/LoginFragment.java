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
import com.stream.wangxiang.event.GetUserListEvent;
import com.stream.wangxiang.net.GetBmobData;
import com.stream.wangxiang.utils.AppUtils;
import com.stream.wangxiang.utils.LoginUtils;
import com.stream.wangxiang.utils.StringUtils;
import com.stream.wangxiang.vo.User;
import com.stream.wanxiang.R;

import org.json.JSONArray;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindCallback;

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
        }

        GetBmobData.getUserList();

    }


    private void doRegister() {

    }


    public void onEventMainThread(GetUserListEvent event){
        if(event.isSucceed()){
            List<User> list = event.getUserList();
            for(User user : list){
                if(user.getUsername().equals(etUsername.getText().toString()) && user.getPassword().equals(etPassword.getText().toString())){
                    LoginUtils.isLogin = true;
                    LoginUtils.bmob_user = user;

                    return;
                }
            }

            if(LoginUtils.isLogin){

                getActivity().finish();
            }

        }
    }




}
