package com.launcher.smartwarm.viewutil;

import android.view.View;

import com.launcher.smartwarm.interfaces.ItemHistory;
import com.launcher.smartwarm.model.Item;

public interface DesktopCallback extends ItemHistory {
    boolean addItemToPoint(Item item, int x, int y);

    boolean addItemToPage(Item item, int page);

    boolean addItemToCell(Item item, int x, int y);

    void removeItem(View view, boolean animate);
}
