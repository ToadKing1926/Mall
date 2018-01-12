package clouddev.com.czy.mall.converter;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import clouddev.com.czy.mall.bean.MainPageJson;
import clouddev.com.czy.ui.recycler.DataConverter;
import clouddev.com.czy.ui.recycler.ItemType;
import clouddev.com.czy.ui.recycler.MultipleFields;
import clouddev.com.czy.ui.recycler.MultipleItemEntity;

/**
 * Created by 29737 on 2018/1/11.
 */

public class MainDataConverter extends DataConverter
{
    private final Gson gson =new Gson();

    @Override
    public ArrayList<MultipleItemEntity> convert()
    {
        final String jsonData = getJsonData();
        final MainPageJson mainPageJson = gson.fromJson(jsonData,MainPageJson.class);
        final ArrayList<MainPageJson.MainPageData> data = mainPageJson.getData();
        final int size = data.size();
        for(int i=0;i<size;i++)
        {
            final MainPageJson.MainPageData mainPageData = data.get(i);
            final String imageUrl = mainPageData.getImageUrl();
            final int spanSize = mainPageData.getSpanSize();
            final String text = mainPageData.getText();
            final int id = mainPageData.getGoodsId();
            final ArrayList<String> banners = mainPageData.getBanners();
            final ArrayList<String> bannerImages = new ArrayList<>();
            int type = 0;
            if(imageUrl == null && text != null)
            {
                type = ItemType.TEXT;
            }
            else if(imageUrl !=null && text == null)
            {
                type = ItemType.IMAGE;
            }
            else if(imageUrl !=null)
            {
                type = ItemType.IMAGE_TEXT;
            }
            else if(banners != null)
            {
                type = ItemType.BANNER;
                final int bannerSize = banners.size();
                for(int j=0;j<size;j++)
                {
                    final String banner = banners.get(j);
                    bannerImages.add(banner);
                }
            }
            final MultipleItemEntity entity = MultipleItemEntity.builder()
                                                                 .setField(MultipleFields.ITEM_TYPE,type)
                                                                 .setField(MultipleFields.SPAN_SIZE,spanSize)
                                                                 .setField(MultipleFields.ID,id)
                                                                 .setField(MultipleFields.TEXT,text)
                                                                 .setField(MultipleFields.IMAGE_URL,imageUrl)
                                                                 .setField(MultipleFields.BANNERS,bannerImages)
                                                                 .build();

            ENTITIES.add(entity);
        }
        return ENTITIES;
    }
}
