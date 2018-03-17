package clouddev.com.czy.fragment.web.client;

import android.content.Loader;
import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import clouddev.com.czy.fragment.web.WebFragment;
import clouddev.com.czy.fragment.web.iPageLoadListener;
import clouddev.com.czy.fragment.web.route.Router;
import clouddev.com.czy.ui.LoaderStyle;
import clouddev.com.czy.ui.czyLoader;

/**
 * Created by 29737 on 2018/3/14.
 */

public class WebClientImpl extends WebViewClient
{
    private final WebFragment FRAGMENT;
    private iPageLoadListener mIPageLoadListener = null;

    public void setPageLoadListener(iPageLoadListener listener)
    {
       this.mIPageLoadListener = listener;
    }

    public WebClientImpl(WebFragment fragment)
    {
        this.FRAGMENT = fragment;
    }

    //原生接管
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url)
    {
        return Router.getInstance().handleWebUrl(FRAGMENT, url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon)
    {
        super.onPageStarted(view, url, favicon);
        if(mIPageLoadListener != null)
        {
            mIPageLoadListener.onLoadStart();
        }
        czyLoader.showLoading(FRAGMENT.getContext(), LoaderStyle.BallBeatIndicator.name());
    }

    @Override
    public void onPageFinished(WebView view, String url)
    {
        super.onPageFinished(view, url);
        if(mIPageLoadListener != null)
        {
            mIPageLoadListener.onLoadFinish();
        }
        czyLoader.stopLoading();
    }
}
