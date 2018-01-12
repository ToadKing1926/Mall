package clouddev.mall;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.WeakHashMap;

import butterknife.BindView;
import clouddev.com.czy.mall.bean.MainPageJson;
import clouddev.com.czy.ui.recycler.ItemType;
import clouddev.com.czy.ui.recycler.MultipleFields;
import clouddev.com.czy.ui.recycler.MultipleItemEntity;
import clouddev.mall.R2;
import clouddev.com.czy.fragment.CoreFragment;
import clouddev.com.czy.mall.ui.CartFragment;
import clouddev.com.czy.mall.ui.DiscoverFragment;
import clouddev.com.czy.mall.ui.MainFragment;
import clouddev.com.czy.mall.ui.MineFragment;
import clouddev.com.czy.network.RestfulClient;
import clouddev.com.czy.network.callback.iError;
import clouddev.com.czy.network.callback.iFailure;
import clouddev.com.czy.network.callback.iSuccess;
import clouddev.com.czy.ui.LoaderStyle;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by 29737 on 2017/12/23.
 */

public class ExampleFragment extends CoreFragment implements BottomNavigationBar.OnTabSelectedListener
{
   @BindView(R2.id.bottom_navigation_bar)
   BottomNavigationBar bottomNavigationBar = null;

    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOURTH = 3;

    private SupportFragment[] mFragments = new SupportFragment[4];
    private int currentPosition = 0;

    @Override
    public Object setLayout()
    {
        return R.layout.fragment_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView)
    {
        initView();
        TestRestfulClient();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        init();

    }



    private void TestRestfulClient()
    {

        RestfulClient.builder()
                     .url("http://127.0.0.1/index")
                     .params("","")
                     .loader(getContext(), LoaderStyle.BallGridBeatIndicator)
                     .success(new iSuccess() {
                         @Override
                         public void onSuccess(String Response)
                         {
                                 Gson gson = new Gson();
                                 final String jsonData = Response;
                                 final MainPageJson mainPageJson = gson.fromJson(jsonData,MainPageJson.class);
                                 final ArrayList<MainPageJson.MainPageData> data = mainPageJson.getData();
                                 final int size = data.size();

                                     final MainPageJson.MainPageData mainPageData = data.get(0);
                                     final String imageUrl = mainPageData.getImageUrl();
                                     final int spanSize = mainPageData.getSpanSize();
                                     final String text = mainPageData.getText();
                                     final int id = mainPageData.getGoodsId();
                                     final ArrayList<String> banners = mainPageData.getBanners();
                                     final ArrayList<String> bannerImages = new ArrayList<>();
                                     Log.d("Hola",imageUrl+spanSize+text+id+banners.get(0));

                             }
                     })
                     .error(new iError() {
                         @Override
                         public void onError(int code, String msg)
                         {
                             Toast.makeText(getContext(),code,Toast.LENGTH_LONG).show();
                         }
                     })
                     .failure(new iFailure() {
                         @Override
                         public void onFaliure() {
                             Toast.makeText(getContext(),"Fail!",Toast.LENGTH_LONG).show();
                         }
                     })
                     .build()
                     .get();
    }

   private void init()
   {
       SupportFragment firstFragment = findChildFragment(MainFragment.class);
       if (firstFragment == null)
       {
           mFragments[FIRST] = new MainFragment();
           mFragments[SECOND] = new DiscoverFragment();
           mFragments[THIRD] = new CartFragment();
           mFragments[FOURTH] = new MineFragment();

           loadMultipleRootFragment(R.id.fragment_container, FIRST,
                   mFragments[FIRST],
                   mFragments[SECOND],
                   mFragments[THIRD],
                   mFragments[FOURTH]);
       } else
           {
           // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题
           // 这里我们需要拿到mFragments的引用
           mFragments[FIRST] = firstFragment;
           mFragments[SECOND] = findChildFragment(DiscoverFragment.class);
           mFragments[THIRD] =  findChildFragment(CartFragment.class);
           mFragments[FOURTH] = findChildFragment(MineFragment.class);
       }
   }

   private void initView()
   {
           bottomNavigationBar
                   .addItem(new BottomNavigationItem(R.drawable.ic_home_black_24dp,"首页"))
                   .addItem(new BottomNavigationItem(R.drawable.ic_search_black_24dp,"发现"))
                   .addItem(new BottomNavigationItem(R.drawable.ic_add_shopping_cart_black_24dp,"购物车"))
                   .addItem(new BottomNavigationItem(R.drawable.ic_account_circle_black_24dp,"我的"))
                   .setMode(BottomNavigationBar.MODE_FIXED)
                   .initialise();
           bottomNavigationBar.setTabSelectedListener(this);
   }

    @Override
    public void onTabSelected(int position)
    {
       showHideFragment(mFragments[position],mFragments[currentPosition]);
       currentPosition = position;
    }

    @Override
    public void onTabUnselected(int position)
    {

    }

    @Override
    public void onTabReselected(int position)
    {

    }
}
