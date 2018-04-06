package clouddev.com.czy.app;

import android.util.Log;
import android.widget.Toast;

import clouddev.com.czy.network.RestfulClient;
import clouddev.com.czy.network.callback.iError;
import clouddev.com.czy.network.callback.iFailure;
import clouddev.com.czy.network.callback.iSuccess;
import clouddev.com.czy.storage.appPreference;

/**
 * Created by 29737
 */

public class AccountManager
{
    private enum SignTag
    {
        SIGN_TAG
    }

    //保存登录状态
    public static void setSignState(boolean state)
    {
        appPreference.setAppFlag(SignTag.SIGN_TAG.name(),state);
    }

    private static boolean isSignIn()
    {
        final String token = appPreference.getToken();
        Log.d("Hola",token);
        RestfulClient.builder()
                     .url("http://192.168.1.119:8088/user/check_login.do")
                     .params("token",token)
                     .success(new iSuccess()
                     {
                         @Override
                         public void onSuccess(String response)
                         {
                             Log.d("Hola",response);
                             appPreference.setAppFlag(SignTag.SIGN_TAG.name(),true);
                         }
                     })
                     .error(new iError()
                     {
                         @Override
                         public void onError(int code, String msg)
                         {
                             appPreference.setAppFlag(SignTag.SIGN_TAG.name(),false);
                         }
                     })
                    .failure(new iFailure()
                    {
                        @Override
                        public void onFaliure()
                        {
                            appPreference.setAppFlag(SignTag.SIGN_TAG.name(),false);
                        }
                    })
                    .build();

        return appPreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    public static void checkAccount(iUserCheck check)
    {
        if(isSignIn())
        {
            check.onSignin();
        }
        else
        {
            check.onSignOut();
        }

    }
}
