package clouddev.com.czy.mall.ui.mine.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import clouddev.com.czy.fragment.CoreFragment;
import clouddev.com.czy.mall.R;
import clouddev.com.czy.mall.R2;
import clouddev.com.czy.mall.ui.mine.MineClickListener;
import clouddev.com.czy.mall.ui.mine.address.AddressFragment;
import clouddev.com.czy.mall.ui.mine.list.ListAdapter;
import clouddev.com.czy.mall.ui.mine.list.ListBean;
import clouddev.com.czy.mall.ui.mine.list.ListItemType;

/**
 * Created by 29737
 */

public class SettingsFragment extends CoreFragment
{
    @BindView(R2.id.rv_settings)
    RecyclerView mRecyclerView = null;
    @Override
    public Object setLayout()
    {
        return R.layout.settings_fragment;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView)
    {
       ListBean push = new ListBean.Builder()
                .setItemType(ListItemType.ARROW_ITEM_SWITCH)
                .setId(1)
                .setFragment(new AddressFragment())
                .setText("开启推送")
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
                {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked)
                    {
                        if(isChecked)
                        {
                            Toast.makeText(getContext(),"对不起，推送功能没有实现",Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .build();

        ListBean clear = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_BUTTON)
                .setId(2)
                .setText("清除缓存")
                .build();

        List<ListBean> data = new ArrayList<>();
        data.add(push);
        data.add(clear);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        ListAdapter adapter = new ListAdapter(data);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnItemTouchListener(new SettingListener(this.getContext()));

    }
}
