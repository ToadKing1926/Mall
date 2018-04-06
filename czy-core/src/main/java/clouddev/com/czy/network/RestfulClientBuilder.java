package clouddev.com.czy.network;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

import clouddev.com.czy.network.callback.iError;
import clouddev.com.czy.network.callback.iFailure;
import clouddev.com.czy.network.callback.iRequest;
import clouddev.com.czy.network.callback.iSuccess;
import clouddev.com.czy.ui.LoaderStyle;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by 29737
 */

public class RestfulClientBuilder
{
    private  String mUrl;
    private  Map<String,Object> mParams = new HashMap<>();
    private  iRequest mRequest;
    private  iSuccess mSuccess;
    private  iError mError;
    private  iFailure mFailure;
    private  RequestBody mBody;
    private  LoaderStyle mLoaderStyle;
    private  Context mContext;
    private File mFile;
    private String mDownload_Dir;
    private String mExtension;
    private String mName;


     RestfulClientBuilder()
     {

     }

    public final RestfulClientBuilder url(String url)
    {
        this.mUrl=url;
        return this;
    }

    public final RestfulClientBuilder params(Map<String,Object> params)
    {
        this.mParams = params;
        return this;
    }

    public final RestfulClientBuilder params(String key,String value)
    {
        if(mParams==null)
        {
            mParams = new WeakHashMap<>();
        }
        mParams.put(key,value);
        return this;
    }

    public final RestfulClientBuilder request(iRequest request)
    {
        this.mRequest=request;
        return this;
    }

    public final RestfulClientBuilder success(iSuccess success)
    {
        this.mSuccess=success;
        return this;
    }

    public final RestfulClientBuilder error(iError error)
    {
        this.mError=error;
        return this;
    }

    public final RestfulClientBuilder failure(iFailure failure)
    {
        this.mFailure=failure;
        return this;
    }

    public final RestfulClientBuilder body(String body)
    {
        this.mBody=RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),body);
        return this;
    }

    private Map<String,Object> checkParams()
    {
        if(mParams==null)
        {
            return new WeakHashMap<>();
        }
        return mParams;
    }

    public final RestfulClientBuilder loader(Context context,LoaderStyle style)
    {
        this.mContext=context;
        this.mLoaderStyle=style;
        return this;
    }

    public final RestfulClientBuilder file(String address)
    {
        this.mFile = new File(address);
        return this;
    }

    public final RestfulClientBuilder file(File file)
    {
          this.mFile = file;
          return this;
    }

    public final RestfulClientBuilder dir(String dir)
    {
        this.mDownload_Dir = dir;
        return this;
    }

    public final RestfulClientBuilder extension(String extension)
    {
        this.mExtension = extension;
        return this;
    }

    public final RestfulClientBuilder name(String name)
    {
        this.mName = name;
        return this;
    }

    public final RestfulClient build()
    {
        return new RestfulClient(mUrl,mParams,mRequest,mSuccess,mError,mFailure,mBody,mLoaderStyle,mContext,mFile,mDownload_Dir,mExtension,mName);
    }



}
