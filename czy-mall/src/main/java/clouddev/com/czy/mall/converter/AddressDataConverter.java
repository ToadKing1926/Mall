package clouddev.com.czy.mall.converter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

import clouddev.com.czy.ui.recycler.DataConverter;
import clouddev.com.czy.ui.recycler.ItemType;
import clouddev.com.czy.ui.recycler.MultipleFields;
import clouddev.com.czy.ui.recycler.MultipleItemEntity;

/**
 * Created by 29737
 */

public class AddressDataConverter extends DataConverter
{
    public static final int ADDRESS_ITEM_TYPE = 40;

    @Override
    public ArrayList<MultipleItemEntity> convert()
    {
        final JSONArray array = JSON.parseObject(getJsonData()).getJSONArray("data");
        final int size = array.size();
        for(int i = 0; i < size; i++)
        {
            final JSONObject data = array.getJSONObject(i);
            final int id = data.getInteger("id");
            final String name = data.getString("name");
            final String phone = data.getString("phone");
            final String address = data.getString("address");
            final boolean isDefault = data.getBoolean("default");

            final MultipleItemEntity entity = MultipleItemEntity.builder()
                                               .setItemType(ADDRESS_ITEM_TYPE)
                                               .setField(MultipleFields.ID,id)
                                               .setField(MultipleFields.NAME,name)
                                               .setField(MultipleFields.TAG,isDefault)
                                               .setField(MultipleFields.PHONE,phone)
                                               .setField(MultipleFields.TEXT,address)
                                               .build();
            ENTITIES.add(entity);
        }
        return ENTITIES;
    }
}
