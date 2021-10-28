package com.launcher.smartwarm.activity.homeparts;

import android.graphics.Point;

import com.launcher.smartwarm.R;
import com.launcher.smartwarm.activity.HomeActivity;
import com.launcher.smartwarm.interfaces.DialogListener;
import com.launcher.smartwarm.manager.Setup;
import com.launcher.smartwarm.model.Item;
import com.launcher.smartwarm.util.Tool;

public class HpDesktopPickAction implements DialogListener.OnActionDialogListener {
    private HomeActivity _homeActivity;

    public HpDesktopPickAction(HomeActivity homeActivity) {
        _homeActivity = homeActivity;
    }

    public void onPickDesktopAction() {
        Setup.eventHandler().showPickAction(_homeActivity, this);
    }

    @Override
    public void onAdd(int type) {
        Point pos = _homeActivity.getDesktop().getCurrentPage().findFreeSpace();
        if (pos != null) {
            _homeActivity.getDesktop().addItemToCell(Item.newActionItem(type), pos.x, pos.y);
        } else {
            Tool.toast(_homeActivity, R.string.toast_not_enough_space);
        }
    }
}
