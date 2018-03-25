package clouddev.com.czy.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import clouddev.com.czy.app.appInit;


/**
 * Created by 29737
 */

public class DiemnUtil
{
    public static int getScreenWidth()
    {
        final Resources resources = appInit.getApplication().getResources();
        final DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

    public static int getScreenHeight()
    {
        final Resources resources = appInit.getApplication().getResources();
        final DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.heightPixels;
    }
}
