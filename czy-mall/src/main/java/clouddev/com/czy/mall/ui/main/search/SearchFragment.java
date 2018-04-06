package clouddev.com.czy.mall.ui.main.search;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.StringUtils;
import com.choices.divider.Divider;
import com.choices.divider.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import clouddev.com.czy.fragment.CoreFragment;
import clouddev.com.czy.mall.R;
import clouddev.com.czy.mall.R2;
import clouddev.com.czy.mall.converter.SearchDataConverter;
import clouddev.com.czy.storage.appPreference;
import clouddev.com.czy.ui.recycler.DividerLookupImpl;
import clouddev.com.czy.ui.recycler.MultipleItemEntity;
import clouddev.com.czy.util.callback.CallBackManager;
import clouddev.com.czy.util.callback.CallBackType;
import clouddev.com.czy.util.callback.iGlobalCallback;

/**
 * Created by 29737
 */

public class SearchFragment extends CoreFragment
{
    @BindView(R2.id.rv_search)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.et_search_view)
    EditText mEditText = null;

    private boolean isSearched = false;


    @OnClick(R2.id.tv_top_search)
    void onClickSearch()
    {
        //TODO:请求服务器
        final String searchItemText = mEditText.getText().toString();
        if(!isSearched)
        {
            saveItem(searchItemText);
        }
        isSearched = false;
        getSupportDelegate().pop();
    }

    @OnClick(R2.id.icon_top_search_back)
    void onClickBack()
    {
        getSupportDelegate().pop();
    }

    private void saveItem(String item)
    {
        if(!StringUtils.isEmpty(item) && !StringUtils.isSpace(item))
        {
            List<String> history;
            final String historyItem = appPreference.getCustomAppProfile("search_history");
            if(StringUtils.isEmpty(historyItem))
            {
                history = new ArrayList<>();
            }
            else
            {
                history = JSON.parseObject(historyItem,ArrayList.class);
            }
            history.add(item);
            final String json = JSON.toJSONString(history);
            appPreference.addCustomAppProfile("search_history",json);
        }
    }
    @Override
    public Object setLayout()
    {
        return R.layout.search_fragment;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView)
    {
         final LinearLayoutManager manager = new LinearLayoutManager(getContext());
         mRecyclerView.setLayoutManager(manager);

        final List<MultipleItemEntity> data = new SearchDataConverter().convert();
        final SearchAdapter adapter = new SearchAdapter(data);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnItemTouchListener(new SearchClickListener());

        CallBackManager.getInstance().addCallback(CallBackType.SEARCH, new iGlobalCallback<String>()
        {
            @Override
            public void executeCallback(String args)
            {
                mEditText.setText(args);
                mRecyclerView.setVisibility(View.INVISIBLE);
                isSearched = true;
            }
        });

        final DividerItemDecoration itemDecoration = new DividerItemDecoration();
        itemDecoration.setDividerLookup(new DividerItemDecoration.DividerLookup()
        {
            @Override
            public Divider getVerticalDivider(int position)
            {
                return null;
            }

            @Override
            public Divider getHorizontalDivider(int position)
            {
                return new Divider.Builder()
                                    .size(2)
                                    .margin(20,20)
                                    .color(Color.GRAY)
                                    .build();
            }
        });
          mRecyclerView.addItemDecoration(itemDecoration);

    }
}
