package clouddev.com.czy.fragment.web.client;

import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Created by 29737 on 2018/3/14.
 */

public class WebChromeClientImpl extends WebChromeClient
{
    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result)
    {
        return super.onJsAlert(view, url, message, result);
    }
}
