package clouddev.com.czy.mall.product;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import butterknife.BindView;
import clouddev.com.czy.fragment.CoreFragment;
import clouddev.com.czy.mall.R;
import clouddev.com.czy.mall.R2;

/**
 * Created by 29737
 */

public class ProductInfoFragment extends CoreFragment
{
    @BindView(R2.id.product_info_title)
    TextView mProductInfoTitle = null;
    @BindView(R2.id.product_info_desc)
    TextView mProductInfoDesc = null;
    @BindView(R2.id.product_info_price)
    TextView mProductInfoPrice = null;

    private static final String ARG_PRODUCT_INFO = "ARG_PRODUCT_INFO";
    private JSONObject mData = null;

    @Override
    public Object setLayout()
    {
        return R.layout.product_info_fragment;
    }

    public static ProductInfoFragment create(String productInfo)
    {
        final Bundle args = new Bundle();
        args.putString(ARG_PRODUCT_INFO,productInfo);
        final ProductInfoFragment fragment = new ProductInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        final String productInfo = args.getString(ARG_PRODUCT_INFO);
        mData = JSON.parseObject(productInfo);
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView)
    {
        final String name = mData.getString("name");
        final String desc = mData.getString("desc");
        final String price = mData.getString("price");

        mProductInfoTitle.setText(name);
        mProductInfoDesc.setText(desc);
        mProductInfoPrice.setText(price);
    }
}
