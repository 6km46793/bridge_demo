package com.example.bridgeandroid.utils;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

public class SelfUtils {
    @SuppressWarnings("unused")
    public static void showMsg(Context context, String content) {
        showMsg(context, content, null);
    }
    public static void showMsg(Context context, String content, String title) {
        showMsg(context, content, title, null);
    }
    public static void showMsg(Context context, String content, String title, Integer icon) {
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setMessage(content)
                .create();
        if (null != title) {
            dialog.setTitle(title);
        }
        if (null != icon) {
            dialog.setIcon(icon);
        }
        dialog.show();
    }

    public static void log(String tag, String msg) {
        Log.v("gameUtils " + tag, msg);
    }

    // 获取应用程序名称
    @SuppressWarnings("unused")
    public static synchronized String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    // 获取应用程序版本名称信息
    @SuppressWarnings("unused")
    public static synchronized String getVersionName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    // 获取应用程序版本名称信息
    @SuppressWarnings("unused")
    public static synchronized int getVersionCode(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    // 获取应用程序版本名称信息
    @SuppressWarnings("unused")
    public static synchronized String getPackageName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.packageName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    // 获取图标 bitmap
    @SuppressWarnings("unused")
    public static synchronized Bitmap getBitmap(Context context) {
        PackageManager packageManager = null;
        ApplicationInfo applicationInfo = null;
        try {
            packageManager = context.getApplicationContext().getPackageManager();
            applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            applicationInfo = null;
        }
        Drawable d = packageManager.getApplicationIcon(applicationInfo); //xxx根据自己的情况获取drawable
        BitmapDrawable bd = (BitmapDrawable) d;
        Bitmap bm = bd.getBitmap();
        return bm;
    }

    @SuppressWarnings("unused")
    public static synchronized ActivityInfo getActivityInfo(Context context, ComponentName componentName) {
        try {
            return context.getPackageManager().getActivityInfo(componentName, PackageManager.GET_META_DATA);
            // .metaData.getString(key)
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @SuppressWarnings("unused")
    public static synchronized ApplicationInfo getAppInfo(Context context) {
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            // .metaData.getString(key)
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static String getAppParams(Context context, String paramsKey, String key) {
        String[] paramList = getAppInfo(context).metaData.getString(paramsKey).split(",");
        for (int i=0;i<paramList.length;i++) {
            if (key.equals(paramList[i].split(":")[0])) {
                return paramList[i].split(":")[1];
            }
        }
        TToast.show(context, key + "不存在");
        return key;
    }
}
