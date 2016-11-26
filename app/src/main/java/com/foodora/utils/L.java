package com.foodora.utils;


import com.foodora.BuildConfig;

import android.text.TextUtils;
import android.util.Log;

/**
 * Class for logging
 * Use it instead standard android.util.Log
 */

public class L {

    private static final String TAG = Log.class.getSimpleName();
    private static final boolean LOG_ENABLED = BuildConfig.DEBUG;
    private static final int MAX_SYMBOLS_PER_ROW = 120;

    private L() {
        // utils
    }

    public static void d(String message) {
        d(TAG, message);
    }

    public static void d(String tag, String message) {
        if (isLogEnable(tag, message)) {
            for (int i = 0; i <= message.length() / MAX_SYMBOLS_PER_ROW; i++) {
                int start = i * MAX_SYMBOLS_PER_ROW;
                int end = (i + 1) * MAX_SYMBOLS_PER_ROW;
                end = end > message.length() ? message.length() : end;
                Log.d(tag, message.substring(start, end));
            }
        }
    }

    public static void e(String message, Throwable e) {
        e(TAG, message, e);
    }

    public static void e(String tag, String message) {
        if (isLogEnable(tag, message)) {
            Log.e(tag, message);
        }
    }

    public static void e(String tag, String message, Throwable e) {
        if (isLogEnable(tag, message)) {
            Log.e(tag, message, e);
        }
    }

    public static void i(String tag, String message) {
        if (isLogEnable(tag, message)) {
            Log.i(tag, message);
        }
    }

    public static void v(String tag, String message) {
        if (isLogEnable(tag, message)) {
            Log.v(tag, message);
        }
    }

    public static void w(String tag, String message) {
        if (isLogEnable(tag, message)) {
            Log.w(tag, message);
        }
    }

    private static boolean isLogEnable(String tag, String message) {
        return LOG_ENABLED && !TextUtils.isEmpty(tag) && !TextUtils.isEmpty(message);
    }
}
