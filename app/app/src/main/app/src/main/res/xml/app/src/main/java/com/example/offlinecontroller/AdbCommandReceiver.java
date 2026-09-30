java
package com.example.offlinecontroller;

import android.app.admin.DeviceAdminReceiver;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.UserManager;

public class AdbCommandReceiver extends DeviceAdminReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        String action = intent.getAction();
        if (action == null) return;

        DevicePolicyManager dpm = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
        ComponentName adminComponent = new ComponentName(context, AdbCommandReceiver.class);

        if (!dpm.isDeviceOwnerApp(context.getPackageName())) return;

        if ("com.example.OFFLINE_ON".equals(action)) {
            dpm.addUserRestriction(adminComponent, UserManager.DISALLOW_FACTORY_RESET);
            dpm.addUserRestriction(adminComponent, UserManager.DISALLOW_ADD_USER);
        } else if ("com.example.OFFLINE_OFF".equals(action)) {
            dpm.clearUserRestriction(adminComponent, UserManager.DISALLOW_FACTORY_RESET);
            dpm.clearUserRestriction(adminComponent, UserManager.DISALLOW_ADD_USER);
        }
    }
}
