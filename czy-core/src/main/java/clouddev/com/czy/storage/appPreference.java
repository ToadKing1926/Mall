package clouddev.com.czy.storage;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import org.json.JSONObject;

import clouddev.com.czy.app.appInit;

/**
 * Created by 29737 on 2018/1/1.
 */

public final class appPreference
{
    private static final SharedPreferences PREFERENCES = PreferenceManager.getDefaultSharedPreferences(appInit.getApplication());
    private static final String APP_PREFERENCES_KEY = "profile";

    private static SharedPreferences getAppPreference()
    {
        return PREFERENCES;
    }

    public static void setAppProfile(String val)
    {
        getAppPreference().edit()
                          .putString(APP_PREFERENCES_KEY,val)
                          .apply();
    }

    public static String getAppProfile()
    {
        return getAppPreference().getString(APP_PREFERENCES_KEY,null);
    }

    public static JSONObject getAppProfileJson()
    {
        final String profile = getAppProfile();
        return new JSONObject();
    }

    public static void removeAppProfile()
    {
        getAppPreference().edit()
                          .remove(APP_PREFERENCES_KEY)
                          .apply();
    }

    public static void clearAppProfile()
    {
        getAppPreference().edit()
                          .clear()
                          .apply();
    }

    //是否初次启动，flag == true 初次启动
    public static void setAppFlag(String key,boolean flag)
    {
        getAppPreference().edit()
                          .putBoolean(key,flag)
                          .apply();
    }

    public static boolean getAppFlag(String key)
    {
        return getAppPreference().getBoolean(key,false);
    }
}
