package clouddev.com.czy.mall.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import clouddev.com.czy.fragment.CoreFragment;
import clouddev.com.czy.mall.R;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by 29737
 */

public class ProductDetailFragment extends CoreFragment
{
    public static ProductDetailFragment create()
    {
        return new ProductDetailFragment();
    }

    @Override
    public Object setLayout()
    {
        return R.layout.product_details_fragment;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView)
    {

    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator()
    {
        return new DefaultHorizontalAnimator();
    }
}
