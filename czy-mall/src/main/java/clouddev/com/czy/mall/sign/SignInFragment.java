package clouddev.com.czy.mall.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import clouddev.com.czy.fragment.CoreFragment;
import clouddev.com.czy.mall.R;
import clouddev.com.czy.mall.R2;
import clouddev.com.czy.mall.database.UserInfo;
import clouddev.com.czy.network.RestfulClient;
import clouddev.com.czy.network.callback.iSuccess;

/**
 * Created by 29737 on 2018/1/2.
 */

public class SignInFragment extends CoreFragment
{
    @BindView(R2.id.sign_in_e_mail)
    TextInputEditText mEMail = null;
    @BindView(R2.id.sign_in_password)
    TextInputEditText mPassword = null;

    private iSignListener mISignListener = null;

    @OnClick(R2.id.sign_in_confirm)
    void onClickConfirm()
    {
       if(check())
       {

            RestfulClient.builder()
                         .url("sign_in")
                         .params("","")
                         .success(new iSuccess()
                         {
                             @Override
                             public void onSuccess(String Response)
                             {
                                 mISignListener.onSignInSuccess();
                             }
                         })
                        .build()
                        .post();
       }
    }

    @OnClick(R2.id.sign_in_icon)
    void onClickWeChat()
    {

    }

    @OnClick(R2.id.sign_in_to_register)
    void onClickToRegister()
    {
        start(new SignUpFragment());
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        if(activity instanceof iSignListener)
        {
            Toast.makeText(getContext(),"sign in",Toast.LENGTH_LONG).show();
            mISignListener = (iSignListener)activity;
        }
    }

    private boolean check()
    {

        final String password = mPassword.getText().toString();
        final String eMail = mEMail.getText().toString();

        boolean isPass = true;



        if(password.isEmpty() || password.length()<6)
        {
            mPassword.setError("密码格式错误,至少输入6个字符");
            isPass = false;
        }
        else
        {
            mPassword.setError(null);
        }

        if(eMail.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(eMail).matches())
        {
            mEMail.setError("邮箱格式错误");
            isPass = false;
        }
        else
        {
            mEMail.setError(null);
        }
        return isPass;
    }

    @Override
    public Object setLayout() {
        return R.layout.sign_in_fragment;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView)
    {

    }
}
