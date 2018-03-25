package clouddev.com.czy.mall.ui.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import clouddev.com.czy.fragment.CoreFragment;
import clouddev.com.czy.mall.R;
import clouddev.com.czy.mall.R2;
import clouddev.com.czy.mall.ui.mine.list.ListAdapter;
import clouddev.com.czy.mall.ui.mine.list.ListBean;
import clouddev.com.czy.mall.ui.mine.list.ListItemType;


/**
 * Created by 29737 on 2018/1/8.
 */

public class MineFragment extends CoreFragment
{
    @BindView(R2.id.mine_personal_setting)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout()
    {
        return R.layout.mine_fragment;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView)
    {
        ListBean push = new ListBean.Builder()
                                     .setItemType(ListItemType.ARROW_ITEM_LAYOUT)
                                     .setId(1)
                                     .setText("收货地址")
                                     .build();

        ListBean system = new ListBean.Builder()
                .setItemType(ListItemType.ARROW_ITEM_LAYOUT)
                .setId(2)
                .setText("系统设置")
                .build();

        final List<ListBean> data = new ArrayList<>();
        data.add(push);
        data.add(system);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(new ListAdapter(data));
    }
}
