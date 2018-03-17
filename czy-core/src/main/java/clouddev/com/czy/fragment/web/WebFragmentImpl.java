package clouddev.com.czy.fragment.web;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import clouddev.com.czy.fragment.web.client.WebChromeClientImpl;
import clouddev.com.czy.fragment.web.client.WebClientImpl;
import clouddev.com.czy.fragment.web.route.RouteKeys;
import clouddev.com.czy.fragment.web.route.Router;

/**
 * Created by 29737 on 2018/3/14.
 */

public class WebFragmentImpl extends WebFragment
{

    private iPageLoadListener mIPageLoadListener = null;

    public static WebFragmentImpl create(String url)
    {
        final Bundle args = new Bundle();
        args.putString(RouteKeys.URL.name(),url);
        final WebFragmentImpl webFragment = new WebFragmentImpl();
        webFragment.setArguments(args);
        return webFragment;
    }



    @Override
    public Object setLayout()
    {
        return getWebView();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView)
    {
        if(getUrl() != null)
        {
            Router.getInstance().loadPage(this.getWebView(),getUrl());
        }
    }

    @Override
    public iWebViewInit setInit()
    {
        return this;
    }

    public void setPageLoadListener(iPageLoadListener listener)
    {
        this.mIPageLoadListener = listener;
    }

    @Override
    @SuppressLint("SetJavascriptEnabled")
    public WebView initWebView(WebView webView)
    {
        WebView.setWebContentsDebuggingEnabled(true);
        //禁止横向滚动
        webView.setHorizontalScrollBarEnabled(false);
        //禁止纵向滚动
        webView.setVerticalScrollBarEnabled(false);
        //允许截图
        webView.setDrawingCacheEnabled(true);

        final WebSettings webSettings = webView.getSettings();
        final String agent = webSettings.getUserAgentString();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUserAgentString(agent+"czy");
        webSettings.setBuiltInZoomControls(false);
        webSettings.setDisplayZoomControls(false);
        webSettings.setSupportZoom(false);

        webSettings.setAllowFileAccess(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);

        webSettings.setAppCacheEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        return webView;
    }

    @Override
    public WebViewClient initWebViewClient()
    {
        final WebClientImpl webClient = new WebClientImpl(this);
        webClient.setPageLoadListener(mIPageLoadListener);
        return webClient;
    }

    @Override
    public WebChromeClient initWebChromeClient()
    {
        return new WebChromeClientImpl();
    }

    @Override
    public String getUrl()
    {
        return super.getUrl();
    }
}
