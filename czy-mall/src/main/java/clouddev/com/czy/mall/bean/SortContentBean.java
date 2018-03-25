package clouddev.com.czy.mall.bean;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by 29737
 */

public class SortContentBean extends SectionEntity<SectionContentItemBean>
{

    private boolean isMore = false;
    private int id = -1;

    public SortContentBean(boolean isHeader, String header)
    {
        super(isHeader, header);
    }

    public SortContentBean(SectionContentItemBean sectionContentItemBean)
    {
        super(sectionContentItemBean);
    }

    public boolean isMore()
    {
        return isMore;
    }

    public void setMore(boolean more)
    {
        isMore = more;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
}
