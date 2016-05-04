package com.stream.wangxiang.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.stream.wangxiang.event.GetCityListEvent;
import com.stream.wangxiang.event.UpdateLocalCityEvent;
import com.stream.wangxiang.net.GetWeatherInfo;
import com.stream.wangxiang.utils.SettingUtils;
import com.stream.wangxiang.utils.SharedPreferenceUtils;
import com.stream.wangxiang.view.CityListView;
import com.stream.wangxiang.view.LetterListView;
import com.stream.wanxiang.R;

import de.greenrobot.event.EventBus;

/**
 * 选取城市的fragment
 * Created by 张川川 on 2016/5/3.
 */
public class ChooseAreaFragment extends BaseFragment implements View.OnClickListener{

    private ImageView mImgBack;
    private TextView mTvCity;

    private CityListView cityListView;

    private LetterListView letterListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choose_area, container, false);
        mImgBack = (ImageView)view.findViewById(R.id.iv_back);
        mImgBack.setOnClickListener(this);
        mTvCity = (TextView) view.findViewById(R.id.tv_location_name);
        letterListView = (LetterListView) view.findViewById(R.id.letter_view);
        setSettingCityShow();

        cityListView = (CityListView)view.findViewById(R.id.city_list_view);
        GetWeatherInfo.getCityList();
        setOnBusy(true);
        return  view;
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

    public void onEventMainThread(GetCityListEvent event){
        setOnBusy(false);
        if(event != null){
            if(event.getCategoryCityList() != null){
                cityListView.setLetterListView(getContext(), letterListView, event.getCategoryCityList());
            }
        }
    }

    public void onEventMainThread(UpdateLocalCityEvent event){
        if(event != null){
            if(isAdded()){
                getActivity().finish();
            }
        }
    }

}
