package clouddev.com.czy.mall.bean;

import java.util.ArrayList;

/**
 * Created by 29737 on 2018/1/12.
 */

public class MainPageJson
{
    private String code;
    private String message;
    private int total;
    private int page_size;
    private ArrayList<MainPageData> data;

    public static class MainPageData
    {
        private int goodsId;
        private String text;
        private int spanSize;
        private String imageUrl;
        private ArrayList<String> banners;

        public int getGoodsId()
        {
            return goodsId;
        }

        public void setGoodsId(int goodsId)
        {
            this.goodsId = goodsId;
        }

        public String getText()
        {
            return text;
        }

        public void setText(String text)
        {
            this.text = text;
        }

        public int getSpanSize()
        {
            return spanSize;
        }

        public void setSpanSize(int spanSize)
        {
            this.spanSize = spanSize;
        }

        public String getImageUrl()
        {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl)
        {
            this.imageUrl = imageUrl;
        }

        public ArrayList<String> getBanners()
        {
            return banners;
        }

        public void setBanners(ArrayList<String> banners) {
            this.banners = banners;
        }
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public int getTotal()
    {
        return total;
    }

    public void setTotal(int total)
    {
        this.total = total;
    }

    public int getPage_size()
    {
        return page_size;
    }

    public void setPage_size(int page_size)
    {
        this.page_size = page_size;
    }

    public ArrayList<MainPageData> getData()
    {
        return data;
    }

    public void setData(ArrayList<MainPageData> data)
    {
        this.data = data;
    }
}
