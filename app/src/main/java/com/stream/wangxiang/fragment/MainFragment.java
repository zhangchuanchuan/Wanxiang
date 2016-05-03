package com.stream.wangxiang.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stream.wangxiang.event.RefreshSubscribeEvent;
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
    public static final int TAB_INDEX_SUBSCRIBE = 4;

    private BaseFragment currentFragment;


    private BaseFragment mHomeFragment;
    private BaseFragment mCategoryFragment;
    private BaseFragment mLocalFragment;
    private BaseFragment mMyselfFragment;

    private BaseFragment mSubscribeFragment;


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
            case TAB_INDEX_SUBSCRIBE:
                goToSubscribe(event.getFromTabIndex());
        }

    }

    private void goToSubscribe(int fromIndex) {
        if(mSubscribeFragment == null){
            mSubscribeFragment = new SubscribeFragment();
        }
        ((SubscribeFragment)mSubscribeFragment).setmFromTabIndex(fromIndex);
        switchFragment(mSubscribeFragment);

    }

    private void goToHome() {
        if(mHomeFragment == null){
            mHomeFragment = new HomeFragment();
        }
        switchFragment(mHomeFragment);
    }


    private void goToCategory(){
        if(mCategoryFragment == null){
            mCategoryFragment = new CategoryFragment();
        }

        switchFragment(mCategoryFragment);
        EventBus.getDefault().post(new RefreshSubscribeEvent());

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
                if(currentFragment.equals(mSubscribeFragment)) {
                    getFragmentManager().beginTransaction().show(toFragment).remove(currentFragment).commit();
                }else{
                    getFragmentManager().beginTransaction().show(toFragment).hide(currentFragment).commit();
                }
            } else {
                if(currentFragment.equals(mSubscribeFragment)) {
                    getFragmentManager().beginTransaction().remove(currentFragment).add(R.id.main_fragment, toFragment).commit();

                }else{
                    getFragmentManager().beginTransaction().hide(currentFragment).add(R.id.main_fragment, toFragment).commit();
                }
            }
            this.currentFragment = toFragment;
        }

    }


}
