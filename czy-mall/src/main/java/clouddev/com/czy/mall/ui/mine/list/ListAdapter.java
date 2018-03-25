package clouddev.com.czy.mall.ui.mine.list;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import clouddev.com.czy.mall.R;

import static clouddev.com.czy.mall.ui.mine.list.ListItemType.*;

/**
 * Created by 29737 on 2018/3/25.
 */

public class ListAdapter extends BaseMultiItemQuickAdapter<ListBean,BaseViewHolder>
{


    public ListAdapter(List<ListBean> data)
    {
        super(data);
        addItemType(ARROW_ITEM_LAYOUT, R.layout.arrow_item_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, ListBean item)
    {
        switch (helper.getItemViewType())
        {
            case ARROW_ITEM_LAYOUT:
                helper.setText(R.id.mine_arrow_text,item.getText());
                helper.setText(R.id.mine_arrow_value,item.getValue());
        }
    }
}
