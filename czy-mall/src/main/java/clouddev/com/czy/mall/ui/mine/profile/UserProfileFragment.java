package clouddev.com.czy.mall.ui.mine.profile;

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
import clouddev.com.czy.mall.ui.mine.settings.NameFragment;

/**
 * Created by 29737
 */

public class UserProfileFragment extends CoreFragment
{
    private static final int REQ_CODE_NAME = 10086;
    private ListAdapter adapter;
    private List<ListBean> data = new ArrayList<>();


    @BindView(R2.id.user_profile)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout()
    {
        return R.layout.user_profile_fragment;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView)
    {
        ListBean image = new ListBean.Builder()
                .setItemType(ListItemType.ARROW_ITEM_AVATAR)
                .setId(1)
                .setImageUrl("http://i9.qhimg.com/t017d891ca365ef60b5.jpg")
                .build();

        ListBean name = new ListBean.Builder()
                .setItemType(ListItemType.ARROW_ITEM_LAYOUT)
                .setId(2)
                .setText("姓名:")
                .setValue("金正恩")
                .build();

        ListBean gender = new ListBean.Builder()
                .setItemType(ListItemType.ARROW_ITEM_LAYOUT)
                .setId(3)
                .setText("性别:")
                .setValue("男")
                .build();

        ListBean birth = new ListBean.Builder()
                .setItemType(ListItemType.ARROW_ITEM_LAYOUT)
                .setId(4)
                .setText("生日:")
                .setValue("1982-01-04")
                .build();


        data.add(image);
        data.add(name);
        data.add(gender);
        data.add(birth);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        adapter = new ListAdapter(data);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnItemTouchListener(new UserProfileListener(this));
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle datas)
    {
        super.onFragmentResult(requestCode, resultCode, datas);
        if(requestCode == REQ_CODE_NAME && resultCode == RESULT_OK)
        {
             final String name = datas.getString("name");
             ListBean newNameBean =  new ListBean.Builder()
                .setItemType(ListItemType.ARROW_ITEM_LAYOUT)
                .setId(2)
                .setText("姓名:")
                .setValue(name)
                .build();
             data.set(1,newNameBean);
             adapter.notifyItemChanged(1);
        }
    }

    public void goNameFragment()
    {
        startForResult(new NameFragment(),REQ_CODE_NAME);
    }
}
