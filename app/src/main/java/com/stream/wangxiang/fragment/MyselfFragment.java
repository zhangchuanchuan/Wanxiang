package com.stream.wangxiang.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stream.wangxiang.activity.ChooseAreaActivity;
import com.stream.wangxiang.activity.LoginActivity;
import com.stream.wangxiang.event.SelectTabEvent;
import com.stream.wangxiang.utils.LoginUtils;
import com.stream.wanxiang.R;

import org.w3c.dom.Text;

import de.greenrobot.event.EventBus;

/**
 * 我的页面
 * Created by 张川川 on 2016/4/22.
 */
public class MyselfFragment extends BaseFragment implements View.OnClickListener{

    private TextView mName;
    private TextView mButton;
    private TextView mSetSubscribe;
    private TextView mSetLocalCity;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myself, container, false);

        findView(view);

        return view;
    }

    private void findView(View view) {
        mName = (TextView) view.findViewById(R.id.my_user_name);
        mButton = (TextView) view.findViewById(R.id.my_button);

        setTextMessage();

        mSetSubscribe = (TextView) view.findViewById(R.id.set_subscribe);
        mSetLocalCity = (TextView) view.findViewById(R.id.set_local_city);

        mButton.setOnClickListener(this);
        mSetSubscribe.setOnClickListener(this);
        mSetLocalCity.setOnClickListener(this);
    }

    private void setTextMessage() {
        if(LoginUtils.isLogin){
            mName.setText(LoginUtils.username+", 您好！");
            mButton.setText("注销");
        }else{
            mName.setText("您还未登录");
            mButton.setText("登录");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.my_button:
                if(LoginUtils.isLogin){
                    // TODO dialog实现
                    LoginUtils.isLogin = false;
                    setTextMessage();
                }else{
                    Intent intent = new Intent();
                    intent.setClass(getContext(), LoginActivity.class);
                    getContext().startActivity(intent);
                }
                break;
            case R.id.set_local_city:
                Intent intent = new Intent();
                intent.setClass(getContext(), ChooseAreaActivity.class);
                getContext().startActivity(intent);
                break;
            case R.id.set_subscribe:
                SelectTabEvent event = new SelectTabEvent(MainFragment.TAB_INDEX_SUBSCRIBE);
                event.setFromTabIndex(MainFragment.TAB_INDEX_MYSELF);
                EventBus.getDefault().post(event);
                break;
        }
    }
}
