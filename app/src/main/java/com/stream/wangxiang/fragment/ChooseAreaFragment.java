package com.stream.wangxiang.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.stream.wangxiang.utils.SettingUtils;
import com.stream.wangxiang.utils.SharedPreferenceUtils;
import com.stream.wangxiang.view.CityListView;
import com.stream.wanxiang.R;

/**
 * 选取城市的fragment
 * Created by 张川川 on 2016/5/3.
 */
public class ChooseAreaFragment extends BaseFragment implements View.OnClickListener{

    private ImageView mImgBack;
    private TextView mTvCity;

    private CityListView cityListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choose_area, container, false);
        mImgBack = (ImageView)view.findViewById(R.id.iv_back);
        mImgBack.setOnClickListener(this);
        mTvCity = (TextView) view.findViewById(R.id.tv_location_name);
        setSettingCityShow();



        return  view;
    }

    private void setSettingCityShow(){
        String city = SharedPreferenceUtils.getString(SharedPreferenceUtils.KEY_FOR_LOCAL_CITY, SettingUtils.defaultCity);
        mTvCity.setText(city);
        mTvCity.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                if(isAdded()){
                    getActivity().finish();
                }
                break;
        }
    }
}
