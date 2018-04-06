package clouddev.com.czy.ui.scanner;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;

import me.dm7.barcodescanner.core.ViewFinderView;

/**
 * Created by 29737
 */

public class czyViewFinderView extends ViewFinderView
{
    public czyViewFinderView(Context context)
    {
        this(context,null);
    }

    public czyViewFinderView(Context context, AttributeSet attributeSet)
    {
        super(context, attributeSet);
        mSquareViewFinder = true;
        mBorderPaint.setColor(Color.YELLOW);
        mLaserPaint.setColor(Color.YELLOW);
    }
}
