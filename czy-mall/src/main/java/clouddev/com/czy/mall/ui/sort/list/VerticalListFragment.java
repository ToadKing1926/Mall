package clouddev.com.czy.mall.ui.sort.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import clouddev.com.czy.fragment.CoreFragment;
import clouddev.com.czy.mall.R;
import clouddev.com.czy.mall.R2;
import clouddev.com.czy.mall.converter.SortVerticalDataConverter;
import clouddev.com.czy.mall.ui.sort.SortFragment;
import clouddev.com.czy.network.RestfulClient;
import clouddev.com.czy.network.callback.iFailure;
import clouddev.com.czy.network.callback.iSuccess;
import clouddev.com.czy.ui.LoaderStyle;
import clouddev.com.czy.ui.recycler.MultipleItemEntity;

/**
 * Created by 29737 on 2018/3/10.
 */

public class VerticalListFragment extends CoreFragment
{
    @BindView(R2.id.vertical_list)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout()
    {
        return R.layout.sort_vertical_fragment;
    }

    private void initRecyclerView()
    {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(null);
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView)
    {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState)
    {
        super.onLazyInitView(savedInstanceState);
        initRecyclerView();
        RestfulClient.builder()
                     .url("/sort_list")
                     .params("","")
                     .loader(getContext(), LoaderStyle.BallGridBeatIndicator)
                     .success(new iSuccess()
                     {
                         @Override
                         public void onSuccess(String response)
                         {
                             final List<MultipleItemEntity> data = new SortVerticalDataConverter()
                                                                        .setJsonData(response)
                                                                        .convert();
                             final SortRecyclerAdapter adapter = new SortRecyclerAdapter(data,(SortFragment)getParentfragment());
                             mRecyclerView.setAdapter(adapter);
                         }
                     })
                     .failure(new iFailure() {
                         @Override
                         public void onFaliure() {
                             Log.d("Hola","fail!");
                         }
                     })
                     .build()
                     .get();
    }
}
