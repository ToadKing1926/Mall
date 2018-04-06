package clouddev.com.czy.mall.ui.mine.profile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;

import clouddev.com.czy.GlideApp;
import clouddev.com.czy.mall.R;
import clouddev.com.czy.mall.ui.mine.date.DateDialogUtil;
import clouddev.com.czy.mall.ui.mine.list.ListBean;
import clouddev.com.czy.util.callback.CallBackManager;
import clouddev.com.czy.util.callback.CallBackType;
import clouddev.com.czy.util.callback.iGlobalCallback;

/**
 * Created by 29737
 */

public class UserProfileListener extends SimpleClickListener
{
    private final UserProfileFragment FRAGMENT;
    private final String[] mGender = new String[]{"男","女","保密"};

    public  UserProfileListener(UserProfileFragment fragment)
    {
        this.FRAGMENT = fragment;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, final View view, int position)
    {
        final ListBean bean = (ListBean)baseQuickAdapter.getData().get(position);
        final int id = bean.getId();
        switch (id)
        {
            case 1:
                CallBackManager.getInstance().addCallback(CallBackType.ON_CROP, new iGlobalCallback<Uri>()
                {
                    @Override
                    public void executeCallback(Uri args)
                    {
                       final ImageView avatar = view.findViewById(R.id.arrow_avatar);
                        GlideApp.with(FRAGMENT)
                                .load(args)
                                .into(avatar);
                        //TODO:上传服务器
                        Log.d("Hola",args.toString());
                    }
                });
                FRAGMENT.startCameraWithCheck();
                break;
            case 2:
                //final CoreFragment nameFragment = bean.getFragment();
                FRAGMENT.goNameFragment();
                break;
            case 3:
                getGenderDialog(new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        final TextView textView = (TextView)view.findViewById(R.id.mine_arrow_value);
                        textView.setText(mGender[i]);
                        dialogInterface.cancel();
                    }
                });
                break;
            case 4:
                final DateDialogUtil dialogUtil = new DateDialogUtil();
                final String[] date = bean.getValue().split("-");
                dialogUtil.setDateListener(new DateDialogUtil.iDateListener()
                {
                    @Override
                    public void onDateChange(String date)
                    {
                        final TextView textView = view.findViewById(R.id.mine_arrow_value);
                        textView.setText(date);
                    }
                });
                dialogUtil.showDialog(FRAGMENT.getContext(),date);
                break;
            default:
                break;
        }
    }

    private void getGenderDialog(DialogInterface.OnClickListener listener)
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(FRAGMENT.getContext());
        builder.setSingleChoiceItems(mGender,2,listener);
        builder.show();
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
