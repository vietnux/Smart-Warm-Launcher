package com.launcher.smartwarm.viewutil;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.View;
import android.widget.ListAdapter;

import com.afollestad.materialdialogs.MaterialDialog;
import com.launcher.smartwarm.R;
import com.launcher.smartwarm.activity.HomeActivity;
import com.launcher.smartwarm.model.App;
import com.launcher.smartwarm.model.Item;
import com.launcher.smartwarm.util.AppManager;
import com.launcher.smartwarm.util.AppSettings;
import com.launcher.smartwarm.util.Tool;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.dialog.MaterialDialogs;
import com.mikepenz.fastadapter.IAdapter;
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DialogHelper {
    public static void editItemDialog(String title, String defaultText, Context c, final OnItemEditListener listener) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(c);
        builder.title(title)
                .positiveText(android.R.string.ok)
                .negativeText(android.R.string.cancel)
                .input(null, defaultText, new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                        listener.itemLabel(input.toString());
                    }
                }).show();

//        new MaterialAlertDialogBuilder(c)
//                .setTitle(title)
//                .setMessage( defaultText )
//                .setPositiveButton(android.R.string.ok, null)
//                .setCancelable(true)
//                .show();
    }

    public static void alertDialog(Context context, String title, String msg, MaterialDialog.SingleButtonCallback onPositive) {
//    public static void alertDialog(Context context, String title, String msg, DialogInterface.OnClickListener onPositive) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context);
        builder.title(title)
                .onPositive(onPositive)
                .content(msg)
                .negativeText(android.R.string.cancel)
                .positiveText(android.R.string.ok)
                .show();
//        new MaterialAlertDialogBuilder(context)
//                .setTitle(title)
//                .setMessage( msg )
//                .setPositiveButton(android.R.string.ok, onPositive)
//                .setCancelable(true)
//                .show();
    }

    public static void alertDialog(Context context, String title, String message, String positive, MaterialDialog.SingleButtonCallback onPositive) {
//    public static void alertDialog(Context context, String title, String message, String positive, MaterialDialog.OnClickListener onPositive) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context);
        builder.title(title)
                .onPositive(onPositive)
                .content(message)
                .negativeText(android.R.string.cancel)
                .positiveText(positive)
                .show();
//        new MaterialAlertDialogBuilder(context)
//                .setTitle(title)
//                .setMessage( message )
//                .setPositiveButton(positive, onPositive)
//                .setCancelable(true)
//                .show();
    }

    public static void selectActionDialog(final Context context, MaterialDialog.ListCallback callback) {
//    public static void selectActionDialog(final Context context, DialogInterface.OnClickListener callback) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context);
        builder.title(R.string.action)
                .items(R.array.entries__gesture_action)
                .itemsCallback(callback)
                .show();
//        new MaterialAlertDialogBuilder(context)
//                .setTitle(R.string.action)
//                .setCancelable(true)
//                .setItems(R.array.entries__gesture_action, callback)
//                .show();
    }

    public static void selectDesktopActionDialog(final Context context, MaterialDialog.ListCallback callback) {
//    public static void selectDesktopActionDialog(final Context context, DialogInterface.OnClickListener callback) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context);
        builder.title(R.string.action)
                .items(R.array.entries__desktop_actions)
                .itemsCallback(callback)
                .show();
//        new MaterialAlertDialogBuilder(context)
//                .setTitle(R.string.action)
//                .setCancelable(true)
//                .setItems(R.array.entries__desktop_actions, callback)
//                .show();
    }

    public static void selectGestureDialog(final Context context, String title, MaterialDialog.ListCallback callback) {
//    public static void selectGestureDialog(final Context context, String title, DialogInterface.OnClickListener callback) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context);
        builder.title(title)
                .items(R.array.entries__gesture)
                .itemsCallback(callback)
                .show();
//            new MaterialAlertDialogBuilder(context)
//                    .setTitle(R.string.action)
//                    .setCancelable(true)
//                    .setItems(R.array.entries__gesture, callback)
//                    .show();
    }

    public static void selectAppDialog(final Context context, final OnAppSelectedListener onAppSelectedListener) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context);
        FastItemAdapter<IconLabelItem> fastItemAdapter = new FastItemAdapter<>();
        builder.title(R.string.select_app)
                .adapter(fastItemAdapter, new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false))
                .negativeText(android.R.string.cancel);
        final MaterialDialog dialog = builder.build();
