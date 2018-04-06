package clouddev.com.czy.mall.ui.mine.settings;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import clouddev.com.czy.mall.ui.mine.list.ListBean;
import clouddev.com.czy.storage.appPreference;


/**
 * Created by 29737
 */

public class SettingListener extends SimpleClickListener
{
    private Context mContext;

    public SettingListener(Context mContext)
    {
        this.mContext = mContext;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position)
    {
        final ListBean bean = (ListBean)baseQuickAdapter.getData().get(position);
        final int id = bean.getId();
        switch (id)
        {
            case 1:
                break;
            case 2:
                Snackbar.make(view,"确定要清除缓存吗？",Snackbar.LENGTH_LONG).setAction("确定", new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        appPreference.clearAppProfile();
                        Toast.makeText(mContext,"清除缓存成功！",Toast.LENGTH_SHORT).show();
                    }
                }).show();
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position)
    {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position)
    {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position)
    {

    }
}
