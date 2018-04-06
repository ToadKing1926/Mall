package clouddev.com.czy.mall.product;

import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import clouddev.com.czy.GlideApp;
import clouddev.com.czy.mall.R;
import clouddev.com.czy.ui.recycler.ItemType;
import clouddev.com.czy.ui.recycler.MultipleFields;
import clouddev.com.czy.ui.recycler.MultipleItemEntity;
import clouddev.com.czy.ui.recycler.MultipleRecyclerViewAdapter;
import clouddev.com.czy.ui.recycler.MultipleViewHolder;

/**
 * Created by 29737
 */

public class ImageTabAdapter extends MultipleRecyclerViewAdapter
{
    public ImageTabAdapter(List<MultipleItemEntity> data)
    {
        super(data);
        addItemType(ItemType.BIG_SINGLE_IMAGE, R.layout.item_image_detail);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity)
    {
        super.convert(holder, entity);
        final int type = holder.getItemViewType();
        switch (type)
        {
            case ItemType.BIG_SINGLE_IMAGE:
                final ImageView imageView = holder.getView(R.id.image_rv_item);
                final String url = entity.getField(MultipleFields.IMAGE_URL);
                GlideApp.with(mContext)
                        .load(url)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .dontAnimate()
                        .into(imageView);
                break;
            default:
                break;
        }
    }
}
