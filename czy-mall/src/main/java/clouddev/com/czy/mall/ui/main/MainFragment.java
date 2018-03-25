package clouddev.com.czy.mall.ui.main;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;


import com.joanzapata.iconify.widget.IconTextView;


import butterknife.BindView;
import clouddev.com.czy.fragment.CoreFragment;
import clouddev.com.czy.mall.R;
import clouddev.com.czy.mall.R2;
import clouddev.com.czy.mall.converter.MainDataConverter;
import clouddev.com.czy.ui.recycler.BaseDivider;
import clouddev.com.czy.ui.refresh.RefreshHandler;
import clouddev.mall.ExampleFragment;

/**
 * Created by 29737
 */

public class MainFragment extends CoreFragment
{
    @BindView(R2.id.swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout =null;
    @BindView(R2.id.main_recycler_view)
    RecyclerView recyclerView = null;
    @BindView(R2.id.main_tool_bar)
    Toolbar toolbar = null;
    @BindView(R2.id.icon_scan)
    IconTextView scan_view = null;
    @BindView(R2.id.search)
    AppCompatEditText appCompatEditText = null;
    @BindView(R2.id.icon_message)
    IconTextView message_view = null;

    private RefreshHandler refreshHandler = null;

    private void initRefresh()
    {
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,android.R.color.holo_orange_light,android.R.color.holo_red_light);
        swipeRefreshLayout.setProgressViewOffset(true,120,300);
    }

    private void initRecyclerView()
    {
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),4);
        final ExampleFragment exampleFragment = getParentfragment();

        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(BaseDivider.create(Color.GRAY,3));
        recyclerView.addOnItemTouchListener(MainItemClickListener.create(exampleFragment));
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState)
    {
        super.onLazyInitView(savedInstanceState);
        initRefresh();
        initRecyclerView();
        refreshHandler.firstPage("/index");
    }

    @Override
    public Object setLayout()
    {
        return R.layout.main_fragment;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView)
    {
         refreshHandler = RefreshHandler.create(swipeRefreshLayout,recyclerView,new MainDataConverter());

    }
}
