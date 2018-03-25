package clouddev.com.czy.ui.recycler;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.LinkedHashMap;
import java.util.WeakHashMap;

/**
 * Created by 29737
 */

public class MultipleItemEntityBuilder
{
    private static final LinkedHashMap<Object,Object> FIELDS = new LinkedHashMap<>();

    public MultipleItemEntityBuilder()
    {
        FIELDS.clear();
    }

    public final MultipleItemEntityBuilder setItemType(int itemType)
    {
        FIELDS.put(MultipleFields.ITEM_TYPE,itemType);
        return this;
    }

    public final MultipleItemEntityBuilder setField(Object key,Object value)
    {
        FIELDS.put(key,value);
        return this;
    }

    public final MultipleItemEntityBuilder setFields(WeakHashMap<?,?> map)
    {
        FIELDS.putAll(map);
        return this;
    }

    public final MultipleItemEntity build()
    {
        return new MultipleItemEntity(FIELDS);
    }
}
