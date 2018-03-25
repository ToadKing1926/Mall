package clouddev.com.czy.ui.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.List;

import clouddev.com.czy.R;
import clouddev.com.czy.fragment.CoreFragment;

/**
 * Created by 29737
 */

public class AutoImageLayout extends LinearLayout
{
    private int mCurNum = 0;
    private int mMaxNum = 0;
    private int mLineNum = 0;
    private IconTextView mIconAdd = null;
    private LayoutParams lp = null;
    private int mDeleteId = 0;
    private ImageView mTargetImage = null;
    private int mImageMargin = 0;
    private CoreFragment mFragment = null;
    private List<View> mListView = null;
    private AlertDialog mTargetDialog = null;

    private static final String ICON_TEXT_PLUS = "{fa-plus}";
    private float mIconsSize = 0;
    private static final List<List<View>> ALL_VIEWS = new ArrayList<>();
    private static final List<Integer> LIST_HEIGHTS = new ArrayList<>();

    public AutoImageLayout(Context context)
    {
        this(context,null);
    }

    public AutoImageLayout(Context context, @Nullable AttributeSet attrs)
    {
        this(context, attrs,0);
    }

    public AutoImageLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        final TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.AutoImageLayout);
        mMaxNum = typedArray.getInteger(R.styleable.AutoImageLayout_max_count,1);
        mLineNum = typedArray.getInteger(R.styleable.AutoImageLayout_line_count,3);
        mImageMargin = typedArray.getInteger(R.styleable.AutoImageLayout_item_margin,0);
        mIconsSize = typedArray.getInteger(R.styleable.AutoImageLayout_icon_size,20);
        typedArray.recycle();
    }
}
