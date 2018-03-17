package clouddev.com.czy.mall.ui.sort.content;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import clouddev.com.czy.fragment.CoreFragment;
import clouddev.com.czy.mall.R;
import clouddev.com.czy.mall.R2;
import clouddev.com.czy.mall.bean.SortContentBean;
import clouddev.com.czy.mall.converter.SortContentDataConverter;
import clouddev.com.czy.network.RestfulClient;
import clouddev.com.czy.network.callback.iSuccess;

/**
 * Created by 29737 on 2018/3/10.
 */

public class ContentFragment extends CoreFragment
{
    @BindView(R2.id.sort_content)
    RecyclerView mRecyclerView = null;
    private static final String ARG_CONTENT_ID = "CONTENT_ID";
    private int mContentId = -1;//待加载的Fragment序号
    private List<SortContentBean> mData = null;


    //通过传入不同的Id，确定加载哪一个Fragment
    public static ContentFragment newInstance(int contentId)
    {
        final Bundle args = new Bundle();
        args.putInt(ARG_CONTENT_ID,contentId);
        final ContentFragment contentFragment = new ContentFragment();
        contentFragment.setArguments(args);
        return contentFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if(args!= null)
        {
            mContentId = args.getInt(ARG_CONTENT_ID);
        }
    }

    @Override
    public Object setLayout()
    {
        return R.layout.sort_content_fragment;
    }

    private void initData()
    {
        RestfulClient.builder()
                     .params("","")
                     .url("/sort_content")
                     .success(new iSuccess()
                     {
                         @Override
                         public void onSuccess(String response)
                         {
                             mData = new SortContentDataConverter().convert(response);
                             final ContentAdapter contentAdapter = new ContentAdapter(R.layout.sort_content_body,R.layout.sort_content_header,mData);
                             mRecyclerView.setAdapter(contentAdapter);
                         }
                     })
                      .build()
                      .get();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView)
    {
        final StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);//瀑布流布局，每排两个垂直排布
        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        initData();
    }
}
