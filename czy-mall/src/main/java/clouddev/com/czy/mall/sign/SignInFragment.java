package clouddev.com.czy.mall.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.litepal.crud.DataSupport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
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

public class SignInFragment extends CoreFragment
{
    @BindView(R2.id.sign_in_username)
    TextInputEditText mUsername = null;
    @BindView(R2.id.sign_in_password)
    TextInputEditText mPassword = null;

    private iSignListener mISignListener = null;

    @OnClick(R2.id.sign_in_confirm)
    void onClickConfirm()
    {
        Map<String,Object> params = new HashMap<>();
       params.put("username",mUsername.getText().toString());
       params.put("password",mPassword.getText().toString());
       if(check())
       {

            RestfulClient.builder()
                         .url("http://test.happymmall.com/user/login.do")
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
                                     Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
                                 }
                                 else
                                 {
                                     mISignListener.onSignInSuccess();
                                 }
                             }
                         })
                        .failure(new iFailure()
                        {
                            @Override
                            public void onFaliure()
                            {
                                Toast.makeText(getContext(),"登录失败！请检查网络",Toast.LENGTH_SHORT).show();
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
            mISignListener = (iSignListener)activity;
        }
    }

    private boolean check()
    {

        final String password = mPassword.getText().toString();
        final String username = mUsername.getText().toString();

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

        if(username.isEmpty())
        {
            mUsername.setError("邮箱格式错误");
            isPass = false;
        }
        else
        {
            mUsername.setError(null);
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
