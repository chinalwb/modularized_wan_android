package com.chinalwb.wanandroid_base.services;

import android.view.MenuItem;

public class NavigationViewItem {

    private int groupId;
    private int itemId;
    private int order;
    private CharSequence title;
    private int iconRes;
    private boolean checked;
    private MenuItem.OnMenuItemClickListener clickListener;

    public NavigationViewItem(int groupId, int itemId, int order, CharSequence title) {
        this.groupId = groupId;
        this.itemId = itemId;
        this.order = order;
        this.title = title;
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

    public MenuItem.OnMenuItemClickListener getClickListener() {
        return clickListener;
    }

    public void setClickListener(MenuItem.OnMenuItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
