package clouddev.com.czy.ui.recycler;

import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;


import java.util.ArrayList;
import java.util.List;

import clouddev.com.czy.GlideApp;
import clouddev.com.czy.R;
import clouddev.com.czy.util.GlideImageLoader;


/**
 * Created by 29737 on 2018/3/5.
 */

public class MultipleRecyclerViewAdapter extends BaseMultiItemQuickAdapter<MultipleItemEntity,MultipleViewHolder>
    implements BaseQuickAdapter.SpanSizeLookup
{
    //Banner只初始化一次
    private boolean mIsInitBanner = false;

    private MultipleRecyclerViewAdapter(List<MultipleItemEntity> data)
    {
        super(data);
        init();
    }

    public static MultipleRecyclerViewAdapter create(List<MultipleItemEntity> data)
    {
        return new MultipleRecyclerViewAdapter(data);
    }

    public static MultipleRecyclerViewAdapter create(DataConverter converter)
    {
        return new MultipleRecyclerViewAdapter(converter.convert());
    }

    private void init()
    {
        //加载不同布局
        addItemType(ItemType.TEXT, R.layout.item_text);
        addItemType(ItemType.IMAGE,R.layout.item_image);
        addItemType(ItemType.IMAGE_TEXT,R.layout.item_text_image);
        addItemType(ItemType.BANNER,R.layout.item_banner);
        //设置宽度监听
        setSpanSizeLookup(this);
        openLoadAnimation();
        //多次执行动画
        isFirstOnly(false);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity)
    {
       final String text;
       final String imageUrl;
       final ArrayList<String> bannerImages;
       switch (holder.getItemViewType())
       {
           case ItemType.TEXT:
               text = entity.getField(MultipleFields.TEXT);
               holder.setText(R.id.text_single,text);
               break;
           case ItemType.IMAGE:
               imageUrl = entity.getField(MultipleFields.IMAGE_URL);
               GlideApp.with(mContext)
                       .load(imageUrl)
                       .diskCacheStrategy(DiskCacheStrategy.ALL)
                       .dontAnimate()
                       .centerCrop()
                       .into((ImageView)holder.getView(R.id.image_single));
               break;
           case ItemType.IMAGE_TEXT:
               text = entity.getField(MultipleFields.TEXT);
               imageUrl = entity.getField(MultipleFields.IMAGE_URL);
               holder.setText(R.id.text_combine,text);
               GlideApp.with(mContext)
                       .load(imageUrl)
                       .diskCacheStrategy(DiskCacheStrategy.ALL)
                       .dontAnimate()
                       .centerCrop()
                       .into((ImageView)holder.getView(R.id.image_combine));
               break;
           case ItemType.BANNER:
               if(!mIsInitBanner)
               {
                   bannerImages = entity.getField(MultipleFields.BANNERS);
                   Banner banner = holder.getView(R.id.banner_single);
                   banner.setImageLoader(new GlideImageLoader())
                         .setImages(bannerImages)
                         .setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                         .start();
               }
               break;

           default:
               break;
       }
    }

    @Override
    protected MultipleViewHolder createBaseViewHolder(View view)
    {
        return MultipleViewHolder.create(view);
    }

    @Override
    public int getSpanSize(GridLayoutManager gridLayoutManager, int position)
    {
        return getData().get(position).getField(MultipleFields.SPAN_SIZE);
    }
}
