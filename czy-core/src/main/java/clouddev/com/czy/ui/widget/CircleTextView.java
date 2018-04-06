package clouddev.com.czy.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by 29737
 */

public class CircleTextView extends AppCompatTextView
{
    private final Paint PAINT;
    private final PaintFlagsDrawFilter FILTER;

    public CircleTextView(Context context)
    {
        this(context,null);
    }

    public CircleTextView(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        PAINT = new Paint();
        FILTER = new PaintFlagsDrawFilter(0,Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
        PAINT.setColor(Color.RED);
        PAINT.setAntiAlias(true);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final int width = getMeasuredWidth();
        final int height = getMaxHeight();
        final int max = Math.max(width,height);
        setMeasuredDimension(max,max);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        canvas.setDrawFilter(FILTER);
        canvas.drawCircle(getWidth()/2,getHeight()/2 , Math.max(getWidth(),getHeight())/2,PAINT);
        super.onDraw(canvas);
    }
}
