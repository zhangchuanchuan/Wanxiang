package com.stream.wangxiang.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stream.wangxiang.event.SelectTabEvent;
import com.stream.wanxiang.R;

import de.greenrobot.event.EventBus;

/**
 *
 * Created by 张川川 on 2016/4/30.
 */
public class MainFragment extends BaseFragment {

    public static final int TAB_INDEX_HOME = 0;
    public static final int TAB_INDEX_CATEGORY = 1;
    public static final int TAB_INDEX_LOCAL = 2;
    public static final int TAB_INDEX_MYSELF = 3;

    private BaseFragment currentFragment;


    private BaseFragment mHomeFragment;
    private BaseFragment mCategoryFragment;
    private BaseFragment mLocalFragment;
    private BaseFragment mMyselfFragment;


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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mHomeFragment = new HomeFragment();
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (currentFragment == null) {
            currentFragment = mHomeFragment;
            getFragmentManager().beginTransaction().add(R.id.main_fragment, currentFragment).commit();
        }
    }

    public void onEventMainThread(SelectTabEvent event){


        if(event == null){
            return;
        }

        switch(event.getTabIndex()){
            case TAB_INDEX_HOME:
                goToHome();
                break;
            case TAB_INDEX_CATEGORY:
                goToCategory();
                break;
            case TAB_INDEX_LOCAL:
                goToLocal();
                break;
            case TAB_INDEX_MYSELF:
                goToMyself();
                break;
        }

    }

    private void goToHome() {
        if(mHomeFragment == null){
            mHomeFragment = new HomeFragment();
        }
        switchFragment(mHomeFragment);
    }

    private void goToCategory() {
        if(mCategoryFragment == null){
            mCategoryFragment = new CategoryFragment();
        }
        switchFragment(mCategoryFragment);
    }


    private void goToLocal() {
        if(mLocalFragment == null){
            mLocalFragment = new LocalFragment();
        }
        switchFragment(mLocalFragment);
    }

    private void goToMyself() {
        if(mMyselfFragment == null){
            mMyselfFragment = new MyselfFragment();
        }
        switchFragment(mMyselfFragment);
    }




    private void switchFragment(BaseFragment toFragment){
        if(toFragment.equals(currentFragment)){

        }else{
            if (toFragment.isAdded()) {
                getFragmentManager().beginTransaction().show(toFragment).hide(currentFragment).commit();
            } else {
                getFragmentManager().beginTransaction().hide(currentFragment).add(R.id.main_fragment, toFragment).commit();
            }
            this.currentFragment = toFragment;
        }

    }


}
