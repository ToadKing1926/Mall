package clouddev.com.czy.mall.ui.mine.date;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by 29737
 */

public class DateDialogUtil
{
    public interface iDateListener
    {
        void onDateChange(String date);
    }

    private iDateListener iDateListener = null;

    public void setDateListener(iDateListener dateListener)
    {
        this.iDateListener = dateListener;
    }

    public void showDialog(Context context,String[] date)
    {
        final LinearLayout linearLayout = new LinearLayout(context);
        final DatePicker datePicker = new DatePicker(context);
        final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        final int year = Integer.parseInt(date[0]);
        final int month = Integer.parseInt(date[1]);
        final int day = Integer.parseInt(date[2]);

        datePicker.setLayoutParams(layoutParams);
        datePicker.init(year,month, day, new DatePicker.OnDateChangedListener()
        {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int day)
            {
                final Calendar calendar = Calendar.getInstance();
                calendar.set(year,month,day);
                final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                final String data = format.format(calendar.getTime());
                if(iDateListener != null)
                {
                    iDateListener.onDateChange(data);
                }
            }
        });
        linearLayout.addView(datePicker);
        new AlertDialog.Builder(context)
                        .setTitle("选择日期")
                        .setView(linearLayout)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {

                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {

                            }
                        })
                        .show();
    }

}
