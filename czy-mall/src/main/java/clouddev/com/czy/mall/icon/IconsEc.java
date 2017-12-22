package clouddev.com.czy.mall.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by 29737 on 2017/12/22.
 */

public enum IconsEc implements Icon
{
    fo_scan('\ue61e'),
    fo_alipay('\ue634')
    ;
    private char character;

    IconsEc(char character)
    {
        this.character = character;
    }

    @Override
    public String key() {
        return null;
    }

    @Override
    public char character() {
        return 0;
    }
}
