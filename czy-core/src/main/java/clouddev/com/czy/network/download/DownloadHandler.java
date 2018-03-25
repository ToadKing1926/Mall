package clouddev.com.czy.network.download;

import android.content.Context;
import android.os.AsyncTask;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import clouddev.com.czy.network.RestfulCreater;
import clouddev.com.czy.network.callback.iError;
import clouddev.com.czy.network.callback.iFailure;
import clouddev.com.czy.network.callback.iRequest;
import clouddev.com.czy.network.callback.iSuccess;
import clouddev.com.czy.ui.LoaderStyle;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 29737
 */

public class DownloadHandler
{
    private final String URL;
    private static final WeakHashMap<String,Object> PARAMS = RestfulCreater.getParams();
    private final iRequest REQUEST;
    private final iSuccess SUCCESS;
    private final iError ERROR;
    private final iFailure FAILURE;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;

    public DownloadHandler(String URL,iRequest REQUEST, iSuccess SUCCESS, iError ERROR, iFailure FAILURE, String DOWNLOAD_DIR, String EXTENSION, String NAME)
    {
        this.URL = URL;
        this.REQUEST = REQUEST;
        this.SUCCESS = SUCCESS;
        this.ERROR = ERROR;
        this.FAILURE = FAILURE;
        this.DOWNLOAD_DIR = DOWNLOAD_DIR;
        this.EXTENSION = EXTENSION;
        this.NAME = NAME;
    }

    public final void handleDownload()
    {
        if(REQUEST != null)
        {
            REQUEST.onRequestStart();
        }
        RestfulCreater.getRestfulService().download(URL,PARAMS)
                      .enqueue(new Callback<ResponseBody>()
                      {
                          @Override
                          public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response)
                          {
                              if(response.isSuccessful())
                              {
                                  final ResponseBody responseBody = response.body();
                                  final FileCacheTask fileCacheTask = new FileCacheTask(REQUEST,SUCCESS,ERROR,FAILURE);
                                  fileCacheTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,DOWNLOAD_DIR,EXTENSION,responseBody,NAME);

                                  if(fileCacheTask.isCancelled())
                                  {
                                      if(REQUEST != null)
                                      {
                                          REQUEST.onRequestEnd();
                                      }
                                  }
                              }
                              else
                              {
                                  if(ERROR != null)
                                  {
                                      ERROR.onError(response.code(),response.message());
                                  }
                              }
                          }

                          @Override
                          public void onFailure(Call<ResponseBody> call, Throwable t)
                          {
                              if(FAILURE != null)
                              {
                                  FAILURE.onFaliure();
                              }
                              t.printStackTrace();
                          }
                      });
    }
}
