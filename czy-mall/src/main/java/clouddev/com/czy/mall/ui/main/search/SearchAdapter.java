package clouddev.com.czy.mall.ui.main.search;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import clouddev.com.czy.mall.R;
import clouddev.com.czy.mall.converter.SearchDataConverter;
import clouddev.com.czy.ui.recycler.ItemType;
import clouddev.com.czy.ui.recycler.MultipleFields;
import clouddev.com.czy.ui.recycler.MultipleItemEntity;
import clouddev.com.czy.ui.recycler.MultipleRecyclerViewAdapter;
import clouddev.com.czy.ui.recycler.MultipleViewHolder;

/**
 * Created by 29737
 */

public class SearchAdapter extends MultipleRecyclerViewAdapter
{
    public SearchAdapter(List<MultipleItemEntity> data)
    {
        super(data);
        addItemType(SearchDataConverter.ITEM_SEARCH, R.layout.item_search);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity)
    {
        super.convert(holder, entity);
        switch (entity.getItemType())
        {
            case SearchDataConverter.ITEM_SEARCH:
                final TextView searchItem = holder.getView(R.id.search_item);
                final String history = entity.getField(MultipleFields.TEXT);
                searchItem.setText(history);
                break;
            default:
                break;
        }
    }

}
