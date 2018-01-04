package clouddev.com.czy.mall.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatTextView;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import clouddev.com.czy.app.AccountManager;
import clouddev.com.czy.fragment.CoreFragment;
import clouddev.com.czy.mall.R;
import clouddev.com.czy.mall.R2;
import clouddev.com.czy.mall.database.UserInfo;
import clouddev.com.czy.network.RestfulClient;
import clouddev.com.czy.network.callback.iFailure;
import clouddev.com.czy.network.callback.iSuccess;

/**
 * Created by 29737 on 2018/1/2.
 */

public class SignUpFragment extends CoreFragment
{
    @BindView(R2.id.sign_up_id)
    TextInputEditText mId = null;
    @BindView(R2.id.sign_up_password)
    TextInputEditText mPassword = null;
    @BindView(R2.id.sign_up_password_confirm)
    TextInputEditText mPasswordConfirm = null;
    @BindView(R2.id.sign_up_e_mail)
    TextInputEditText mEMail = null;
    @BindView(R2.id.sign_up_phone_number)
    TextInputEditText mPhoneNumber = null;
    @BindView(R2.id.sign_up_to_login)
    AppCompatTextView mToLogin = null;

    private iSignListener mISignListener = null;

    @OnClick(R2.id.sign_up_confirm)
    void onClickConfirm()
    {
        if(check())
        {

             RestfulClient.builder()
                         .url("sign_up")
                         .params("","")
                         .success(new iSuccess() {
                             @Override
                             public void onSuccess(String Response)
                             {
                                 UserInfo userInfo = new UserInfo();
                                 userInfo.setUsername(mId.getText().toString());
                                 userInfo.setPassword(mPassword.getText().toString());
                                 userInfo.setEmail(mEMail.getText().toString());
                                 userInfo.setPhone(mPhoneNumber.getText().toString());
                                 if(!userInfo.save())
                                 {
                                     Toast.makeText(getContext(),"缓存失败",Toast.LENGTH_LONG).show();
                                 }
                                 else
                                 {
                                     mISignListener.onSignUpSuccess();
                                 }
                             }
                         })
                         .failure(new iFailure() {
                             @Override
                             public void onFaliure()
                             {
                                 Toast.makeText(getContext(),"注册失败！",Toast.LENGTH_LONG).show();
                             }
                         })
                        .build()
                        .post();

        }
    }

    @OnClick(R2.id.sign_up_to_login)
    void onClickToRegister()
    {
        start(new SignInFragment());
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        if(activity instanceof iSignListener)
        {
            mISignListener = (iSignListener)activity;
        }
    }

    private boolean check()
   {
        final String id = mId.getText().toString();
        final String password = mPassword.getText().toString();
        final String passwordConfirm = mPasswordConfirm.getText().toString();
        final String eMail = mEMail.getText().toString();
        final String phoneNumber = mPhoneNumber.getText().toString();

        boolean isPass = true;

        if(id.isEmpty())
        {
            mId.setError("ID格式错误");
            isPass = false;
        }
        else
        {
            mId.setError(null);
        }

        if(password.isEmpty() || password.length()<6)
        {
            mPassword.setError("密码格式错误,至少输入6个字符");
            isPass = false;
        }
        else
        {
            mPassword.setError(null);
        }

        if(!passwordConfirm.equals(password))
        {
            mPasswordConfirm.setError("密码输入不一致，请仔细检查");
            isPass = false;
        }
        else
        {
            mPasswordConfirm.setError(null);
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

        if(phoneNumber.isEmpty() || phoneNumber.length() != 11)
        {
            mPhoneNumber.setError("手机号码格式错误");
            isPass = false;
        }
        else
        {
            mPhoneNumber.setError(null);
        }
        return isPass;

    }

    @Override
    public Object setLayout()
    {
        return R.layout.sign_up_fragment;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }


}
