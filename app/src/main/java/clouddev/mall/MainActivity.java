package clouddev.mall;

import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import clouddev.com.czy.activity.FragmentVectorActivity;
import clouddev.com.czy.app.appInit;
import clouddev.com.czy.fragment.CoreFragment;
import clouddev.com.czy.mall.launcher.SplashFragment;
import clouddev.com.czy.mall.launcher.SplashScrollFragment;

public class MainActivity extends FragmentVectorActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
        {
            actionBar.hide();
        }
    }

    @Override
    public CoreFragment setRootFragment()
    {

        return new SplashFragment();
    }
}
