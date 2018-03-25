package clouddev.com.czy.ui;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

import clouddev.com.czy.R;
import clouddev.com.czy.util.DiemnUtil;

/**
 * Created by 29737
 */

public class czyLoader
{
    private static final int LOADER_SIZE_SCALE = 8;
    private static final ArrayList<AppCompatDialog> LOADRES = new ArrayList<>();

    public static void showLoading(Context context,String type)
    {
        final AppCompatDialog dialog =new AppCompatDialog(context, R.style.dialog);
        final AVLoadingIndicatorView avLoadingIndicatorView = LoadCreator.create(type,context);
        dialog.setContentView(avLoadingIndicatorView);

        int deviceWidth= DiemnUtil.getScreenWidth();
        int deviceHeight=DiemnUtil.getScreenHeight();

        final Window dialogWindow = dialog.getWindow();

        if(dialogWindow!=null)
        {
            WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();
            layoutParams.width = deviceWidth/LOADER_SIZE_SCALE;
            layoutParams.height=deviceHeight/LOADER_SIZE_SCALE;
            layoutParams.gravity = Gravity.CENTER;
        }
        LOADRES.add(dialog);
        dialog.show();
    }

    public static void stopLoading()
    {
        for(AppCompatDialog dialog:LOADRES)
        {
            if(dialog!=null)
            {
                if(dialog.isShowing())
                {
                    dialog.cancel();
                }
            }
        }
    }
}
