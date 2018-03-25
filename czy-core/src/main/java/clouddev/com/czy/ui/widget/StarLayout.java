package clouddev.com.czy.ui.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;

import clouddev.com.czy.R;

/**
 * Created by 29737
 */

public class StarLayout extends LinearLayout implements View.OnClickListener
{
    private static final CharSequence ICON_UNSELECT = "{fa-star-o}";
    private static final CharSequence ICON_SELECT = "{fa-star}";
    private static final ArrayList<IconTextView> STAR_LIST = new ArrayList<>();

    public StarLayout(Context context)
    {
        this(context,null);
    }

    public StarLayout(Context context, @Nullable AttributeSet attrs)
    {
        this(context, attrs,0);
    }

    public StarLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        initStars();
    }

    private void initStars()
    {
        for(int i = 0;i < 5;i++)
        {
            final IconTextView star = new IconTextView(getContext());
            star.setGravity(Gravity.CENTER);
            final LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            lp.weight = 1;
            star.setLayoutParams(lp);
            star.setText(ICON_UNSELECT);
            star.setTag(R.id.star_count,i);
            star.setTag(R.id.star_is_selected,false);
            star.setOnClickListener(this);
            STAR_LIST.add(star);
            this.addView(star);
        }
    }

    private void selectStar(int count)
    {
        for(int i = 0;i<=count;i++)
        {
            if(i <= count)
            {
                final IconTextView star = STAR_LIST.get(i);
                star.setText(ICON_SELECT);
                star.setTextColor(Color.YELLOW);
                star.setTag(R.id.star_is_selected,true);
            }
        }
    }

    private void unSelectStar(int count)
    {
        for(int i = 0;i < 5;i++)
        {
            if(i >= count)
            {
                final IconTextView star = STAR_LIST.get(i);
                star.setText(ICON_UNSELECT);
                star.setTextColor(Color.GRAY);
                star.setTag(R.id.star_is_selected,false);
            }
        }
    }

    public int getStarCount()
    {
        int count = 0;
        for(int i = 0;i < 5;i++)
        {
            final IconTextView star = STAR_LIST.get(i);
            final boolean isSelected = (boolean)star.getTag(R.id.star_is_selected);
            if(isSelected)
            {
                count++;
            }
        }
        return count;
    }

    @Override
    public void onClick(View view)
    {
         final IconTextView star = (IconTextView)view;
         final int count = (int)star.getTag(R.id.star_count);
         final boolean isSelected = (boolean)star.getTag(R.id.star_is_selected);
         if(!isSelected)
         {
             selectStar(count);
         }
         else
         {
             unSelectStar(count);
         }
    }
}
