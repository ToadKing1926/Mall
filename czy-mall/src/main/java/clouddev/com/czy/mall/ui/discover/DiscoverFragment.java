package clouddev.com.czy.mall.ui.discover;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import clouddev.com.czy.fragment.CoreFragment;
import clouddev.com.czy.fragment.web.WebFragmentImpl;
import clouddev.com.czy.mall.R;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by 29737
 */

public class DiscoverFragment extends CoreFragment
{
    @Override
    public Object setLayout() {
        return R.layout.discover_fragment;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView)
    {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState)
    {
        super.onLazyInitView(savedInstanceState);
        final WebFragmentImpl webFragment = WebFragmentImpl.create("discover.html");
        webFragment.setTopFragment(this.getParentfragment());
        loadRootFragment(R.id.web_container,webFragment);
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator()
    {
        return new DefaultHorizontalAnimator();
    }
}