//        final MaterialAlertDialogBuilder dialog = new MaterialAlertDialogBuilder(context)
//                .setTitle(R.string.select_app)
//                .setAdapter( (ListAdapter) fastItemAdapter,
//                        (DialogInterface.OnClickListener) new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false))
//                .setCancelable(true);
//                .setItems(R.array.entries__gesture, callback)
//                .show();


        List<IconLabelItem> items = new ArrayList<>();
        final List<App> apps = AppManager.getInstance(context).getApps();
        for (int i = 0; i < apps.size(); i++) {
            items.add(new IconLabelItem(apps.get(i).getIcon(), apps.get(i).getLabel())
                    .withIconSize(context, 50)
                    .withIsAppLauncher(true)
                    .withIconGravity(Gravity.START)
                    .withIconPadding(context, 8));
        }
        fastItemAdapter.set(items);
        fastItemAdapter.withOnClickListener(new com.mikepenz.fastadapter.listeners.OnClickListener<IconLabelItem>() {
            @Override
            public boolean onClick(View v, IAdapter<IconLabelItem> adapter, IconLabelItem item, int position) {
                if (onAppSelectedListener != null) {
                    onAppSelectedListener.onAppSelected(apps.get(position));
                }
//                dialog.dismiss();
                dialog.setCancelable(true);
                return true;
            }
        });
        dialog.show();
    }

    public static void startPickIconPackIntent(final Context context) {
        PackageManager packageManager = context.getPackageManager();
        Activity activity = (Activity) context;
        AppManager appManager = AppManager.getInstance(context);
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
//        intent.addCategory("com.anddoes.launcher.THEME");
        intent.addCategory(Intent.CATEGORY_LAUNCHER);

        FastItemAdapter<IconLabelItem> fastItemAdapter = new FastItemAdapter<>();

        final List<ResolveInfo> resolveInfos = packageManager.queryIntentActivities(intent, 0); //
//        final List<ResolveInfo> resolveInfos = packageManager.queryIntentActivities(intent, PackageManager.GET_META_DATA); //
        Collections.sort(resolveInfos, new ResolveInfo.DisplayNameComparator(packageManager));
//        final MaterialAlertDialogBuilder dialog = new MaterialAlertDialogBuilder(activity)
//                .setAdapter( fastItemAdapter, null)
//                .setTitle(activity.getString(R.string.select_icon_pack));
//                .build();
        final MaterialDialog dialog = new MaterialDialog.Builder(context)
                .adapter(fastItemAdapter, null)
                .title((activity.getString(R.string.select_icon_pack)))
                .build();
//        dialog.show();
        fastItemAdapter.add(new IconLabelItem(activity, R.drawable.ic_launcher, R.string.default_icons)
                .withIconPadding(context, 16)
                .withIconGravity(Gravity.START)
                .withOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        appManager._recreateAfterGettingApps = true;
                        AppSettings.get().setIconPack("");
                        appManager.getAllApps();
                        dialog.dismiss();
//                        dialog.setCancelable(true);
                    }
                }));
        for (int i = 0; i < resolveInfos.size(); i++) {
            final int mI = i;
            fastItemAdapter.add(new IconLabelItem(resolveInfos.get(i).loadIcon(packageManager), resolveInfos.get(i).loadLabel(packageManager).toString())
                    .withIconPadding(context, 16)
                    .withIconSize(context, 50)
                    .withIsAppLauncher(true)
                    .withIconGravity(Gravity.START)
                    .withOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                                appManager._recreateAfterGettingApps = true;
                                AppSettings.get().setIconPack(resolveInfos.get(mI).activityInfo.packageName);
                                appManager.getAllApps();
                                dialog.dismiss();
//                                dialog.setCancelable(true);
                            } else {
                                Tool.toast(context, (activity.getString(R.string.toast_icon_pack_error)));
                                ActivityCompat.requestPermissions(HomeActivity.Companion.getLauncher(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, HomeActivity.REQUEST_PERMISSION_STORAGE);
                            }
                        }
                    }));
        }
        dialog.show();
    }

    public static void deletePackageDialog(Context context, Item item) {
        if (item.getType() == Item.Type.APP) {
            try {
                Uri packageURI = Uri.parse("package:" + item.getIntent().getComponent().getPackageName());
                Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, packageURI);
                context.startActivity(uninstallIntent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public interface OnAppSelectedListener {
        void onAppSelected(App app);
    }

    public interface OnItemEditListener {
        void itemLabel(String label);
    }
}
