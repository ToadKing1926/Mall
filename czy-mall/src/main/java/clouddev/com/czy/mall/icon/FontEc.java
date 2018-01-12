package clouddev.com.czy.mall.icon;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;

/**
 * Created by 29737 on 2017/12/22.
 */

public class FontEc implements IconFontDescriptor
{
    @Override
    public String ttfFileName()
    {
        return "iconfont.ttf";
    }

    @Override
    public Icon[] characters()
    {
        return IconsEc.values();
    }
}
