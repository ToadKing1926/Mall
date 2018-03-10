package clouddev.mall;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import butterknife.BindView;
import clouddev.com.czy.fragment.CoreFragment;
import clouddev.com.czy.mall.R;
import clouddev.com.czy.mall.R2;
import clouddev.com.czy.mall.ui.CartFragment;
import clouddev.com.czy.mall.ui.DiscoverFragment;
import clouddev.com.czy.mall.ui.MainFragment;
import clouddev.com.czy.mall.ui.MineFragment;
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
        return R.layout.example_fragment;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView)
    {
        initView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        init();
    }



    private void TestRestfulClient()
    {

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
