package clouddev.com.czy.fragment.web;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

import clouddev.com.czy.app.ConfigType;
import clouddev.com.czy.app.appInit;
import clouddev.com.czy.fragment.CoreFragment;
import clouddev.com.czy.fragment.web.route.RouteKeys;

/**
 * Created by 29737 on 2018/3/13.
 */

public abstract class WebFragment extends CoreFragment implements iWebViewInit
{
    private WebView mWebView = null;
    private final ReferenceQueue<WebView> WEB_VIEW_QUEUE = new ReferenceQueue<>();
    private String mUrl = null;
    private boolean mIsWebViewAvailable = false;
    private CoreFragment mTopFragment;

    public WebFragment()
    {

    }

    public abstract iWebViewInit setInit();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        mUrl = args.getString(RouteKeys.URL.name());
        initWebView();
    }

    @SuppressLint("JavascriptInterface")
    private void initWebView()
    {
        if(mWebView != null)
        {
            mWebView.removeAllViews();
            mWebView.destroy();
        }
        else
        {
            final iWebViewInit init = setInit();
            if(init != null)
            {
                //代码直接生成，尽可能避免内存泄漏
                final WeakReference<WebView> webViewWeakReference = new WeakReference<>(new WebView(getContext()),WEB_VIEW_QUEUE);
                mWebView = webViewWeakReference.get();
                mWebView = init.initWebView(mWebView);
                mWebView.setWebViewClient(init.initWebViewClient());
                mWebView.setWebChromeClient(init.initWebChromeClient());
                final String name = appInit.getConfiguration(ConfigType.JAVASCEIPT_INTERFACE);
                mWebView.addJavascriptInterface(WebInterface.create(this),name);
                mIsWebViewAvailable = true;
            }
            else
            {
                throw new NullPointerException("初始器异常！");
            }
        }
    }

    public void setTopFragment(CoreFragment fragment)
    {
        mTopFragment = fragment;
    }

    public CoreFragment getTopFragment()
    {
        if(mTopFragment == null)
        {
            mTopFragment = this;
        }
        return mTopFragment;
    }

    public WebView getWebView()
    {
        if(mWebView != null)
        {
            return mIsWebViewAvailable? mWebView:null;
        }
        else
        {
            throw new NullPointerException("WebView为空！");
        }
    }

    public String getUrl()
    {
        return mUrl;
    }

    @Override
    public void onPause()
    {
        super.onPause();
        if(mWebView != null)
        {
            mWebView.onPause();
        }
    }

    @Override
    public void onResume()
    {
        super.onResume();
        if(mWebView != null)
        {
            mWebView.onResume();
        }
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        mIsWebViewAvailable = false;
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        if(mWebView != null)
        {
            mWebView.removeAllViews();
            mWebView.destroy();
            mWebView = null;
        }
    }
}
