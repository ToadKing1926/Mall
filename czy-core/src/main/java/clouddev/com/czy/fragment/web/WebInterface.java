package clouddev.com.czy.fragment.web;

import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSON;

import clouddev.com.czy.fragment.web.event.Event;
import clouddev.com.czy.fragment.web.event.EventManager;

/**
 * Created by 29737 on 2018/3/13.
 */

public class WebInterface
{
    private final WebFragment FRAGMENT;

    private WebInterface(WebFragment fragment)
    {
        this.FRAGMENT = fragment;
    }

    public static WebInterface create(WebFragment fragment)
    {
        return new WebInterface(fragment);
    }

    @JavascriptInterface
    public String event(String params)
    {
        final String action = JSON.parseObject(params).getString("action");
        final Event event = EventManager.getInstance().createEvent(action);
        if(event != null)
        {
            event.setAction(action);
            event.setContext(FRAGMENT.getContext());
            event.setFragment(FRAGMENT);
            event.setUrl(FRAGMENT.getUrl());
            return event.execute(params);
        }
        return null;
    }
}
