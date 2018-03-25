package clouddev.com.czy.mall.ui.mine.address;

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
import clouddev.com.czy.mall.converter.AddressDataConverter;
import clouddev.com.czy.network.RestfulClient;
import clouddev.com.czy.network.callback.iSuccess;
import clouddev.com.czy.ui.LoaderStyle;
import clouddev.com.czy.ui.recycler.MultipleItemEntity;

/**
 * Created by 29737
 */

public class AddressFragment extends CoreFragment implements iSuccess
{
    @BindView(R2.id.rv_address)
    RecyclerView mRecyclerView = null;
    @Override
    public Object setLayout()
    {
        return R.layout.address_fragment;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView)
    {
        RestfulClient.builder()
                     .params("","")
                     .url("/address")
                     .loader(getContext(), LoaderStyle.BallClipRotateMultipleIndicator)
                     .success(this)
                     .build()
                     .get();
    }

    @Override
    public void onSuccess(String response)
    {
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        final List<MultipleItemEntity> data = new AddressDataConverter().setJsonData(response).convert();
        final AddressAdapter addressAdapter = new AddressAdapter(data);
        mRecyclerView.setAdapter(addressAdapter);
    }
}
