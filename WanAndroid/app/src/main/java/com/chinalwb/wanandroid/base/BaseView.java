package com.chinalwb.wanandroid.base;

public interface BaseView<T> {

    void setPresenter(T presenter);

    void showError(Throwable error);
}
