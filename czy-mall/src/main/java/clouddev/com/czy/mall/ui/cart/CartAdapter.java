package clouddev.com.czy.mall.ui.cart;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.List;

import clouddev.com.czy.GlideApp;
import clouddev.com.czy.app.appInit;
import clouddev.com.czy.mall.R;
import clouddev.com.czy.mall.converter.CartDataConverter;
import clouddev.com.czy.ui.recycler.MultipleFields;
import clouddev.com.czy.ui.recycler.MultipleItemEntity;
import clouddev.com.czy.ui.recycler.MultipleRecyclerViewAdapter;
import clouddev.com.czy.ui.recycler.MultipleViewHolder;

/**
 * Created by 29737 on 2018/3/18.
 */

public class CartAdapter extends MultipleRecyclerViewAdapter
{
    private boolean mIsSelectedAll = true;
    private iCartItemListener mCartItemListener = null;
    private double mTotalPrice = 0.0;

    public static final int POSITION = 12;
    public static final int SELECTED = 11;

    protected CartAdapter(List<MultipleItemEntity> data)
    {
        super(data);
        for(MultipleItemEntity entity:data)
        {
            final double price = entity.getField(CartDataConverter.PRICE);
            final int count = entity.getField(MultipleFields.COUNT);
            final double total = price * count;
            mTotalPrice += total;
        }
        addItemType(CartDataConverter.CART_ITEM, R.layout.item_cart);
    }

    public void calcTotalPrice()
    {

    }

    public void setIsSelectedAll(boolean isSelectedAll)
    {
        this.mIsSelectedAll = isSelectedAll;
    }

    public void setCartItemListener(iCartItemListener mCartItemListener)
    {
        this.mCartItemListener = mCartItemListener;
    }

    public double getTotalPrice() {
        return mTotalPrice;
    }

    public void setTotalPrice(double price)
    {
        this.mTotalPrice = price;
    }

    @Override
    protected void convert(MultipleViewHolder holder, final MultipleItemEntity entity)
    {
        super.convert(holder, entity);
        switch (holder.getItemViewType())
        {

            case CartDataConverter.CART_ITEM:
                final int id = entity.getField(MultipleFields.ID);
                final String titles = entity.getField(MultipleFields.NAME);
                final String descs = entity.getField(MultipleFields.TEXT);
                final String thumbUrl = entity.getField(MultipleFields.IMAGE_URL);
                final int counts = entity.getField(MultipleFields.COUNT);
                final double prices = entity.getField(CartDataConverter.PRICE);

                final ImageView thumb = holder.getView(R.id.image_item_cart);
                final TextView title = holder.getView(R.id.cart_product_title);
                final TextView desc = holder.getView(R.id.cart_product_desc);
                final TextView price = holder.getView(R.id.cart_product_price);
                final TextView count = holder.getView(R.id.cart_count);
                final IconTextView minus = holder.getView(R.id.icon_item_minus);
                final IconTextView plus = holder.getView(R.id.icon_item_plus);
                final IconTextView iconIsSelected = holder.getView(R.id.icon_item_cart);

                title.setText(titles);
                desc.setText(descs);
                price.setText("￥"+String.valueOf(prices));
                count.setText(String.valueOf(counts));
                GlideApp.with(mContext)
                        .load(thumbUrl)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .centerCrop()
                        .dontAnimate()
                        .into(thumb);

                entity.setField(CartAdapter.SELECTED,mIsSelectedAll);
                final boolean isSelected = entity.getField(CartAdapter.SELECTED);
                if(isSelected)
                {
                    iconIsSelected.setTextColor(ContextCompat.getColor(appInit.getApplication(),R.color.app_main));
                }
                else
                {
                    iconIsSelected.setTextColor(Color.GRAY);
                }

                iconIsSelected.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        final boolean curSelected = entity.getField(CartAdapter.SELECTED);
                        final int curCount = Integer.parseInt(count.getText().toString());
                        if(curSelected)
                        {
                            iconIsSelected.setTextColor(Color.GRAY);
                            entity.setField(CartAdapter.SELECTED,false);
                            mTotalPrice -= prices * curCount;
                            mCartItemListener.onItemClick(0);
                            mIsSelectedAll = false;
                        }
                        else
                        {
                            iconIsSelected.setTextColor(ContextCompat.getColor(appInit.getApplication(),R.color.app_main));
                            entity.setField(CartAdapter.SELECTED,true);
                            mTotalPrice += prices * curCount;
                            mCartItemListener.onItemClick(prices * curCount);
                        }
                    }
                });

                minus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int countNum = Integer.parseInt(count.getText().toString());
                        final boolean isSelected = entity.getField(CartAdapter.SELECTED);
                        if(countNum > 1 && isSelected)
                        {
                            //TODO:请求服务器
                            countNum--;
                            count.setText(String.valueOf(countNum));
                            entity.setField(MultipleFields.COUNT,countNum);
                            if(mCartItemListener != null)
                            {
                                mTotalPrice -= prices;
                                final double itemTotal = countNum * prices;
                                mCartItemListener.onItemClick(itemTotal);
                            }
                        }
                    }
                });

                plus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final boolean isSelected = entity.getField(CartAdapter.SELECTED);
                        if(isSelected)
                        {
                            int countNum = Integer.parseInt(count.getText().toString());
                            //TODO:请求服务器
                            countNum++;
                            count.setText(String.valueOf(countNum));
                            entity.setField(MultipleFields.COUNT,countNum);
                            if(mCartItemListener != null)
                            {
                                mTotalPrice += prices;
                                final double itemTotal = countNum * prices;
                                mCartItemListener.onItemClick(itemTotal);
                            }
                        }
                    }
                });
                break;
            default:
                break;
        }
    }


}
