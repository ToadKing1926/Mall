package clouddev.com.czy.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;

import clouddev.com.czy.app.appInit;
import clouddev.com.czy.network.RestfulClient;
import clouddev.com.czy.network.callback.iSuccess;
import clouddev.com.czy.ui.recycler.DataConverter;
import clouddev.com.czy.ui.recycler.MultipleRecyclerViewAdapter;

/**
 * Created by 29737
 */

public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener
{
    private final SwipeRefreshLayout swipeRefreshLayout;
    private final PageBean pageBean;
    private final RecyclerView recyclerView;
    private MultipleRecyclerViewAdapter multipleRecyclerViewAdapter = null;
    private final DataConverter dataConverter;

    private RefreshHandler(SwipeRefreshLayout swipeRefreshLayout, PageBean pageBean, RecyclerView recyclerView, DataConverter dataConverter)
    {
        this.swipeRefreshLayout = swipeRefreshLayout;
        swipeRefreshLayout.setOnRefreshListener(this);
        this.pageBean = pageBean;
        this.recyclerView = recyclerView;
        this.dataConverter = dataConverter;
    }

    public static RefreshHandler create(SwipeRefreshLayout swipeRefreshLayout, RecyclerView recyclerView, DataConverter dataConverter)
    {
        return new RefreshHandler(swipeRefreshLayout,new PageBean(),recyclerView,dataConverter);
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

    public void firstPage(String url)
    {
        pageBean.setDelayed(1000);
        RestfulClient.builder()
                     .url(url)
                     .params("","")
                     .success(new iSuccess()
                     {
                         @Override
                         public void onSuccess(String response)
                         {
                             JSONObject object = JSON.parseObject(response);
                             pageBean.setTotal(object.getInteger("total"))
                                       .setPageSize (object.getInteger("page_size"));
                             multipleRecyclerViewAdapter = MultipleRecyclerViewAdapter.create(dataConverter.setJsonData(response));
                             multipleRecyclerViewAdapter.setOnLoadMoreListener(RefreshHandler.this,recyclerView);
                             recyclerView.setAdapter(multipleRecyclerViewAdapter);
                             pageBean.addIndex();
                         }
                     })
                     .build()
                     .get();
    }

    private void paging(final String url)
    {
        final int pageSize = pageBean.getPageSize();
        final int curCount = pageBean.getCurCount();
        final int total = pageBean.getTotal();
        final int index = pageBean.getPageIndex();

        if(multipleRecyclerViewAdapter.getData().size() < pageSize || curCount >= total)
        {
            multipleRecyclerViewAdapter.loadMoreEnd(true);
        }
        else
        {
            appInit.getHandler().postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    RestfulClient.builder()
                            .url(url)
                            .params("","")
                            .success(new iSuccess()
                            {
                                @Override
                                public void onSuccess(String response)
                                {
                                    multipleRecyclerViewAdapter.addData(dataConverter.setJsonData(response).convert());
                                    pageBean.setCurCount(multipleRecyclerViewAdapter.getData().size());
                                    multipleRecyclerViewAdapter.loadMoreComplete();
                                    pageBean.addIndex();
                                }
                            })
                            .build()
                            .get();
                }
            },2000);
        }
    }

    @Override
    public void onLoadMoreRequested()
    {
       //paging("/index");
    }
}
