package clouddev.com.czy.mall.ui.main;

import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;

import clouddev.com.czy.fragment.CoreFragment;
import clouddev.com.czy.mall.product.ProductDetailFragment;
import clouddev.com.czy.ui.recycler.MultipleFields;
import clouddev.com.czy.ui.recycler.MultipleItemEntity;

/**
 * Created by 29737
 */

public class MainItemClickListener extends SimpleClickListener
{
    private final CoreFragment FRAGMENT;

    private MainItemClickListener(CoreFragment coreFragment)
    {
        this.FRAGMENT = coreFragment;
    }

    public static MainItemClickListener create(CoreFragment coreFragment)
    {
        return new MainItemClickListener(coreFragment);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position)
    {
        MultipleItemEntity entity = (MultipleItemEntity) baseQuickAdapter.getData().get(position);
        int productId = entity.getField(MultipleFields.ID);
        ProductDetailFragment productDetailFragment = ProductDetailFragment.create(productId);
        FRAGMENT.start(productDetailFragment);
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e)
    {
        return super.onInterceptTouchEvent(rv, e);
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e)
    {
        super.onTouchEvent(rv, e);
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept)
    {
        super.onRequestDisallowInterceptTouchEvent(disallowIntercept);
    }

    @Override
    public boolean inRangeOfView(View view, MotionEvent ev)
    {
        return super.inRangeOfView(view, ev);
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
