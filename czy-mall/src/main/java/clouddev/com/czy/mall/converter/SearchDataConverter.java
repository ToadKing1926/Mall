package clouddev.com.czy.mall.converter;

import com.alibaba.fastjson.JSONArray;

import java.util.ArrayList;

import clouddev.com.czy.storage.appPreference;
import clouddev.com.czy.ui.recycler.DataConverter;
import clouddev.com.czy.ui.recycler.MultipleFields;
import clouddev.com.czy.ui.recycler.MultipleItemEntity;

/**
 * Created by 29737
 */

public class SearchDataConverter extends DataConverter
{
    public static final int ITEM_SEARCH = 50;

    @Override
    public ArrayList<MultipleItemEntity> convert()
    {
        final String jsonString = appPreference.getCustomAppProfile("search_history");
        if(!jsonString.equals(""))
        {
            final JSONArray array = JSONArray.parseArray(jsonString);
            final int size = array.size();
            for(int i = 0;i < size;i++)
            {
                final String itemText = array.getString(i);
                final MultipleItemEntity entity = MultipleItemEntity.builder()
                                         .setItemType(ITEM_SEARCH)
                                         .setField(MultipleFields.TEXT,itemText)
                                         .build();
                ENTITIES.add(entity);
            }

        }
        return ENTITIES;
    }
}
