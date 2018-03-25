package clouddev.mall;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;

import org.litepal.LitePal;

import clouddev.com.czy.app.appInit;
import clouddev.com.czy.mall.icon.FontEc;
import clouddev.com.czy.network.interceptor.DebugInterceptor;

/**
 * Created by 29737
 */

public class MallApp extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        LitePal.initialize(getApplicationContext());//初始化数据库
        appInit.init(this)
                .setApiHost("http://127.0.0.1")
                .setICON(new FontEc())
                .setICON(new FontAwesomeModule())
                .setIntercepter(new DebugInterceptor("index",R.raw.mainpage))
                .setIntercepter(new DebugInterceptor("sort_list",R.raw.sort))
                .setIntercepter(new DebugInterceptor("sort_content",R.raw.sort_content))
                .setIntercepter(new DebugInterceptor("cart",R.raw.cart))
                .setIntercepter(new DebugInterceptor("order_list",R.raw.order_list))
                .setIntercepter(new DebugInterceptor("address",R.raw.address))
                .setJSInterface("czy")
                .configure();

    }

}
