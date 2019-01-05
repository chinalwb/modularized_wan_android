package com.chinalwb.wanandroid_base.base;

public interface BaseView<T> {

    void setPresenter(T presenter);

    void showError(Throwable error);
}
