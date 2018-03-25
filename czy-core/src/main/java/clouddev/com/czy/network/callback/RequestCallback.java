package clouddev.com.czy.network.callback;

import android.os.Handler;
import android.util.Log;

import clouddev.com.czy.ui.LoaderStyle;
import clouddev.com.czy.ui.czyLoader;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 29737
 */

public class RequestCallback implements Callback<String>
{
    private final iRequest REQUEST;
    private final iSuccess SUCCESS;
    private final iError ERROR;
    private final iFailure FAILURE;
    private final LoaderStyle LOADER_STYLE;
    private static final Handler HANDLER = new Handler();

    public RequestCallback(iRequest REQUEST, iSuccess SUCCESS, iError ERROR, iFailure FAILURE,LoaderStyle LOADER_STYLE)
    {
        this.REQUEST = REQUEST;
        this.SUCCESS = SUCCESS;
        this.ERROR = ERROR;
        this.FAILURE = FAILURE;
        this.LOADER_STYLE=LOADER_STYLE;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response)
    {
        if(response.isSuccessful())
        {
            if(call.isExecuted())
            {

                if(SUCCESS!=null)
                {
                    SUCCESS.onSuccess(response.body());
                }
            }
        }
        else
        {
            if(ERROR!=null)
                ERROR.onError(response.code(),response.message());
        }
        if(LOADER_STYLE!=null)
        {
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run()
                {
                    czyLoader.stopLoading();
                }
            },1000);
        }

    }

    @Override
    public void onFailure(Call<String> call, Throwable t)
    {
       if(FAILURE!=null)
           FAILURE.onFaliure();
       if(REQUEST!=null)
           REQUEST.onRequestEnd();
       t.printStackTrace();
       stopLoading();
    }

    private void stopLoading()
    {
        if(LOADER_STYLE!=null)
        {
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run()
                {
                    czyLoader.stopLoading();
                }
            },1000);
        }
    }
}
