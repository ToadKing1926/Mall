package clouddev.com.czy.mall.ui;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;

import clouddev.com.czy.mall.R;

/**
 * Created by 29737
 */

public class TranslucentBehavior extends CoordinatorLayout.Behavior<Toolbar>
{
    private int mDistanceY = 0;//顶部距离

    public TranslucentBehavior(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, Toolbar child, View dependency)
    {
        return dependency.getId() == R.id.main_recycler_view;
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull Toolbar child, @NonNull View directTargetChild, @NonNull View target, int axes, int type)
    {
        return true;
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull Toolbar child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type)
    {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
        mDistanceY += dy;//增加滑动距离
        final int targetHeight = child.getBottom();//调整Toolbar高度
        //滑动小于Toolbar高度调整渐变色
        if(mDistanceY > 0 && mDistanceY <= targetHeight)
        {
            final float scale = (float) mDistanceY / targetHeight;
            final float alpha = scale * 255;
            child.setBackgroundColor(Color.argb((int)alpha,255,124,2));
        }
        else if(mDistanceY > targetHeight)//直接设为预定色
        {
            child.setBackgroundColor(Color.rgb(255,124,2));
        }
    }
}
