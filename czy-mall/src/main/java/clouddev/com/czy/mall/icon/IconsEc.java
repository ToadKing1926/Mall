package clouddev.com.czy.mall.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by 29737
 */

public enum IconsEc implements Icon
{
    icon_scan('\ue61e'),
    icon_alipay('\ue634')
    ;
    private char character;

    IconsEc(char character)
    {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace("_","-");
    }

    @Override
    public char character() {
        return character;
    }
}
