package clouddev.com.czy.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;
import android.util.Log;

import clouddev.com.czy.R;
import clouddev.com.czy.fragment.CoreFragment;
import me.yokeyword.fragmentation.BuildConfig;
import me.yokeyword.fragmentation.Fragmentation;
import me.yokeyword.fragmentation.SupportActivity;



import me.yokeyword.fragmentation.SupportActivity;


/**
 * Created by 29737 on 2017/12/23.
 */

public abstract class FragmentVectorActivity extends SupportActivity
{
   public abstract CoreFragment setRootFragment();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);
    }

    private void init(@Nullable Bundle savedInstanceState)
    {
        final ContentFrameLayout container=new ContentFrameLayout(this);
        container.setId(R.id.delegate_container);
        setContentView(container);
        if(savedInstanceState==null)
        {
            loadRootFragment(R.id.delegate_container,setRootFragment());
        }
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}
