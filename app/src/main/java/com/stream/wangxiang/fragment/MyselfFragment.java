package com.stream.wangxiang.fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stream.wangxiang.activity.ChooseAreaActivity;
import com.stream.wangxiang.activity.LoginActivity;
import com.stream.wangxiang.event.LoginSuccessEvent;
import com.stream.wangxiang.event.SelectTabEvent;
import com.stream.wangxiang.utils.LoginUtils;
import com.stream.wangxiang.utils.SettingUtils;
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

        TextView mSetSubscribe = (TextView) view.findViewById(R.id.set_subscribe);
        TextView mSetLocalCity = (TextView) view.findViewById(R.id.set_local_city);

        mButton.setOnClickListener(this);
        mSetSubscribe.setOnClickListener(this);
        mSetLocalCity.setOnClickListener(this);
    }

    private void setTextMessage() {
        if(LoginUtils.isLogin){
            mName.setText(LoginUtils.bmob_user.getUsername()+", 您好！");
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
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
                    dialogBuilder.setTitle("确定要注销吗？").setItems(new String[]{"确定", "取消"}, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which){
                                case 1:
                                    dialog.dismiss();
                                    break;
                                case 0:
                                    SettingUtils.setLoginOut();
                                    setTextMessage();
                                    dialog.dismiss();
                                    break;
                            }
                        }
                    }).create().show();

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

    public void onEventMainThread(LoginSuccessEvent event){
        setTextMessage();
    }

}
