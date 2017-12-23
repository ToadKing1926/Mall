package clouddev.mall;

import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import clouddev.com.czy.activity.FragmentVectorActivity;
import clouddev.com.czy.app.appInit;
import clouddev.com.czy.fragment.CoreFragment;

public class MainActivity extends FragmentVectorActivity {



    @Override
    public CoreFragment setRootFragment()
    {

        return new ExampleFragment();
    }
}
