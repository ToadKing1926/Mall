package clouddev.com.czy.mall.product;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

/**
 * Created by 29737
 */

public class TabPagerAdapter extends FragmentStatePagerAdapter
{

    private final ArrayList<String> TAB_TITLE = new ArrayList<>();
    private final ArrayList<ArrayList<String>> PICTURE = new ArrayList<>();

    public TabPagerAdapter(FragmentManager fm, JSONObject data)
    {
        super(fm);
        final JSONArray tabs = data.getJSONArray("tabs");
        final int size = tabs.size();
        for(int i = 0;i < size;i++)
        {
            final JSONObject eachTab = tabs.getJSONObject(i);
            final String name = eachTab.getString("name");
            final JSONArray pictureUrls = eachTab.getJSONArray("pictures");
            final ArrayList<String> eachTabPicturesArray = new ArrayList<>();
            final int picSize = pictureUrls.size();
            for(int j = 0; j < picSize; j++)
            {
                eachTabPicturesArray.add(pictureUrls.getString(j));
            }
            TAB_TITLE.add(name);
            PICTURE.add(eachTabPicturesArray);
        }
    }

    @Override
    public Fragment getItem(int position)
    {
        return ImageTabFragment.create(PICTURE.get(position));
    }

    @Override
    public int getCount()
    {
        return TAB_TITLE.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position)
    {
        return TAB_TITLE.get(position);
    }
}
