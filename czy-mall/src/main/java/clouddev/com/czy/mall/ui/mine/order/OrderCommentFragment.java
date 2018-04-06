package clouddev.com.czy.mall.ui.mine.order;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.OnClick;
import clouddev.com.czy.GlideApp;
import clouddev.com.czy.fragment.CoreFragment;
import clouddev.com.czy.mall.R;
import clouddev.com.czy.mall.R2;
import clouddev.com.czy.ui.widget.AutoImageLayout;
import clouddev.com.czy.ui.widget.StarLayout;
import clouddev.com.czy.util.callback.CallBackManager;
import clouddev.com.czy.util.callback.CallBackType;
import clouddev.com.czy.util.callback.iGlobalCallback;

/**
 * Created by 29737
 */

public class OrderCommentFragment extends CoreFragment
{

    @BindView(R2.id.order_comment_avatar)
    ImageView mImageView = null;
    @BindView(R2.id.order_comment_rating)
    RatingBar mRatingBar = null;
    @BindView(R2.id.order_comment_desc)
    EditText mEditText = null;

    private static final String ARG_PRODUCT_ID = "PRODUCT_ID";

    private float rating = 0;
    private String desc = null;
    private int mProductId;
    private String[] avatars = {"https://i1.mifile.cn/a4/T1a5JjBbVT1RXrhCrK.jpg","https://c1.mifile.cn/f/i/16/chain/water1a//water1a-01.jpg","https://i1.mifile.cn/a1/pms_1519959193.42473450!220x220.jpg"};

    public static OrderCommentFragment create(@NonNull int productId)
    {
        final Bundle args = new Bundle();
        args.putInt(ARG_PRODUCT_ID,productId);
        final OrderCommentFragment fragment = new OrderCommentFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if(args != null)
        {
            mProductId = args.getInt(ARG_PRODUCT_ID,-1);
        }
    }

    @OnClick(R2.id.comment_commit)
    void OnCommitClick()
    {
        desc = mEditText.getText().toString();
        Toast.makeText(getContext(),"Rating:" + String.valueOf(rating) + "desc:" + desc,Toast.LENGTH_LONG).show();
    }

    @Override
    public Object setLayout()
    {
        return R.layout.order_comment_fragment;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView)
    {
        GlideApp.with(getContext())
                .load(avatars[mProductId - 1])
                .centerCrop()
                .into(mImageView);
        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener()
        {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b)
            {
                rating = v;
            }
        });
    }
}
