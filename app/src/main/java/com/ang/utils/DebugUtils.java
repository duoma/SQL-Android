package com.ang.utils;

import android.text.TextUtils;
import android.util.Log;

/**
 * @author duoma
 * @date 2019/02/22
 */
public class DebugUtils {
    private static final String TAG = "DebugUtil";

    private static boolean DEBUG = true;

    public static void isDebug(boolean debug) {
        DEBUG = debug;
    }

    public static void d(String tag, String msg) {
        if (DEBUG && !TextUtils.isEmpty(msg)) {
            Log.d(tag, msg);
        }
    }

    public static void d(String msg) {
        if (DEBUG && !TextUtils.isEmpty(msg)) {
            Log.d(TAG, msg);
        }
    }

    public static void e(String tag, String error) {
        if (DEBUG && !TextUtils.isEmpty(error)) {
            Log.e(tag, error);
        }
    }

    public static void e(String error) {
        if (DEBUG && !TextUtils.isEmpty(error)) {
            Log.e(TAG, error);
        }
    }

    public static void i(String tag, String msg) {
        if (DEBUG && !TextUtils.isEmpty(msg)) {
            Log.i(tag, msg);
        }
    }

    public static void i(String msg) {
        if (DEBUG && !TextUtils.isEmpty(msg)) {
            Log.i(TAG, msg);
        }
    }

    public static void ld(String tag, String msg) {
        if (DEBUG && !TextUtils.isEmpty(msg)) {
            if (msg.length() > 4000) {
                int chunkCount = msg.length() / 4000;
                for (int i = 0; i <= chunkCount; i++) {
                    int max = 4000 * (i + 1);
                    if (max >= msg.length()) {
                        Log.d(tag, "打印第" + i + "段" + " 总共" + chunkCount + "段" + ":" + msg.substring(4000 * i));
                    } else {
                        Log.d(tag, "打印第" + i + "段" + " 总共" + chunkCount + "段" + ":" + msg.substring(4000 * i, max));
                    }
                }
            } else {
                Log.d(tag, msg);
            }
        }
    }
}
