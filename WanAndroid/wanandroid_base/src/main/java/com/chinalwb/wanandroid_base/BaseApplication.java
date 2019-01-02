package com.chinalwb.wanandroid_base;

import android.app.Application;

// TODO annotation - dynamic inject the service data into main app
public abstract class BaseApplication extends Application {

    public abstract void initServiceData();
}
