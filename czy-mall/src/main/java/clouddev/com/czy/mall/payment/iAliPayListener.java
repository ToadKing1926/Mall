package clouddev.com.czy.mall.payment;

/**
 * Created by 29737
 */

public interface iAliPayListener
{
    void onPaySuccess();
    void onPaying();
    void onPayFail();
    void onPayCancel();
    void onPayConnectError();
}
