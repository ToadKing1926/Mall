package clouddev.com.czy.mall.ui.mine.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import clouddev.com.czy.fragment.CoreFragment;
import clouddev.com.czy.mall.R;
import clouddev.com.czy.mall.R2;
import clouddev.com.czy.ui.widget.StarLayout;

/**
 * Created by 29737
 */

public class OrderCommentFragment extends CoreFragment
{

    @BindView(R2.id.star_layout)
    StarLayout mStarLayout = null;

    @OnClick(R2.id.comment_commit)
    void OnCommitClick()
    {
        Toast.makeText(getContext(),String.valueOf(mStarLayout.getStarCount()),Toast.LENGTH_LONG).show();
    }

    @Override
    public Object setLayout()
    {
        return R.layout.order_comment_fragment;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView)
    {

    }
}
