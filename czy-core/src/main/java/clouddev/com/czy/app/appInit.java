package clouddev.com.czy.app;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.util.HashMap;


/**
 * Created by 29737 on 2017/12/21.
 */

public final class appInit
{
    public static appConfig init(Context context)
    {
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(), context.getApplicationContext());
        return appConfig.getInstance();
    }

    private static HashMap<String,Object> getConfigurations()
    {
        return appConfig.getInstance().getAppConfigration();
    }

    public static Context getApplication()
    {
        return (Context)getConfigurations().get(ConfigType.APPLICATION_CONTEXT.name());
    }
}
