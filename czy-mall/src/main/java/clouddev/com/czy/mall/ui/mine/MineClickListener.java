package clouddev.com.czy.mall.ui.mine;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;

import clouddev.com.czy.fragment.CoreFragment;
import clouddev.com.czy.mall.ui.mine.list.ListBean;

/**
 * Created by 29737
 */

public class MineClickListener extends SimpleClickListener
{
    private final CoreFragment FRAGMENT;

    public MineClickListener(CoreFragment fragment)
    {
        this.FRAGMENT = fragment;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position)
    {
       final ListBean bean = (ListBean)adapter.getData().get(position);
       int id = bean.getId();
       switch (id)
       {
           case 1:
               FRAGMENT.getParentfragment().getSupportDelegate().start(bean.getFragment());
               break;
           case 2:
               FRAGMENT.getParentfragment().getSupportDelegate().start(bean.getFragment());
               break;
           case 3:
               FRAGMENT.getParentfragment().getSupportDelegate().start(bean.getFragment());
               break;
       }
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position)
    {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position)
    {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position)
    {

    }
}
