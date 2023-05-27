package com.example.studyandroid.skin;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.collection.ArrayMap;
import androidx.core.view.LayoutInflaterCompat;

import com.example.studyandroid.skin.utils.SkinThemeUtils;

import java.lang.reflect.Field;
import java.util.Observable;

public class ApplicationActivityLifecycle implements Application.ActivityLifecycleCallbacks {

    private Observable mObserable;
    private ArrayMap<Activity, SkinLayoutInflaterFactory> mLayoutInflaterFactories = new
            ArrayMap<>();

    public ApplicationActivityLifecycle(Observable observable) {
        mObserable = observable;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        /**
         *  更新状态栏
         */
        SkinThemeUtils.updateStatusBarColor(activity);

        /**
         *  更新布局视图
         */
        //获得Activity的布局加载器
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        try {
            //Android 布局加载器 使用 mFactorySet 标记是否设置过Factory
            //如设置过抛出一次
            //设置 mFactorySet 标签为false
            Class<LayoutInflaterCompat> compatClass = LayoutInflaterCompat.class;
            Class<LayoutInflater> inflaterClass = LayoutInflater.class;
            try {
                Field sCheckedField = compatClass.getDeclaredField("sCheckedField");
                sCheckedField.setAccessible(true);
                sCheckedField.setBoolean(layoutInflater, false);
                Field mFactory = inflaterClass.getDeclaredField("mFactory");
                mFactory.setAccessible(true);
                Field mFactory2 = inflaterClass.getDeclaredField("mFactory2");
                mFactory2.setAccessible(true);
                SkinLayoutInflaterFactory factory = new SkinLayoutInflaterFactory(activity);
                mFactory2.set(layoutInflater, factory);
                mFactory.set(layoutInflater, factory);
                mLayoutInflaterFactories.put(activity, factory);
                mObserable.addObserver(factory);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        SkinLayoutInflaterFactory observer = mLayoutInflaterFactories.remove(activity);
        SkinManager.getInstance().deleteObserver(observer);
    }
}
