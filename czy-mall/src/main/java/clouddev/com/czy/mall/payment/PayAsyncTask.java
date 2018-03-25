package clouddev.com.czy.mall.payment;

import android.app.Activity;
import android.os.AsyncTask;

import com.alipay.sdk.app.PayTask;

import java.util.Map;

import clouddev.com.czy.ui.czyLoader;

/**
 * Created by 29737 on 2018/3/24.
 */

public class PayAsyncTask extends AsyncTask<String,Void,Map<String,String>>
{
    private final Activity ACTIVITY;
    private final iAliPayListener LISTENER;

    private static final String ALI_PAY_STATUS_SUCCESS = "9000";
    private static final String ALI_PAY_STATUS_PAYING = "8000";
    private static final String ALI_PAY_STATUS_FAIL = "4000";
    private static final String ALI_PAY_STATUS_CANCEL = "6001";
    private static final String ALI_PAY_STATUS_CONNECT_ERROR = "6002";

    public PayAsyncTask(Activity activity,iAliPayListener listener)
    {
        this.ACTIVITY = activity;
        this.LISTENER = listener;
    }

    @Override
    protected Map<String,String> doInBackground(String... params)
    {
        final String orderInfo = params[0];
        final PayTask payTask = new PayTask(ACTIVITY);
        return payTask.payV2(orderInfo,true);
    }

    @Override
    protected void onPostExecute(Map<String,String> result)
    {
        super.onPostExecute(result);
        czyLoader.stopLoading();

        final String resultInfo = result.get("resultInfo");
        final String resultStatus = result.get("resultStatus");

        switch (resultStatus)
        {
            case ALI_PAY_STATUS_SUCCESS:
                if(LISTENER != null)
                {
                    LISTENER.onPaySuccess();
                }
                break;
            case ALI_PAY_STATUS_FAIL:
                if(LISTENER != null)
                {
                    LISTENER.onPayFail();
                }
                break;
            case ALI_PAY_STATUS_PAYING:
                if(LISTENER != null)
                {
                    LISTENER.onPaying();
                }
                break;
            case ALI_PAY_STATUS_CANCEL:
                if(LISTENER != null)
                {
                    LISTENER.onPayCancel();
                }
                break;
            case ALI_PAY_STATUS_CONNECT_ERROR:
                if(LISTENER != null)
                {
                    LISTENER.onPayConnectError();
                }
                break;
        }
    }
}
