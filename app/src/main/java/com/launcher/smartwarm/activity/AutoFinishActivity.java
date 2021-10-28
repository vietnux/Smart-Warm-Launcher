package com.launcher.smartwarm.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;

public class AutoFinishActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        finish();
    }

    public static void start(Context c) {
        c.startActivity(new Intent(c, AutoFinishActivity.class));
    }
}
