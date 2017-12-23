package clouddev.mall;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.util.WeakHashMap;

import butterknife.BindView;
import clouddev.com.czy.fragment.CoreFragment;
import clouddev.com.czy.network.RestfulClient;
import clouddev.com.czy.network.callback.iError;
import clouddev.com.czy.network.callback.iFailure;
import clouddev.com.czy.network.callback.iSuccess;
import clouddev.com.czy.ui.LoaderStyle;

/**
 * Created by 29737 on 2017/12/23.
 */

public class ExampleFragment extends CoreFragment
{

    @Override
    public Object setLayout()
    {

        return R.layout.fragment_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView)
    {
        TestRestfulClient();
    }

    public void TestRestfulClient()
    {

        RestfulClient.builder()
                     .url("http://127.0.0.1/index")
                     .params("","")
                     .loader(getContext(), LoaderStyle.BallGridBeatIndicator)
                     .success(new iSuccess() {
                         @Override
                         public void onSuccess(String Response)
                         {
                             Toast.makeText(getContext(),Response,Toast.LENGTH_LONG).show();
                         }
                     })
                     .error(new iError() {
                         @Override
                         public void onError(int code, String msg)
                         {
                             Toast.makeText(getContext(),code,Toast.LENGTH_LONG).show();
                         }
                     })
                     .failure(new iFailure() {
                         @Override
                         public void onFaliure() {
                             Toast.makeText(getContext(),"Fail!",Toast.LENGTH_LONG).show();
                         }
                     })
                     .build()
                     .get();

    }
}
