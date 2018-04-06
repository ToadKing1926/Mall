package clouddev.com.czy.mall.ui.main;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.joanzapata.iconify.widget.IconTextView;


import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;
import clouddev.com.czy.fragment.CoreFragment;
import clouddev.com.czy.mall.R;
import clouddev.com.czy.mall.R2;
import clouddev.com.czy.mall.converter.MainDataConverter;
import clouddev.com.czy.mall.product.ProductDetailFragment;
import clouddev.com.czy.mall.ui.main.search.SearchFragment;
import clouddev.com.czy.ui.recycler.BaseDivider;
import clouddev.com.czy.ui.refresh.RefreshHandler;
import clouddev.com.czy.util.callback.CallBackManager;
import clouddev.com.czy.util.callback.CallBackType;
import clouddev.com.czy.util.callback.iGlobalCallback;
import clouddev.mall.MallFragment;

/**
 * Created by 29737
 */

public class MainFragment extends CoreFragment implements View.OnFocusChangeListener
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
    EditText editText = null;
    @BindView(R2.id.icon_random)
    IconTextView message_view = null;

    @OnClick(R2.id.icon_scan)
    void onClickScan()
    {
       startScanWithCheck(this.getParentfragment());
    }

    @OnClick(R2.id.icon_random)
    void onClickRandom()
    {
        Random random = new Random();
        int productId = random.nextInt(6);
        ProductDetailFragment productDetailFragment = ProductDetailFragment.create(productId);
        start(productDetailFragment);
    }

    private RefreshHandler refreshHandler = null;

    private void initRefresh()
    {
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,android.R.color.holo_orange_light,android.R.color.holo_red_light);
        swipeRefreshLayout.setProgressViewOffset(true,120,300);
    }

    private void initRecyclerView()
    {
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),4);
        final MallFragment exampleFragment = getParentfragment();

        recyclerView.setLayoutManager(gridLayoutManager);
        //recyclerView.addItemDecoration(BaseDivider.create(Color.GRAY,3));
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
         CallBackManager.getInstance().addCallback(CallBackType.SCAN, new iGlobalCallback<String>()
         {
             @Override
             public void executeCallback(String args)
             {
                 Toast.makeText(getContext(),args,Toast.LENGTH_LONG).show();
             }
         });
         editText.setOnFocusChangeListener(this);
    }

    @Override
    public void onFocusChange(View view, boolean hasFocus)
    {
       if(hasFocus)
       {
           getParentfragment().getSupportDelegate().start(new SearchFragment());
       }
    }
}
