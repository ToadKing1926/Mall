package clouddev.com.czy.mall.converter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

import clouddev.com.czy.mall.bean.SectionContentItemBean;
import clouddev.com.czy.mall.bean.SortContentBean;
import clouddev.com.czy.ui.recycler.DataConverter;

/**
 * Created by 29737
 */

public class SortContentDataConverter
{
    public final List<SortContentBean> convert(String json)
    {
        final List<SortContentBean> dataList = new ArrayList<>();
        final JSONArray array = JSON.parseObject(json).getJSONArray("data");

        final int size = array.size();
        for(int i =0;i<size;i++)
        {
            final JSONObject data = array.getJSONObject(i);
            final int id = data.getInteger("id");
            final String title = data.getString("section");

            //添加标题
            final SortContentBean titleBean = new SortContentBean(true,title);
            titleBean.setId(id);
            titleBean.setMore(true);
            dataList.add(titleBean);

            final JSONArray products = data.getJSONArray("products");
            final int length = products.size();
            for(int j = 0;j<length;j++)
            {
                final JSONObject contentItem = products.getJSONObject(j);
                final int productID = contentItem.getInteger("product_id");
                final String productThumb = contentItem.getString("product_thumb");
                final String productName = contentItem.getString("product_name");

                final SectionContentItemBean sectionContentItemBean = new SectionContentItemBean();
                sectionContentItemBean.setId(productID);
                sectionContentItemBean.setThumb(productThumb);
                sectionContentItemBean.setProductName(productName);


                dataList.add(new SortContentBean(sectionContentItemBean));
            }
        }
        return dataList;
    }
}
