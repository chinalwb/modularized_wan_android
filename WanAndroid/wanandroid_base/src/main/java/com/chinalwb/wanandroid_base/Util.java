package com.chinalwb.wanandroid_base;

import android.content.Context;
import android.content.res.Resources;
import android.widget.Toast;

public class Util {
    public static void toast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static float dp2px(int dp) {
        return Resources.getSystem().getDisplayMetrics().density * dp;
    }
}
