package com.chinalwb.wanandroid.features.wx.model;

import com.chinalwb.wanandroid.main.model.Article;
import com.chinalwb.wanandroid.main.model.Data;

import java.util.List;

public class GzhListResponse {
    private List<GzhTab> data;
    private int errorCode;
    private String errorMsg;

    public List<GzhTab> getData() {
        return data;
    }

    public void setData(List<GzhTab> data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
