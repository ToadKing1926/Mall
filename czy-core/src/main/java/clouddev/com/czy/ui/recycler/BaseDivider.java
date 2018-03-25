package clouddev.com.czy.ui.recycler;

import android.support.annotation.ColorInt;

import com.choices.divider.DividerItemDecoration;

/**
 * Created by 29737
 */

public class BaseDivider extends DividerItemDecoration
{
    private BaseDivider(@ColorInt int color, int size)
    {
        setDividerLookup(new DividerLookupImpl(color,size));
    }

    public static BaseDivider create(@ColorInt int color, int size)
    {
        return new BaseDivider(color,size);
    }
}
