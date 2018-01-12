package clouddev.com.czy.ui;

import android.support.v4.widget.SwipeRefreshLayout;

import clouddev.com.czy.app.appInit;

/**
 * Created by 29737 on 2018/1/10.
 */

public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener
{
    private final SwipeRefreshLayout swipeRefreshLayout;

    public RefreshHandler(SwipeRefreshLayout swipeRefreshLayout)
    {
        this.swipeRefreshLayout = swipeRefreshLayout;
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    private void refresh()
    {
        swipeRefreshLayout.setRefreshing(true);
        appInit.getHandler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                swipeRefreshLayout.setRefreshing(false);
            }
        },2000);
    }

    @Override
    public void onRefresh()
    {
       refresh();
    }
}
