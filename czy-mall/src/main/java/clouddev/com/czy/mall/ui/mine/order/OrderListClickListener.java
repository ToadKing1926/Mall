package clouddev.com.czy.mall.ui.mine.order;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;

/**
 * Created by 29737
 */

public class OrderListClickListener extends SimpleClickListener
{
    private OrderItemFragment FRAGMENT;

    public OrderListClickListener(OrderItemFragment fragment)
    {
        this.FRAGMENT = fragment;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position)
    {
        FRAGMENT.getSupportDelegate().start(OrderCommentFragment.create(position + 1));
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
