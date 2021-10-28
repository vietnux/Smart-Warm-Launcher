package com.launcher.smartwarm.interfaces;

import android.view.View;

import com.launcher.smartwarm.model.Item;

public interface ItemHistory {
    void setLastItem(Item item, View view);

    void revertLastItem();

    void consumeLastItem();
}
