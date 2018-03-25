package clouddev.com.czy.mall.ui.mine.list;



import android.graphics.Color;
import android.support.v7.widget.SwitchCompat;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import clouddev.com.czy.GlideApp;
import clouddev.com.czy.mall.R;

import static clouddev.com.czy.mall.ui.mine.list.ListItemType.*;

/**
 * Created by 29737
 */

public class ListAdapter extends BaseMultiItemQuickAdapter<ListBean,BaseViewHolder>
{


    public ListAdapter(List<ListBean> data)
    {
        super(data);
        addItemType(ARROW_ITEM_LAYOUT, R.layout.arrow_item_layout);
        addItemType(ARROW_ITEM_AVATAR,R.layout.arrow_item_avatar);
        addItemType(ARROW_ITEM_SWITCH,R.layout.arrow_switch_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, ListBean item)
    {

        switch (helper.getItemViewType())
        {
            case ARROW_ITEM_LAYOUT:
                helper.setText(R.id.mine_arrow_text,item.getText());
                helper.setText(R.id.mine_arrow_value,item.getValue());
                break;
            case ARROW_ITEM_AVATAR:
                GlideApp.with(mContext)
                        .load(item.getImageUrl())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .centerCrop()
                        .dontAnimate()
                        .into((ImageView)helper.getView(R.id.arrow_avatar));
                break;
            case ARROW_ITEM_SWITCH:
                helper.setText(R.id.arrow_switch_text,item.getText());
                final SwitchCompat switchCompat = helper.getView(R.id.list_item_switch);
                switchCompat.setChecked(false);
                switchCompat.setOnCheckedChangeListener(item.getListener());
                break;
            default:
                break;
        }
    }
}
