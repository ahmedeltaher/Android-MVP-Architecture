package com.task.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;

import com.task.App;

/**
 * Created by AhmedEltaher on 5/12/2016.
 */

public class UiUtils {
    public static Drawable getDrawable(int resId) {
        Context context = App.getContext();
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ?
            context.getResources().getDrawable(resId, context.getTheme()) :
            context.getResources().getDrawable(resId);
    }

}
