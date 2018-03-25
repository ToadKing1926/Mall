package clouddev.com.czy.mall.ui.mine.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import clouddev.com.czy.fragment.CoreFragment;
import clouddev.com.czy.mall.R;
import clouddev.com.czy.mall.R2;
import clouddev.com.czy.mall.converter.OrderListDataConverter;
import clouddev.com.czy.mall.ui.mine.settings.NameFragment;
import clouddev.com.czy.network.RestfulClient;
import clouddev.com.czy.network.callback.iSuccess;
import clouddev.com.czy.ui.LoaderStyle;
import clouddev.com.czy.ui.recycler.MultipleItemEntity;

/**
 * Created by 29737
 */

public class OrderItemFragment extends CoreFragment
{
    private String mType = null;

    @BindView(R2.id.order_list)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout()
    {
        return R.layout.order_fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        mType = args.getString("TYPE");
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView)
    {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState)
    {
        super.onLazyInitView(savedInstanceState);
        RestfulClient.builder()
                     .loader(getContext(), LoaderStyle.BallClipRotateMultipleIndicator)
                     .params("","")
                     .url("/order_list")
                     .success(new iSuccess()
                     {
                         @Override
                         public void onSuccess(String response)
                         {
                             final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                             mRecyclerView.setLayoutManager(layoutManager);
                             final List<MultipleItemEntity> data = new OrderListDataConverter().setJsonData(response).convert();
                             final OrderListAdapter adapter = new OrderListAdapter(data);
                             mRecyclerView.setAdapter(adapter);
                             mRecyclerView.addOnItemTouchListener(new OrderListClickListener(OrderItemFragment.this));
                         }
                     })
                     .build()
                     .get();
    }


}
