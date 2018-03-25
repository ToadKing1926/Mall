package clouddev.com.czy.mall.ui.cart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ViewStubCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.joanzapata.iconify.widget.IconTextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;
import java.util.concurrent.CancellationException;

import butterknife.BindView;
import butterknife.OnClick;
import clouddev.com.czy.app.appInit;
import clouddev.com.czy.fragment.CoreFragment;
import clouddev.com.czy.mall.R;
import clouddev.com.czy.mall.R2;
import clouddev.com.czy.mall.converter.CartDataConverter;
import clouddev.com.czy.mall.payment.FastPay;
import clouddev.com.czy.mall.ui.main.MainFragment;
import clouddev.com.czy.network.RestfulClient;
import clouddev.com.czy.network.callback.iSuccess;
import clouddev.com.czy.ui.LoaderStyle;
import clouddev.com.czy.ui.recycler.MultipleFields;
import clouddev.com.czy.ui.recycler.MultipleItemEntity;
import clouddev.mall.ExampleFragment;

/**
 * Created by 29737 on 2018/1/8.
 */

public class CartFragment extends CoreFragment
{
    private CartAdapter mAdapter = null;
    private double mPrice = 0.0;
    //数量标记
    private int mCurrentCount = 0;
    private int mTotalCount = 0;
    @BindView(R2.id.rv_cart)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.cart_select_all)
    IconTextView mIconSelectAll = null;
    @BindView(R2.id.view_stub_cart)
    ViewStubCompat mStubEmptyCart = null;
    @BindView(R2.id.cart_total_price)
    TextView mTotalPrice = null;


    @OnClick(R2.id.cart_select_all)
    void onClickSelectAll()
    {
        final int tag = (int)mIconSelectAll.getTag();
        if(tag == 0)
        {
            mIconSelectAll.setTextColor(ContextCompat.getColor(appInit.getApplication(),R.color.app_main));
            mIconSelectAll.setTag(1);//已经选择
            mAdapter.setIsSelectedAll(true);
            mAdapter.notifyItemRangeChanged(0,mAdapter.getItemCount());
            mAdapter.setTotalPrice(mPrice);
            mTotalPrice.setText("￥"+String.valueOf(mPrice));
        }
        else
        {
            mIconSelectAll.setTextColor(Color.GRAY);
            mIconSelectAll.setTag(0);//未选择
            mAdapter.setIsSelectedAll(false);
            mAdapter.notifyItemRangeChanged(0,mAdapter.getItemCount());
            mAdapter.setTotalPrice(0.0);
            mTotalPrice.setText("￥0.0");
        }
    }

    @OnClick(R2.id.cart_remove)
    void onClickRemoveItem()
    {
        Snackbar.make(getView(),"确定要删除吗？",Snackbar.LENGTH_LONG)
                .setAction("确定",new View.OnClickListener(){
                    @Override
                    public void onClick(View v)
                    {
                        final List<MultipleItemEntity> data = mAdapter.getData();
                        double totalPrice = 0.0;
                        List<MultipleItemEntity> delData = new ArrayList<>();
                        for(MultipleItemEntity entity : data)
                        {
                            if(entity.getField(CartAdapter.SELECTED))
                            {
                                delData.add(entity);
                                final int count = entity.getField(MultipleFields.COUNT);
                                final double price = entity.getField(CartDataConverter.PRICE);
                                totalPrice += count * price;
                            }
                        }
                        int size = mAdapter.getData().size();
                        for(MultipleItemEntity entity : delData)
                        {
                           mAdapter.getData().remove(entity);
                        }
                        mAdapter.notifyItemRangeChanged(0,size);
                        mPrice -= totalPrice;
                        mTotalPrice.setText("￥"+String.valueOf(mPrice));
                        checkItemCount();
                    }
                }).show();

    }

    @OnClick(R2.id.cart_clear)
    void onClickClear()
    {
        Snackbar.make(getView(),"确定要清空吗？",Snackbar.LENGTH_LONG)
                .setAction("确定",new View.OnClickListener(){
                    @Override
                    public void onClick(View v)
                    {
                        mAdapter.getData().clear();
                        mAdapter.notifyDataSetChanged();
                        checkItemCount();
                    }
                        }
                ).show();

    }

    @OnClick(R2.id.to_pay)
    void onPayment()
    {
        FastPay.create(this).beginPay();
    }


    @SuppressWarnings("RestrictedApi")
    private void checkItemCount()
    {
        final int count = mAdapter.getItemCount();
        if(count == 0)
        {
           final View stubView = mStubEmptyCart.inflate();
           final TextView tvEmptyCart = stubView.findViewById(R.id.stub_cart);
           tvEmptyCart.setText("购物车空空如也，戳我去逛逛吧！");
           tvEmptyCart.setOnClickListener(new View.OnClickListener(){
               @Override
               public void onClick(View v)
               {
                   ExampleFragment exampleFragment = (ExampleFragment)getParentFragment();
                   exampleFragment.onTabSelected(ExampleFragment.FIRST);
               }
           });
           mRecyclerView.setVisibility(View.GONE);
           mIconSelectAll.setTextColor(Color.GRAY);
           mIconSelectAll.setEnabled(false);//空购物车不能全选
            mTotalPrice.setText("￥0.0");
        }
        else
        {
            mRecyclerView.setVisibility(View.VISIBLE);
            mIconSelectAll.setEnabled(true);
        }
    }

    private void createOrder()
    {
        final String url = "";
        final WeakHashMap<String,Object> orderParams = new WeakHashMap<>();
        orderParams.put("userid","");
        orderParams.put("amount","");
        orderParams.put("comment","");
        orderParams.put("type","");
        orderParams.put("ordertype","");
        RestfulClient.builder()
                     .url(url)
                     .loader(getContext(), LoaderStyle.BallClipRotateMultipleIndicator)
                     .params(orderParams)
                     .success(new iSuccess() {
                         @Override
                         public void onSuccess(String response) {

                         }
                     })
                     .build()
                     .post();
    }

    @Override
    public Object setLayout() {
        return R.layout.cart_fragment;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
            mIconSelectAll.setTag(1);
            mIconSelectAll.setTextColor(ContextCompat.getColor(appInit.getApplication(),R.color.app_main));
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState)
    {
        super.onLazyInitView(savedInstanceState);
        RestfulClient.builder()
                     .url("/cart")
                     .params("","")
                     .success(new iSuccess()
                     {
                         @Override
                         public void onSuccess(String response)
                         {
                            final ArrayList<MultipleItemEntity> data = new CartDataConverter().setJsonData(response).convert();
                            final LinearLayoutManager manager = new LinearLayoutManager(getContext());
                            mRecyclerView.setLayoutManager(manager);
                            mAdapter = new CartAdapter(data);
                            mRecyclerView.setAdapter(mAdapter);
                            mPrice = mAdapter.getTotalPrice();
                            mTotalPrice.setText("￥"+String.valueOf(mPrice));
                            mAdapter.setCartItemListener(new iCartItemListener()
                            {
                                @Override
                                public void onItemClick(double itemTotalPrice)
                                {
                                    final double price = mAdapter.getTotalPrice();
                                    mTotalPrice.setText("￥"+String.valueOf(price));
                                }
                            });
                            checkItemCount();

                         }
                     })
                     .build()
                     .get();
    }


}
