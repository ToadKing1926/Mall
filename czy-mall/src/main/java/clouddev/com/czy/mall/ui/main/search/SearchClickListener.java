package clouddev.com.czy.mall.ui.main.search;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;

import clouddev.com.czy.ui.recycler.MultipleFields;
import clouddev.com.czy.ui.recycler.MultipleItemEntity;
import clouddev.com.czy.util.callback.CallBackManager;
import clouddev.com.czy.util.callback.CallBackType;

/**
 * Created by 29737
 */

public class SearchClickListener extends SimpleClickListener
{

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position)
    {
        final MultipleItemEntity entity = (MultipleItemEntity) baseQuickAdapter.getData().get(position);
        final String searchProduct = entity.getField(MultipleFields.TEXT);
        CallBackManager.getInstance().getCallback(CallBackType.SEARCH).executeCallback(searchProduct);
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
