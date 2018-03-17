package clouddev.com.czy.mall.ui.sort.content;

import android.support.v7.widget.AppCompatImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import clouddev.com.czy.GlideApp;
import clouddev.com.czy.mall.R;
import clouddev.com.czy.mall.bean.SectionContentItemBean;
import clouddev.com.czy.mall.bean.SortContentBean;

/**
 * Created by 29737 on 2018/3/12.
 */

public class ContentAdapter extends BaseSectionQuickAdapter<SortContentBean,BaseViewHolder>
{

    public ContentAdapter(int layoutResId, int sectionHeadResId, List<SortContentBean> data)
    {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, SortContentBean item)
    {
        helper.setText(R.id.header,item.header);
        helper.setVisible(R.id.load_more,item.isMore());
        helper.addOnClickListener(R.id.load_more);
    }

    @Override
    protected void convert(BaseViewHolder helper, SortContentBean item)
    {
        //t代表传入的泛型
       final String thumb = item.t.getThumb();
       final String name = item.t.getProductName();
       final int id = item.t.getId();
       final SectionContentItemBean sectionContentItemBean = item.t;
       helper.setText(R.id.tv,name);
       final AppCompatImageView appCompatImageView = helper.getView(R.id.iv);

        GlideApp.with(mContext)
                .load(thumb)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .into(appCompatImageView);
    }
}
