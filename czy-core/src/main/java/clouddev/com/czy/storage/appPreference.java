package clouddev.com.czy.storage;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.alibaba.fastjson.JSONObject;

import clouddev.com.czy.app.appInit;

/**
 * Created by 29737
 */

public final class appPreference
{
    private static final SharedPreferences PREFERENCES = PreferenceManager.getDefaultSharedPreferences(appInit.getApplication());
    private static final String APP_PREFERENCES_KEY = "profile";
    private static final String USER_TOKEN = "Username";

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

    //保存登录状态用的Token
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

    public static void setToken(String token)
    {
        getAppPreference().edit()
                .putString(USER_TOKEN,token)
                .apply();
    }

    public static String getToken()
    {
        return getAppPreference().getString(USER_TOKEN,"");
    }

    public static void addCustomAppProfile(String key,String value)
    {
        getAppPreference()
                .edit()
                .putString(key,value)
                .apply();
    }

    public static String getCustomAppProfile(String key)
    {
        return getAppPreference().getString(key,"");
    }
}
