package com.chinalwb.wanandroid_base.services;

import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.chinalwb.wanandroid_base.Util;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class NavigationViewItem {

    private int groupId;
    private int itemId;
    private int order;
    private CharSequence title;
    private int iconRes;
    private boolean checked;
    private Fragment fragment;

    public NavigationViewItem(int groupId, int itemId, int order, CharSequence title) {
        this.groupId = groupId;
        this.itemId = itemId;
        this.order = order;
        this.title = title;
    }

    /**
     * Click the item to show fragment in fragment container
     * @param fragmentManager
     * @param fragmentContainerId
     */
    public void showFragment(FragmentManager fragmentManager, int fragmentContainerId) {
        if (this.fragment == null) {
            Log.e("xx", "Fragment is null!");
            return;
        }

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(fragmentContainerId, fragment);
        fragmentTransaction.commit();
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public CharSequence getTitle() {
        return title;
    }

    public void setTitle(CharSequence title) {
        this.title = title;
    }

    public int getIconRes() {
        return iconRes;
    }

    public void setIconRes(int iconRes) {
        this.iconRes = iconRes;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }
}
