package clouddev.com.czy.ui.recycler;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by 29737
 */

public class MultipleViewHolder extends BaseViewHolder
{
    private MultipleViewHolder(View view)
    {
        super(view);
    }

    public static MultipleViewHolder create(View view)
    {
        return new MultipleViewHolder(view);
    }
}
