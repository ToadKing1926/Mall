package clouddev.com.czy.mall.ui.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import clouddev.com.czy.fragment.CoreFragment;
import clouddev.com.czy.mall.R;
import clouddev.com.czy.mall.R2;
import clouddev.com.czy.mall.ui.mine.about.OpenSourceFragment;
import clouddev.com.czy.mall.ui.mine.address.AddressFragment;
import clouddev.com.czy.mall.ui.mine.list.ListAdapter;
import clouddev.com.czy.mall.ui.mine.list.ListBean;
import clouddev.com.czy.mall.ui.mine.list.ListItemType;
import clouddev.com.czy.mall.ui.mine.order.OrderItemFragment;
import clouddev.com.czy.mall.ui.mine.profile.UserProfileFragment;
import clouddev.com.czy.mall.ui.mine.settings.SettingsFragment;


/**
 * Created by 29737
 */

public class MineFragment extends CoreFragment
{
    private Bundle mArgs = null;

    @BindView(R2.id.mine_personal_setting)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout()
    {
        return R.layout.mine_fragment;
    }

    @OnClick(R2.id.mine_all_order)
    void onClickAllOrder()
    {
        mArgs.putString("TYPE","ALL");
        startOrderFragment();
    }

    @OnClick(R2.id.mine_receive)
    void onClickReceive()
    {
        mArgs.putString("TYPE","RECEIVE");
        startOrderFragment();
    }

    @OnClick(R2.id.mine_pay)
    void onClickPay()
    {
        mArgs.putString("TYPE","PAY");
        startOrderFragment();
    }

    @OnClick(R2.id.mine_rate)
    void onClickRate()
    {
        mArgs.putString("TYPE","RATE");
        startOrderFragment();
    }

    @OnClick(R2.id.mine_after_sale)
    void onClickAfterSale()
    {
        mArgs.putString("TYPE","AFTER_SALE");
        startOrderFragment();
    }

    @OnClick(R2.id.mine_user_avatar)
    void onClickAvatar()
    {
        getParentfragment().getSupportDelegate().start(new UserProfileFragment());
    }

    private void startOrderFragment()
    {
        final OrderItemFragment fragment = new OrderItemFragment();
        fragment.setArguments(mArgs);
        getParentfragment().getSupportDelegate().start(fragment);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mArgs = new Bundle();
    }


    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView)
    {
        ListBean address = new ListBean.Builder()
                .setItemType(ListItemType.ARROW_ITEM_LAYOUT)
                .setId(1)
                .setFragment(new AddressFragment())
                .setText("收货地址")
                .build();

        ListBean settings = new ListBean.Builder()
                                     .setItemType(ListItemType.ARROW_ITEM_LAYOUT)
                                     .setId(2)
                                     .setText("系统设置")
                                     .setFragment(new SettingsFragment())
                                     .build();

        ListBean about = new ListBean.Builder()
                .setItemType(ListItemType.ARROW_ITEM_LAYOUT)
                .setId(3)
                .setText("关于")
                .setFragment(new OpenSourceFragment())
                .build();

        final List<ListBean> data = new ArrayList<>();
        data.add(address);
        data.add(settings);
        data.add(about);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        final ListAdapter adapter = new ListAdapter(data);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnItemTouchListener(new MineClickListener(this));
    }
}
