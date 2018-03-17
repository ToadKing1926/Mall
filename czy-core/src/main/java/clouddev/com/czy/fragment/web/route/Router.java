package clouddev.com.czy.fragment.web.route;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.webkit.URLUtil;
import android.webkit.WebView;

import java.util.logging.Handler;

import clouddev.com.czy.fragment.CoreFragment;
import clouddev.com.czy.fragment.web.WebFragment;
import clouddev.com.czy.fragment.web.WebFragmentImpl;

/**
 * Created by 29737 on 2018/3/14.
 */

public class Router
{
    private Router()
    {

    }

    private static class Holder
    {
        private static final Router INSTANCE = new Router();
    }

    public static Router getInstance()
    {
        return Holder.INSTANCE;
    }

    public final boolean handleWebUrl(WebFragment fragment,String url)
    {
        //电话协议
        if(url.contains("tel:"))
        {
            callPhone(fragment.getContext(),url);
            return true;
        }

        final CoreFragment topFragment = fragment.getTopFragment();
        final WebFragmentImpl webFragment = WebFragmentImpl.create(url);
        topFragment.start(webFragment);
        return true;
    }

    private void loadWebPage(WebView webView, String url)
    {
        if(webView != null)
        {
            webView.loadUrl(url);
        }
        else
        {
            throw new NullPointerException("WebView为空！");
        }
    }

    private void loadLocalPage(WebView webView, String url)
    {
        loadWebPage(webView,"file:///android_asset/"+url);
    }

    public final void loadPage(WebView webView, String url)
    {
        if(URLUtil.isNetworkUrl(url)||URLUtil.isAssetUrl(url))
        {
            loadWebPage(webView,url);
        }
        else
        {
            loadLocalPage(webView,url);
        }
    }

    private void callPhone(Context context,String uri)
    {
        final Intent intent = new Intent(Intent.ACTION_DIAL);
        final Uri data = Uri.parse(uri);
        intent.setData(data);
        ContextCompat.startActivity(context,intent,null);
    }
}
