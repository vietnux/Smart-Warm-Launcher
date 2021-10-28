package com.launcher.smartwarm.interfaces;

import com.launcher.smartwarm.model.App;

import java.util.List;

public interface AppUpdateListener {
    boolean onAppUpdated(List<App> apps);
}
