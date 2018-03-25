package clouddev.com.czy.mall.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;

import butterknife.BindView;
import clouddev.com.czy.app.AccountManager;
import clouddev.com.czy.app.iUserCheck;
import clouddev.com.czy.fragment.CoreFragment;
import clouddev.com.czy.mall.R;
import clouddev.com.czy.mall.R2;
import clouddev.com.czy.mall.sign.SignInFragment;
import clouddev.com.czy.util.GlideImageLoader;
import clouddev.com.czy.storage.appPreference;
import clouddev.com.czy.ui.ScrollLauncherTag;
import clouddev.com.czy.ui.iLauncherListener;



/**
 * Created by 29737
 */

public class SplashScrollFragment extends CoreFragment implements OnBannerListener
{
    @BindView(R2.id.splash_banner)
    Banner banner = null;
    private static final ArrayList<Integer> IMAGES = new ArrayList<>();
    private iLauncherListener mILauncherListener = null;

    private void initBanner()
    {
        IMAGES.add(R.mipmap.launcher_01);
        IMAGES.add(R.mipmap.launcher_02);
        IMAGES.add(R.mipmap.launcher_03);
        banner.setImageLoader(new GlideImageLoader())
                .setImages(IMAGES)
                .setIndicatorGravity(BannerConfig.CENTER)
                .isAutoPlay(false)
                .setOnBannerListener(this)
                .start();
    }
    @Override
    public Object setLayout()
    {
       return R.layout.splash_scroll_fragment;
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        if(activity instanceof iLauncherListener)
            mILauncherListener = (iLauncherListener)activity;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView)
    {
        initBanner();
    }

    @Override
    public void OnBannerClick(int position)
    {

        if(position == IMAGES.size()-1)
        {
            appPreference.setAppFlag(ScrollLauncherTag.FIRST_LAUNCHER_APP.name(),true);
            AccountManager.checkAccount(new iUserCheck()
            {
                @Override
                public void onSignin()
                {
                    if(mILauncherListener != null)
                    {
                        mILauncherListener.onLauncherFinish(true);
                    }
                }

                @Override
                public void onSignOut()
                {
                    if(mILauncherListener != null)
                    {
                        mILauncherListener.onLauncherFinish(false);
                    }
                }
            });
        }

    }
}
