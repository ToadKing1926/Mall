package clouddev.com.czy.mall.ui.mine;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import clouddev.com.czy.GlideApp;
import clouddev.com.czy.app.AccountManager;
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
import clouddev.com.czy.network.RestfulClient;
import clouddev.com.czy.network.callback.iError;
import clouddev.com.czy.network.callback.iFailure;
import clouddev.com.czy.network.callback.iSuccess;
import clouddev.com.czy.storage.appPreference;
import clouddev.com.czy.util.callback.CallBackManager;
import clouddev.com.czy.util.callback.CallBackType;
import clouddev.com.czy.util.callback.iGlobalCallback;
import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by 29737
 */

public class MineFragment extends CoreFragment
{
    private Bundle mArgs = null;

    @BindView(R2.id.mine_personal_setting)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.mine_user_avatar)
    CircleImageView mCircleImageView = null;


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
        GlideApp.with(getContext())
                .load("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1524477672&di=2c444befdeb8c23ba4390ea3684167fc&src=http://img0.pconline.com.cn/pconline/1501/13/6006483_1.jpg")
                .dontAnimate()
                .centerCrop()
                .into(mCircleImageView);

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
        CallBackManager.getInstance().addCallback(CallBackType.ON_CHANGE_AVATAR, new iGlobalCallback<Uri>()
        {
            @Override
            public void executeCallback(Uri args)
            {
                GlideApp.with(getContext())
                        .load(args)
                        .into(mCircleImageView);
                //TODO:上传服务器
            }
        });
    }
}
