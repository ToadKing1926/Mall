package clouddev.mall;

import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.litepal.LitePal;

import clouddev.com.czy.activity.FragmentVectorActivity;
import clouddev.com.czy.app.AccountManager;
import clouddev.com.czy.app.appInit;
import clouddev.com.czy.fragment.CoreFragment;
import clouddev.com.czy.mall.launcher.SplashFragment;
import clouddev.com.czy.mall.launcher.SplashScrollFragment;
import clouddev.com.czy.mall.sign.SignInFragment;
import clouddev.com.czy.mall.sign.SignUpFragment;
import clouddev.com.czy.mall.sign.iSignListener;
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
            startWithPop(new ExampleFragment());
        }
        else
        {
            startWithPop(new SignInFragment());
        }
    }

    @Override
    public void onSignInSuccess()
    {
        Toast.makeText(this,"sign in",Toast.LENGTH_LONG).show();
        AccountManager.setSignState(true);
        startWithPop(new ExampleFragment());
    }

    @Override
    public void onSignUpSuccess()
    {
        AccountManager.setSignState(true);
        startWithPop(new ExampleFragment());
    }
}
