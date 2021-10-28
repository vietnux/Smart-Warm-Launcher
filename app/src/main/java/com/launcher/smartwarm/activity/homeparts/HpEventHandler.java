package com.launcher.smartwarm.activity.homeparts;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.launcher.smartwarm.interfaces.DialogListener;
import com.launcher.smartwarm.manager.Setup;
import com.launcher.smartwarm.model.Item;
import com.launcher.smartwarm.util.Definitions;
import com.launcher.smartwarm.util.LauncherAction;
import com.launcher.smartwarm.viewutil.DialogHelper;

public class HpEventHandler implements Setup.EventHandler {
    @Override
    public void showLauncherSettings(Context context) {
        LauncherAction.RunAction(LauncherAction.Action.LauncherSettings, context);
    }

    @Override
    public void showPickAction(Context context, final DialogListener.OnActionDialogListener listener) {
        DialogHelper.selectDesktopActionDialog(context, new MaterialDialog.ListCallback() {
//        DialogHelper.selectDesktopActionDialog(context, new DialogInterface.OnClickListener() {
            @Override
            public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                if (position == 0) {
                    listener.onAdd(Definitions.ACTION_LAUNCHER);
                }
            }
        });
    }

    @Override
    public void showEditDialog(Context context, Item item, final DialogListener.OnEditDialogListener listener) {
        DialogHelper.editItemDialog("Edit Item", item.getLabel(), context, new DialogHelper.OnItemEditListener() {
            @Override
            public void itemLabel(String label) {
                listener.onRename(label);
            }
        });
    }

    @Override
    public void showDeletePackageDialog(Context context, Item item) {
        DialogHelper.deletePackageDialog(context, item);
    }
}
