package clouddev.com.czy.util.timer;

import java.util.TimerTask;

/**
 * Created by 29737 on 2018/1/1.
 */

public class BaseTimerTask extends TimerTask
{
    private iTimerListener mITimerListener = null;

    public BaseTimerTask(iTimerListener mITimerListener)
    {
        this.mITimerListener = mITimerListener;
    }

    @Override
    public void run()
    {
        if(mITimerListener != null)
        {
            mITimerListener.onTimer();
        }
    }
}
