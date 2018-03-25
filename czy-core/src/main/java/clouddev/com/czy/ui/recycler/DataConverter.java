package clouddev.com.czy.ui.recycler;

import java.util.ArrayList;

/**
 * Created by 29737
 */

public abstract class DataConverter
{
    protected final ArrayList<MultipleItemEntity> ENTITIES = new ArrayList<>();
    private String mJsonData = null;

    public abstract ArrayList<MultipleItemEntity> convert();

    public DataConverter setJsonData(String data)
    {
        this.mJsonData = data;
        return this;
    }

    protected String getJsonData()
    {
        if(mJsonData == null || mJsonData.isEmpty())
        {
            throw new RuntimeException("数据为空！");
        }
        return mJsonData;
    }
}
