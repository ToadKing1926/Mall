package clouddev.com.czy.mall.ui.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import clouddev.com.czy.fragment.CoreFragment;
import clouddev.com.czy.mall.R;
import clouddev.com.czy.mall.ui.sort.content.ContentFragment;
import clouddev.com.czy.mall.ui.sort.list.VerticalListFragment;

/**
 * Created by 29737
 */

public class SortFragment extends CoreFragment
{
    @Override
    public Object setLayout()
    {
        return R.layout.sort_fragment;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView)
    {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState)
    {
        super.onLazyInitView(savedInstanceState);
        final VerticalListFragment verticalListFragment = new VerticalListFragment();
        loadRootFragment(R.id.vertical_list_container,verticalListFragment);
        loadRootFragment(R.id.sort_content_container, ContentFragment.newInstance(1),false,false);
    }
}
