package clouddev.mall;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;

import clouddev.com.czy.app.appInit;
import clouddev.com.czy.mall.icon.FontEc;

/**
 * Created by 29737 on 2017/12/21.
 */

public class MallApp extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        appInit.init(this)
                .setICON(new FontEc())
                .setICON(new FontAwesomeModule())
                .configure();

    }
}
