package com.task.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.widget.TextView;

import com.task.App;

/**
 * Created by AhmedEltaher on 05/12/16.
 */

public class ResHelper {
    public static Drawable getDrawable(int resId) {
        Context context = App.getContext();
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ?
            context.getResources().getDrawable(resId, context.getTheme()) :
            context.getResources().getDrawable(resId);
    }

    public static int getColor(Context context, int colorId) {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ?
            context.getResources().getColor(colorId, context.getTheme()) :
            context.getResources().getColor(colorId);
    }

    public static ColorStateList getColorStateList(Context context, int colorId) {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ?
            context.getResources().getColorStateList(colorId, context.getTheme()) :
            context.getResources().getColorStateList(colorId);
    }

    public static void setTextAppearance(Context context, TextView textView, int resId) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            textView.setTextAppearance(context, resId);
        } else {
            textView.setTextAppearance(resId);
        }
    }

    public static int getDimension(Context context, int dimensionId) {
        return context.getResources().getDimensionPixelSize(dimensionId);
    }
}
