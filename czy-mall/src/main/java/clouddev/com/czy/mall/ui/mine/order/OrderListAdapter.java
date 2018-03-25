package clouddev.com.czy.mall.ui.mine.order;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import clouddev.com.czy.GlideApp;
import clouddev.com.czy.mall.R;
import clouddev.com.czy.mall.converter.CartDataConverter;
import clouddev.com.czy.mall.converter.OrderListDataConverter;
import clouddev.com.czy.ui.recycler.MultipleFields;
import clouddev.com.czy.ui.recycler.MultipleItemEntity;
import clouddev.com.czy.ui.recycler.MultipleRecyclerViewAdapter;
import clouddev.com.czy.ui.recycler.MultipleViewHolder;

/**
 * Created by 29737
 */

public class OrderListAdapter extends MultipleRecyclerViewAdapter
{
    public OrderListAdapter(List<MultipleItemEntity> data)
    {
        super(data);
        addItemType(OrderListDataConverter.ITEM_ORDER_LIST, R.layout.item_order_list);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity)
    {
        super.convert(holder, entity);
        switch (holder.getItemViewType())
        {
            case OrderListDataConverter.ITEM_ORDER_LIST:
                final ImageView imageView = holder.getView(R.id.image_order_list);
                final TextView title = holder.getView(R.id.order_list_title);
                final TextView price = holder.getView(R.id.order_list_price);
                final TextView time = holder.getView(R.id.order_list_time);

                final String titles = entity.getField(MultipleFields.TITLE);
                final String times = entity.getField(MultipleFields.TIME);
                final double prices = entity.getField(CartDataConverter.PRICE);
                final String imageUrl = entity.getField(MultipleFields.IMAGE_URL);

                title.setText(titles);
                price.setText("￥"+String.valueOf(prices));
                time.setText(times);
                GlideApp.with(mContext)
                        .load(imageUrl)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .centerCrop()
                        .dontAnimate()
                        .into(imageView);

                break;
            default:
                break;
        }
    }
}
