package clouddev.com.czy.mall.ui.mine.address;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import clouddev.com.czy.fragment.CoreFragment;
import clouddev.com.czy.mall.R;
import clouddev.com.czy.mall.R2;
import clouddev.com.czy.mall.converter.AddressDataConverter;
import clouddev.com.czy.network.RestfulClient;
import clouddev.com.czy.network.callback.iSuccess;
import clouddev.com.czy.ui.LoaderStyle;
import clouddev.com.czy.ui.recycler.MultipleItemEntity;
import clouddev.com.czy.util.callback.CallBackManager;
import clouddev.com.czy.util.callback.CallBackType;
import clouddev.com.czy.util.callback.iGlobalCallback;

/**
 * Created by 29737
 */

public class AddressFragment extends CoreFragment implements iSuccess
{
    @BindView(R2.id.rv_address)
    RecyclerView mRecyclerView = null;

    private  List<MultipleItemEntity> addressEntity = null;
    private AddressAdapter addressAdapter = null;

    @OnClick(R2.id.address_add)
    void onClickAdd()
    {
        start(new AddressDetailsFragment());
        CallBackManager.getInstance().addCallback(CallBackType.ADDRESS, new iGlobalCallback<MultipleItemEntity>()
        {
            @Override
            public void executeCallback(MultipleItemEntity entity)
            {
                addressEntity.add(entity);
                addressAdapter.notifyDataSetChanged();
            }
        });
    }

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
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        addressEntity = new AddressDataConverter().setJsonData(response).convert();
        addressAdapter = new AddressAdapter(addressEntity);
        mRecyclerView.setAdapter(addressAdapter);
    }

}
