package com.chinalwb.wanandroid_base;

import android.app.Application;

// TODO annotation - dynamic inject the service data into main app
//
// Note: (2019-01-05)
// 1. I forget why I wanted to use annotation, I only remember
//    I want to get rid of reflection and android.app.Application.
//
// 2. This can be just an interface rather than extends Application
//    For example, all modules just needs implement this interface
public abstract class BaseApplication extends Application {

    public abstract void initServiceData();
}
