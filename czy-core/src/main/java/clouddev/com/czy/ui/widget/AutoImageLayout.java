package clouddev.com.czy.ui.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.List;

import clouddev.com.czy.GlideApp;
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
    private LayoutParams mParams = null;
    private int mDeleteId = 0;
    private ImageView mTargetImage = null;
    private int mImageMargin = 0;
    private CoreFragment mFragment = null;
    private List<View> mLineView = null;
    private AlertDialog mTargetDialog = null;

    private static final String ICON_TEXT_PLUS = "{fa-plus}";
    private float mIconsSize = 0;
    private static final List<List<View>> ALL_VIEWS = new ArrayList<>();
    private static final List<Integer> LINE_HEIGHTS = new ArrayList<>();
    private boolean mIsInit = false;
    private boolean mInitOnLayout = false;

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
        mIconsSize = typedArray.getDimension(R.styleable.AutoImageLayout_icon_size,20);
        typedArray.recycle();
    }

    public final void onCrop(Uri uri)
    {
        createNewImageView();
        GlideApp.with(getContext())
                .load(uri)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(mTargetImage);
    }

    public final void setFragment(CoreFragment fragment)
    {
        this.mFragment = fragment;
    }

    private void createNewImageView()
    {
        mTargetImage = new ImageView(getContext());
        mTargetImage.setId(mCurNum);
        mTargetImage.setLayoutParams(mParams);
        mTargetImage.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                mDeleteId = view.getId();
                mTargetDialog.show();
                final Window window = mTargetDialog.getWindow();
                if(window != null)
                {
                    window.setContentView(R.layout.dialog_image_click);
                    window.setGravity(Gravity.BOTTOM);
                    window.setWindowAnimations(R.style.anim_panel_up_from_bottom);
                    window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    final WindowManager.LayoutParams params = window.getAttributes();
                    params.width = WindowManager.LayoutParams.MATCH_PARENT;
                    params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
                    params.dimAmount = 0.5f;
                    window.setAttributes(params);
                    window.findViewById(R.id.dialog_image_clicked_btn_delete).setOnClickListener(new OnClickListener()
                    {
                        @Override
                        public void onClick(View view)
                        {
                            final ImageView deleteImageView = findViewById(mDeleteId);
                            final AlphaAnimation alphaAnimation = new AlphaAnimation(1,0);
                            alphaAnimation.setDuration(500);
                            alphaAnimation.setRepeatCount(0);
                            alphaAnimation.setFillAfter(true);
                            alphaAnimation.setStartOffset(0);
                            deleteImageView.setAnimation(alphaAnimation);
                            alphaAnimation.start();
                            AutoImageLayout.this.removeView(deleteImageView);
                            mCurNum --;
                            if(mCurNum < mMaxNum)
                            {
                                mIconAdd.setVisibility(VISIBLE);
                            }
                            mTargetDialog.cancel();
                        }
                    });
                    window.findViewById(R.id.dialog_image_clicked_btn_tbd).setOnClickListener(new OnClickListener()
                    {
                        @Override
                        public void onClick(View view)
                        {
                            mTargetDialog.cancel();
                        }
                    });
                    window.findViewById(R.id.dialog_image_clicked_btn_cancel).setOnClickListener(new OnClickListener()
                    {
                        @Override
                        public void onClick(View view)
                        {
                            mTargetDialog.cancel();
                        }
                    });
                }

            }
        });

        this.addView(mTargetImage,mCurNum);
        mCurNum++;

        if(mCurNum > mMaxNum)
        {
            mIconAdd.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
       final int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
       final int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
       final int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
       final int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

       int width = 0;
       int height = 0;

       int lineWidth = 0;
       int lineHeight = 0;

       int cCount = getChildCount();
       for(int i = 0; i < cCount; i++)
       {
           final View child = getChildAt(i);
           measureChild(child,widthMeasureSpec,heightMeasureSpec);
           final MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
           int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
           int childHeight = child.getMeasuredHeight() + lp.topMargin +lp.bottomMargin;
           if(lineWidth + childWidth > sizeWidth - getPaddingLeft() - getPaddingRight())
           {
               width = Math.max(width,lineWidth);
               lineWidth = childWidth;
               height += lineHeight;
               lineHeight = childHeight;
           }
           else
           {
               lineWidth += childWidth;
               lineHeight = Math.max(lineHeight,childHeight);

           }
           if(i == cCount - 1)
           {
               width = Math.max(lineWidth,width);
               height += lineHeight;
           }
       }
       setMeasuredDimension(modeWidth == MeasureSpec.EXACTLY ? sizeWidth : width + getPaddingLeft() + getPaddingRight(),modeHeight == MeasureSpec.EXACTLY ? sizeHeight : height + getPaddingTop() + getPaddingBottom());
       final int imageSizeLen = sizeWidth / mLineNum;
       if(!mIsInit)
       {
           mParams = new LayoutParams(imageSizeLen,imageSizeLen);
           mIsInit = true;
       }

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b)
    {
        ALL_VIEWS.clear();
        LINE_HEIGHTS.clear();
        final int width = getWidth();
        int lineWidth = 0;
        int lineHeight = 0;

        if(!mInitOnLayout)
        {
            mLineView = new ArrayList<>();
            mInitOnLayout = true;
        }

        final int cCount = getChildCount();
        for(int i = 0; i < cCount; i++)
        {
            final View child = getChildAt(i);
            final MarginLayoutParams lp = (MarginLayoutParams)child.getLayoutParams();
            final int childWidth = child.getMeasuredWidth();
            final int childHeight = child.getMeasuredHeight();

            if(childWidth + lineWidth + lp.leftMargin + lp.rightMargin > width - getPaddingLeft() - getPaddingRight())
            {
                LINE_HEIGHTS.add(lineHeight);
                ALL_VIEWS.add(mLineView);
                lineWidth = 0;
                lineHeight = childHeight + lp.topMargin + lp.bottomMargin;
                mLineView.clear();
            }
            lineWidth += childWidth + lp.leftMargin + lp.rightMargin;
            lineHeight = Math.max(lineHeight,lineHeight + lp.topMargin + lp.bottomMargin);
            mLineView.add(child);
        }

        //最后一行
        LINE_HEIGHTS.add(lineHeight);
        ALL_VIEWS.add(mLineView);

        int left = getPaddingLeft();
        int top = getPaddingTop();
        final int lineNum = ALL_VIEWS.size();

        for(int i = 0; i < lineNum; i++)
        {
            mLineView = ALL_VIEWS.get(i);
            lineHeight = LINE_HEIGHTS.get(i);
            final int size = mLineView.size();
            for(int j = 0; j < size; j++ )
            {
                final View child = mLineView.get(j);
                if(child.getVisibility() == GONE)
                {
                    continue;
                }
                final MarginLayoutParams lp = (MarginLayoutParams)child.getLayoutParams();
                final int lc = left + lp.leftMargin;
                final int tc = top + lp.topMargin;
                final int rc = lc + child.getMeasuredWidth() - mImageMargin;
                final int bc = tc + child.getMeasuredHeight();

                child.layout(lc,tc,rc,bc);
                left += child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            }
            left += getPaddingLeft();
            top += lineHeight;
        }
        mIconAdd.setLayoutParams(mParams);
        mInitOnLayout = false;
    }

    private void initAddIcon()
    {
        mIconAdd = new IconTextView(getContext());
        mIconAdd.setText(ICON_TEXT_PLUS);
        mIconAdd.setGravity(Gravity.CENTER);
        mIconAdd.setTextSize(mIconsSize);
        mIconAdd.setBackgroundResource(R.drawable.border_auto_image_layout);
        mIconAdd.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                mFragment.startCameraWithCheck();
            }
        });
        this.addView(mIconAdd);
    }

    @Override
    protected void onFinishInflate()
    {
        super.onFinishInflate();
        initAddIcon();
        mTargetDialog = new AlertDialog.Builder(getContext()).create();
    }
}
