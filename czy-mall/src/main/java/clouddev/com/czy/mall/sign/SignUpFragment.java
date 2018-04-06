package clouddev.com.czy.mall.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import clouddev.com.czy.app.AccountManager;
import clouddev.com.czy.fragment.CoreFragment;
import clouddev.com.czy.mall.R;
import clouddev.com.czy.mall.R2;
import clouddev.com.czy.mall.database.UserInfo;
import clouddev.com.czy.network.RestfulClient;
import clouddev.com.czy.network.callback.iError;
import clouddev.com.czy.network.callback.iFailure;
import clouddev.com.czy.network.callback.iSuccess;

/**
 * Created by 29737
 */

public class SignUpFragment extends CoreFragment
{
    @BindView(R2.id.sign_up_id)
    TextInputEditText mUsername = null;
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
        Map<String,Object> params = new HashMap<>(6);

        if(check())
        {
            mISignListener.onSignUpSuccess("123456789");
            /*params.put("username",mUsername.getText().toString());
            params.put("password",mPassword.getText().toString());
            params.put("email",mEMail.getText().toString());
            params.put("phone",mPhoneNumber.getText().toString());

             RestfulClient.builder()
                         .url("http://192.168.1.119:8088/user/register.do")
                         .params(params)
                         .success(new iSuccess()
                         {
                             @Override
                             public void onSuccess(String response)
                             {
                                 final JSONObject obj = JSON.parseObject(response);
                                 final int status = obj.getInteger("status");
                                 if(status == 1)
                                 {
                                     final String msg = obj.getString("msg");
                                 }
                                 else
                                 {
                                     UserInfo userInfo = new UserInfo();
                                     userInfo.setUsername(mUsername.getText().toString());
                                     userInfo.setPassword(mPassword.getText().toString());
                                     userInfo.setEmail(mEMail.getText().toString());
                                     userInfo.setPhone(mPhoneNumber.getText().toString());
                                     if(!userInfo.save())
                                     {
                                         Toast.makeText(getContext(),"缓存失败",Toast.LENGTH_LONG).show();
                                     }
                                     else
                                     {
                                         final String token = obj.getString("data");
                                         mISignListener.onSignInSuccess(token);
                                     }
                                 }
                             }

                         })
                         .error(new iError()
                         {
                             @Override
                             public void onError(int code, String msg)
                             {
                                 Log.d("Hola",msg);
                             }
                         })
                         .failure(new iFailure() {
                             @Override
                             public void onFaliure()
                             {
                                 Log.d("Hola","Sign Up");
                                 mISignListener.onSignUpSuccess("123456789");
                                 //Toast.makeText(getContext(),"注册失败！请检查网络",Toast.LENGTH_SHORT).show();
                             }
                         })
                        .build()
                        .post();*/

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
        final String username = mUsername.getText().toString();
        final String password = mPassword.getText().toString();
        final String passwordConfirm = mPasswordConfirm.getText().toString();
        final String eMail = mEMail.getText().toString();
        final String phoneNumber = mPhoneNumber.getText().toString();

        boolean isPass = true;

        if(username.isEmpty())
        {
            mUsername.setError("ID格式错误");
            isPass = false;
        }
        else
        {
            mUsername.setError(null);
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
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView)
    {

    }


}
