package clouddev.com.czy.mall.converter;

import android.content.ClipData;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

import clouddev.com.czy.ui.recycler.DataConverter;
import clouddev.com.czy.ui.recycler.ItemType;
import clouddev.com.czy.ui.recycler.MultipleFields;
import clouddev.com.czy.ui.recycler.MultipleItemEntity;

/**
 * Created by 29737 on 2018/3/10.
 */

public class SortVerticalDataConverter extends DataConverter
{
    @Override
    public ArrayList<MultipleItemEntity> convert()
    {
        final JSONArray dataArray = JSON.parseObject(getJsonData())
                                         .getJSONObject("data")
                                         .getJSONArray("list");
        final int size = dataArray.size();
        for(int i = 0;i<size;i++)
        {
            final JSONObject data = dataArray.getJSONObject(i);
            final int id = data.getInteger("id");
            final String name = data.getString("name");

            final MultipleItemEntity multipleItemEntity = MultipleItemEntity.builder()
                                                           .setField(MultipleFields.ITEM_TYPE, ItemType.SORT_MENU)
                                                           .setField(MultipleFields.ID, id)
                                                           .setField(MultipleFields.TEXT,name)
                                                           .setField(MultipleFields.TAG,false)//标记是否被点击
                                                           .build();
           ENTITIES.add(multipleItemEntity);
            //默认点击第一个
            ENTITIES.get(0).setField(MultipleFields.TAG,true);
        }
        return ENTITIES;
    }
}
