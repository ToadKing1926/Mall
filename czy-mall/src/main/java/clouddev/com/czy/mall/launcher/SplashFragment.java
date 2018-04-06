package clouddev.com.czy.mall.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;
import clouddev.com.czy.app.AccountManager;
import clouddev.com.czy.app.iUserCheck;
import clouddev.com.czy.fragment.CoreFragment;
import clouddev.com.czy.mall.R;
import clouddev.com.czy.mall.R2;
import clouddev.com.czy.ui.iLauncherListener;
import clouddev.com.czy.util.timer.BaseTimerTask;
import clouddev.com.czy.util.timer.iTimerListener;

/**
 * Created by 29737
 */

public class SplashFragment extends CoreFragment implements iTimerListener
{
    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView appCompatTextView = null;

    private Timer timer = null;
    //启动页面倒计时秒数
    private int mCount = 5;
    private iLauncherListener mILauncherListener = null;

    @OnClick(R2.id.tv_launcher_timer)
    void onClick()
    {
        if(timer != null)
        {
            timer.cancel();
            timer = null;
            checkIsShow();
        }
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        if(activity instanceof iLauncherListener)
        {
            mILauncherListener = (iLauncherListener)activity;
        }
    }

    private void initTimer()
    {
        timer = new Timer();
        final BaseTimerTask timerTask = new BaseTimerTask(this);
        timer.schedule(timerTask,0,1000);
    }

    @Override
    public Object setLayout()
    {
        return R.layout.splash_fragment;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView)
    {
        initTimer();
    }

    private void checkIsShow()
    {
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

    @Override
    public void onTimer()
    {
       getFVActivity().runOnUiThread(new Runnable() {
           @Override
           public void run()
           {
               if(appCompatTextView != null)
               {
                   appCompatTextView.setText(MessageFormat.format("跳过\n{0}s",mCount));
                   mCount--;
               }
               if(mCount < 0)
               {
                   if(timer != null)
                   {
                       timer.cancel();
                       timer = null;
                       checkIsShow();
                   }
               }
           }
       });
    }
}
