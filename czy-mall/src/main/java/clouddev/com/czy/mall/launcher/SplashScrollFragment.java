package clouddev.com.czy.mall.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;

import butterknife.BindView;
import clouddev.com.czy.fragment.CoreFragment;
import clouddev.com.czy.mall.R;
import clouddev.com.czy.mall.R2;
import clouddev.com.czy.mall.util.GlideImageLoader;
import clouddev.com.czy.storage.appPreference;
import clouddev.com.czy.ui.ScrollLauncherTag;


/**
 * Created by 29737 on 2018/1/1.
 */

public class SplashScrollFragment extends CoreFragment implements OnBannerListener
{
    @BindView(R2.id.splash_banner)
    Banner banner = null;
    private static final ArrayList<Integer> IMAGES = new ArrayList<>();

    private void initBanner()
    {
        IMAGES.add(R.mipmap.launcher_01);
        IMAGES.add(R.mipmap.launcher_02);
        IMAGES.add(R.mipmap.launcher_03);
        banner.setImageLoader(new GlideImageLoader())
                .setImages(IMAGES)
                .setIndicatorGravity(BannerConfig.CENTER)
                .setOnBannerListener(this)
                .start();
    }
    @Override
    public Object setLayout()
    {
       return R.layout.splash_scroll_fragment;
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
            //TODO:check if user has login
        }

    }
}
