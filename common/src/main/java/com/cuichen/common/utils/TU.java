package com.cuichen.common.utils;

import android.widget.Toast;
import com.cuichen.common.base.BaseApplication;

public class TU {
    private static Toast toast = null;

    public static void showToast(String tip) {
        if (toast == null) {
            toast = Toast.makeText(BaseApplication.Companion.get(), tip, Toast.LENGTH_SHORT);
        } else {
            toast.setText(tip);
        }
        toast.show();
    }
}
