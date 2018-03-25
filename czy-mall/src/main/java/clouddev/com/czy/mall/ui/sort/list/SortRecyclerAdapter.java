package clouddev.com.czy.mall.ui.sort.list;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import clouddev.com.czy.fragment.CoreFragment;
import clouddev.com.czy.mall.R;
import clouddev.com.czy.mall.ui.sort.SortFragment;
import clouddev.com.czy.mall.ui.sort.content.ContentFragment;
import clouddev.com.czy.ui.recycler.ItemType;
import clouddev.com.czy.ui.recycler.MultipleFields;
import clouddev.com.czy.ui.recycler.MultipleItemEntity;
import clouddev.com.czy.ui.recycler.MultipleRecyclerViewAdapter;
import clouddev.com.czy.ui.recycler.MultipleViewHolder;

/**
 * Created by 29737
 */

public class SortRecyclerAdapter extends MultipleRecyclerViewAdapter
{
    private final SortFragment FRAGMENT;
    private int mPrePosition = 0;

    protected SortRecyclerAdapter(List<MultipleItemEntity> data, SortFragment sortFragment)
    {
        super(data);
        this.FRAGMENT = sortFragment;
        addItemType(ItemType.SORT_MENU, R.layout.item_vertical_menu_list);
    }

    @Override
    protected void convert(final MultipleViewHolder holder,final MultipleItemEntity entity)
    {
        super.convert(holder, entity);
        switch(holder.getItemViewType())
        {
            case ItemType.SORT_MENU:
                final String text = entity.getField(MultipleFields.TEXT);
                final boolean isClicked = entity.getField(MultipleFields.TAG);
                final TextView name = holder.getView(R.id.vertical_item_name);
                final View line = holder.getView(R.id.view_line);
                final View itemView = holder.itemView;
                itemView.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        final int curPos = holder.getAdapterPosition();
                        if(mPrePosition != curPos)
                        {
                            //还原上一个
                            getData().get(mPrePosition).setField(MultipleFields.TAG,false);
                            notifyItemChanged(mPrePosition);

                            //更新选中的Item
                            entity.setField(MultipleFields.TAG,true);
                            notifyItemChanged(curPos);
                            mPrePosition = curPos;

                            final int contentId = getData().get(curPos).getField(MultipleFields.ID);
                            showContent(contentId);
                        }
                    }
                });

                if(!isClicked)
                {
                    line.setVisibility(View.INVISIBLE);
                    name.setTextColor(ContextCompat.getColor(mContext,R.color.we_chat_black));
                    itemView.setBackgroundColor(ContextCompat.getColor(mContext,R.color.item_background));
                }
                else
                {
                    line.setVisibility(View.VISIBLE);
                    name.setTextColor(ContextCompat.getColor(mContext,R.color.app_main));
                    line.setBackgroundColor(ContextCompat.getColor(mContext,R.color.app_main));
                    itemView.setBackgroundColor(Color.WHITE);
                }
                holder.setText(R.id.vertical_item_name,text);
                break;
            default:
                break;
        }
    }


    private void showContent(int contentId)
    {
        final ContentFragment contentFragment = ContentFragment.newInstance(contentId);
        switchContent(contentFragment);
    }

    private void switchContent(ContentFragment fragment)
    {
        final CoreFragment contentFragment = FRAGMENT.findChildFragment(ContentFragment.class);
        if(contentFragment != null)
        {
            contentFragment.replaceFragment(fragment,false);
        }
    }
}
