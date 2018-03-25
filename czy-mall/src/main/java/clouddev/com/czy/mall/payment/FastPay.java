package clouddev.com.czy.mall.payment;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import clouddev.com.czy.fragment.CoreFragment;
import clouddev.com.czy.mall.R;

/**
 * Created by 29737
 */

public class FastPay implements View.OnClickListener
{
    private iAliPayListener mIAlipayListener = null;
    private Activity mActivity = null;
    private int mOrderID = -1;
    private AlertDialog mDialog = null;

    private FastPay(CoreFragment fragment)
    {
        this.mActivity = fragment.getFVActivity();
        this.mDialog = new AlertDialog.Builder(fragment.getContext()).create();
    }

    public static FastPay create(CoreFragment fragment)
    {
        return new FastPay(fragment);
    }

    public void beginPay()
    {
        mDialog.show();
        final Window window = mDialog.getWindow();
        if(window != null)
        {
            window.setContentView(R.layout.payment_dialog);
            window.setGravity(Gravity.BOTTOM);
            window.setWindowAnimations(R.style.anim_panel_up_from_bottom);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            final WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            window.setAttributes(params);

            window.findViewById(R.id.btn_cancel_payment).setOnClickListener(this);
            window.findViewById(R.id.tv_alipay).setOnClickListener(this);
        }
    }

    public final void aliPay(int orderID)
    {
        final String paySign = "";
        final PayAsyncTask payAsyncTask = new PayAsyncTask(mActivity,mIAlipayListener);
        payAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,paySign);

    }

    @Override
    public void onClick(View view)
    {
        int id = view.getId();
        mDialog.cancel();
        if(id == R.id.tv_alipay)
        {

        }
        else if(id == R.id.btn_cancel_payment)
        {

        }
    }
}
