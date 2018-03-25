package clouddev.com.czy.ui.refresh;

/**
 * Created by 29737
 */

public final class PageBean
{
    private int mPageIndex = 0;//当前页
    private int mTotal = 0;//数据总量
    private int mPageSize = 0;//一页显示多少数据
    private int mCurCount = 0;//已经显示的数据
    private int mDelayed = 0;//加载延迟

    public int getPageIndex()
    {
        return mPageIndex;
    }

    public PageBean setPageIndex(int mPageIndex)
    {
        this.mPageIndex = mPageIndex;
        return this;
    }

    public int getTotal()
    {
        return mTotal;
    }

    public PageBean setTotal(int mTotal)
    {
        this.mTotal = mTotal;
        return this;
    }

    public int getPageSize()
    {
        return mPageSize;
    }

    public PageBean setPageSize(int mPageSize)
    {
        this.mPageSize = mPageSize;
        return this;
    }

    public int getCurCount()
    {
        return mCurCount;
    }

    public PageBean setCurCount(int mCurCount)
    {
        this.mCurCount = mCurCount;
        return this;
    }

    public int getDelayed()
    {
        return mDelayed;
    }

    public PageBean setDelayed(int mDelayed)
    {
        this.mDelayed = mDelayed;
        return this;
    }

    PageBean addIndex()
    {
        mPageIndex++;
        return this;
    }
}
