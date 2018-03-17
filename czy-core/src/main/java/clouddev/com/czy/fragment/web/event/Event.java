package clouddev.com.czy.fragment.web.event;


import android.content.Context;
import android.webkit.WebView;


import clouddev.com.czy.fragment.web.WebFragment;

/**
 * Created by 29737 on 2018/3/15.
 */

public abstract class Event implements iEvent
{
    private Context mContext = null;
    private String  mAction = null;
    private WebFragment mFragment = null;
    private String mUrl = null;

    public Context getContext()
    {
        return mContext;
    }

    public void setContext(Context mContext)
    {
        this.mContext = mContext;
    }

    public String getAction()
    {
        return mAction;
    }

    public void setAction(String mAction)
    {
        this.mAction = mAction;
    }

    public WebFragment getFragment()
    {
        return mFragment;
    }

    public void setFragment(WebFragment mFragment)
    {
        this.mFragment = mFragment;
    }

    public String getUrl()
    {
        return mUrl;
    }

    public void setUrl(String mUrl)
    {
        this.mUrl = mUrl;
    }



}
