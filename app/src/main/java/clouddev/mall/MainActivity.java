package clouddev.mall;

import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.util.Log;

import org.litepal.LitePal;

import clouddev.com.czy.activity.FragmentVectorActivity;
import clouddev.com.czy.app.AccountManager;
import clouddev.com.czy.fragment.CoreFragment;
import clouddev.com.czy.mall.launcher.SplashFragment;
import clouddev.com.czy.mall.sign.SignInFragment;
import clouddev.com.czy.mall.sign.iSignListener;
import clouddev.com.czy.storage.appPreference;
import clouddev.com.czy.ui.iLauncherListener;

public class MainActivity extends FragmentVectorActivity implements iSignListener,iLauncherListener {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
        {
            actionBar.hide();
        }
        LitePal.getDatabase();
    }

    @Override
    public CoreFragment setRootFragment()
    {
        return new SplashFragment();
    }

    @Override
    public void onLauncherFinish(boolean isFinish)
    {
        if(isFinish)
        {
            startWithPop(new MallFragment());
        }
        else
        {
            startWithPop(new SignInFragment());
        }
    }

    @Override
    public void onSignInSuccess(String token)
    {
        AccountManager.setSignState(true);
        appPreference.setToken(token);
        startWithPop(new MallFragment());
    }

    @Override
    public void onSignUpSuccess(String token)
    {
        AccountManager.setSignState(true);
        appPreference.setToken(token);
        Log.d("Hola","123456");
        startWithPop(new MallFragment());
    }
}
