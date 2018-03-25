package clouddev.com.czy.mall.converter;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.ArrayList;

import clouddev.com.czy.ui.recycler.DataConverter;
import clouddev.com.czy.ui.recycler.MultipleFields;
import clouddev.com.czy.ui.recycler.MultipleItemEntity;
import clouddev.com.czy.ui.recycler.MultipleItemEntityBuilder;

/**
 * Created by 29737 on 2018/3/26.
 */

public class OrderListDataConverter extends DataConverter
{
    public static final int ITEM_ORDER_LIST = 30;
    @Override
    public ArrayList<MultipleItemEntity> convert()
    {
        final JSONArray array = JSON.parseObject(getJsonData()).getJSONArray("data");
        final int size = array.size();
        for (int i = 0; i<size;i++)
        {
            final JSONObject data = array.getJSONObject(i);
            final String thumb = data.getString("thumb");
            final String title = data.getString("title");
            final int id = data.getInteger("id");
            final double price = data.getInteger("price");
            final String time = data.getString("time");

            final MultipleItemEntity entity = MultipleItemEntity.builder()
                                                              .setItemType(ITEM_ORDER_LIST)
                                                              .setField(MultipleFields.ID,id)
                                                              .setField(MultipleFields.IMAGE_URL,thumb)
                                                              .setField(MultipleFields.TITLE,title)
                                                              .setField(CartDataConverter.PRICE,price)
                                                              .setField(MultipleFields.TIME,time)
                                                              .build();
             ENTITIES.add(entity);
        }
        return ENTITIES;
    }
}
