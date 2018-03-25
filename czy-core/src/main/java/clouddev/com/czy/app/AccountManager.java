package clouddev.com.czy.app;

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
