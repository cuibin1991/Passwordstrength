package com.example.cuibin.passwordstrength.component;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;

/**
 * Created by cuibin on 17/11/16.
 */

public class AlertDialogBuilder {

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public static AlertDialog.Builder newBuilder(Context context) {
        AlertDialog.Builder builder;
        if (VERSION.SDK_INT < VERSION_CODES.HONEYCOMB) {
            builder = new AlertDialog.Builder(context);
        } else if (VERSION.SDK_INT < VERSION_CODES.ICE_CREAM_SANDWICH) {
            builder = new AlertDialog.Builder(context, AlertDialog.THEME_HOLO_LIGHT);
        } else {
            builder = new AlertDialog.Builder(context, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
        }
        return builder;
    }
}