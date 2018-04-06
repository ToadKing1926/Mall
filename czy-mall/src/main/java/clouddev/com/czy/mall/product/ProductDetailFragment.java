package clouddev.com.czy.mall.product;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.joanzapata.iconify.widget.IconTextView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import clouddev.com.czy.fragment.CoreFragment;
import clouddev.com.czy.mall.R;
import clouddev.com.czy.mall.R2;
import clouddev.com.czy.network.RestfulClient;
import clouddev.com.czy.network.callback.iSuccess;
import clouddev.com.czy.ui.LoaderStyle;
import clouddev.com.czy.ui.widget.CircleTextView;
import clouddev.com.czy.util.GlideImageLoader;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by 29737
 */

public class ProductDetailFragment extends CoreFragment implements AppBarLayout.OnOffsetChangedListener
{
    @BindView(R2.id.product_detail_toolbar)
    Toolbar mToolbar = null;
    @BindView(R2.id.tab_layout)
    TabLayout mTabLayout = null;
    @BindView(R2.id.view_pager)
    ViewPager mViewPager = null;
    @BindView(R2.id.detail_banner)
    Banner mBanner = null;
    @BindView(R2.id.collapsing_toolbar_detail)
    CollapsingToolbarLayout mCollapsingToolbarLayout = null;
    @BindView(R2.id.app_bar_detail)
    AppBarLayout mAppBar = null;

    //底部
    @BindView(R2.id.icon_favor)
    IconTextView mIconFavor = null;
    @BindView(R2.id.tv_shopping_cart_amount)
    CircleTextView mCircleTextView = null;
    @BindView(R2.id.rl_add_shop_cart)
    RelativeLayout mRlAddShopCart = null;
    @BindView(R2.id.icon_shop_cart)
    IconTextView mIconShopCart = null;

    private static final String ARG_PRODUCT_ID = "ARG_PRODUCT_ID";
    private List<String> bannerImages = new ArrayList<>();
    private int mProductId;
    private String mProductThumbUrl;
    private int mShopCart = 0;

    @OnClick(R2.id.rl_add_shop_cart)
    void onClickShopCart()
    {
        mShopCart++;
        if(mCircleTextView.getVisibility() == View.GONE)
        {
            mCircleTextView.setVisibility(View.VISIBLE);
        }
        mCircleTextView.setText(String.valueOf(mShopCart));
    }

    @OnClick(R2.id.icon_product_back)
    void onClickBack()
    {
        getSupportDelegate().pop();
    }

    private void setShopCartCount()
    {
        if(mShopCart == 0)
        {
            mCircleTextView.setVisibility(View.GONE);
        }
    }

    public static ProductDetailFragment create(@NonNull int productId)
    {
        final Bundle args = new Bundle();
        args.putInt(ARG_PRODUCT_ID,productId);
        final ProductDetailFragment fragment = new ProductDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if(args != null)
        {
            mProductId = args.getInt(ARG_PRODUCT_ID,-1);
        }
    }

    @Override
    public Object setLayout()
    {
        return R.layout.product_details_fragment;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView)
    {
       mCollapsingToolbarLayout.setContentScrimColor(Color.WHITE);
       mAppBar.addOnOffsetChangedListener(this);
       initData();
       initTabLayout();
       mCircleTextView.setBackgroundColor(Color.WHITE);
    }

    private void initData()
    {
        RestfulClient.builder()
                     .url("/product_detail_"+ mProductId)
                     .params("","")
                     .loader(getContext(), LoaderStyle.BallClipRotateMultipleIndicator)
                     .success(new iSuccess()
                     {
                         @Override
                         public void onSuccess(String response)
                         {
                             final JSONObject data = JSON.parseObject(response).getJSONObject("data");
                             final JSONArray banners = data.getJSONArray("banners");
                             initBanner(banners);
                             initPager(data);
                             initProductInfo(data);
                             setShopCartCount();
                         }
                     })
                     .build()
                     .get();
    }

    private void initProductInfo(JSONObject data)
    {
        final String productData = data.toJSONString();
        getSupportDelegate().loadRootFragment(R.id.frame_product_info,ProductInfoFragment.create(productData));
    }

    private void initBanner(JSONArray banners)
    {
        final int size = banners.size();
        for(int i = 0;i < size;i++)
        {
            bannerImages.add(banners.getString(i));
        }
        mBanner.setImageLoader(new GlideImageLoader())
                .setImages(bannerImages)
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                .start();;
    }

    private void initTabLayout()
    {
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(getContext(),R.color.app_main));
        mTabLayout.setTabTextColors(ColorStateList.valueOf(Color.BLACK));
        mTabLayout.setBackgroundColor(Color.WHITE);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initPager(JSONObject data)
    {
         final PagerAdapter adapter = new TabPagerAdapter(getFragmentManager(),data);
         mViewPager.setAdapter(adapter);
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator()
    {
        return new DefaultHorizontalAnimator();
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset)
    {

    }
}
