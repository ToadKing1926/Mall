package clouddev.com.czy.mall.converter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

import clouddev.com.czy.mall.ui.cart.CartAdapter;
import clouddev.com.czy.ui.recycler.DataConverter;
import clouddev.com.czy.ui.recycler.MultipleFields;
import clouddev.com.czy.ui.recycler.MultipleItemEntity;

/**
 * Created by 29737
 */

public class CartDataConverter extends DataConverter
{
    public static final int CART_ITEM = 6;
    public static final int PRICE = 10;

    @Override
    public ArrayList<MultipleItemEntity> convert()
    {
        final ArrayList<MultipleItemEntity> dataList = new ArrayList<>();
        JSONArray dataArray = JSON.parseObject(getJsonData()).getJSONArray("data");
        final int size = dataArray.size();
        for(int i =0;i<size;i++)
        {
            final JSONObject data = dataArray.getJSONObject(i);
            final String thumb = data.getString("thumb");
            final String title = data.getString("title");
            final String desc = data.getString("desc");
            final int id = data.getInteger("id");
            final int count = data.getInteger("count");
            final double price = data.getDouble("price");

            final MultipleItemEntity entity = MultipleItemEntity.builder()
                                               .setField(MultipleFields.ITEM_TYPE,CART_ITEM)
                                               .setField(MultipleFields.ID,id)
                                               .setField(MultipleFields.IMAGE_URL,thumb)
                                               .setField(MultipleFields.COUNT,count)
                                               .setField(MultipleFields.NAME,title)
                                               .setField(MultipleFields.TEXT,desc)
                                               .setField(PRICE,price)
                                               .setField(CartAdapter.SELECTED,true)
                                               .setField(CartAdapter.POSITION,i)
                                               .build();
             dataList.add(entity);

        }
        return dataList;
    }
}
