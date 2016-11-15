package com.allong.mylauncher;

import android.graphics.drawable.Drawable;

/**
 * Created by yangjian on 2016/10/21 13:06.
 */
public class AppDetail {
    CharSequence Label;
    CharSequence name;
    Drawable icon;

    public CharSequence getLabel() {
        return Label;
    }

    public void setLabel(CharSequence label) {
        Label = label;
    }

    public CharSequence getName() {
        return name;
    }

    public void setName(CharSequence name) {
        this.name = name;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }
}
