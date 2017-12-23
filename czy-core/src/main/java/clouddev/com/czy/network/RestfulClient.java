package clouddev.com.czy.network;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.util.Map;

import clouddev.com.czy.network.callback.RequestCallback;
import clouddev.com.czy.network.callback.iError;
import clouddev.com.czy.network.callback.iFailure;
import clouddev.com.czy.network.callback.iRequest;
import clouddev.com.czy.network.callback.iSuccess;
import clouddev.com.czy.network.download.DownloadHandler;
import clouddev.com.czy.ui.LoaderStyle;
import clouddev.com.czy.ui.czyLoader;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

/**
 * Created by 29737 on 2017/12/24.
 */

public class RestfulClient
{
    private final String URL;
    private final Map<String,Object> PARAMS;
    private final iRequest REQUEST;
    private final iSuccess SUCCESS;
    private final iError ERROR;
    private final iFailure FAILURE;
    private final RequestBody BODY;
    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;
    private final File FILE;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;

    public RestfulClient(String URL, Map<String, Object> PARAMS, iRequest REQUEST, iSuccess SUCCESS, iError ERROR, iFailure FAILURE, RequestBody BODY,LoaderStyle LOADER_STYLE,Context CONTEXT,File FILE,String DOWNLOAD_DIR,String EXTENSION,String NAME)
    {
        this.URL = URL;
        this.PARAMS = PARAMS;
        this.REQUEST = REQUEST;
        this.SUCCESS = SUCCESS;
        this.ERROR = ERROR;
        this.FAILURE = FAILURE;
        this.BODY = BODY;
        this.LOADER_STYLE = LOADER_STYLE;
        this.CONTEXT = CONTEXT;
        this.FILE = FILE;
        this.DOWNLOAD_DIR = DOWNLOAD_DIR;
        this.EXTENSION = EXTENSION;
        this.NAME = NAME;
    }

    public static RestfulClientBuilder builder()
    {
        return new RestfulClientBuilder();
    }

    private void request(HttpMethod method)
    {
        final RestfulService service=RestfulCreater.getRestfulService();
        Call<String> call=null;

        if(REQUEST!=null)
        {
            REQUEST.onRequestStart();
        }

        if(LOADER_STYLE!=null)
        {
            czyLoader.showLoading(CONTEXT,LOADER_STYLE.name());
        }

        switch (method)
        {
            case GET:
                call = service.get(URL,PARAMS);
                break;
            case PUT:
                call = service.put(URL,PARAMS);
                break;
            case PUT_RAW:
                call = service.putRaw(URL,BODY);
                break;
            case POST:
                call = service.post(URL,PARAMS);
                break;
            case POST_RAW:
                call = service.postRaw(URL,BODY);
                break;
            case DELETE:
                call = service.delete(URL,PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()),FILE);
                final MultipartBody.Part body = MultipartBody.Part.createFormData("file",FILE.getName(),requestBody);
                call = RestfulCreater.getRestfulService().upload(URL,body);
                break;
            default:
                break;
        }

        if(call!=null)
        {
            call.enqueue(new RequestCallback(REQUEST,SUCCESS,ERROR,FAILURE,LOADER_STYLE));
        }
    }

    public final void get()
    {
        request(HttpMethod.GET);
    }

    public final void post()
    {
        if(BODY == null)
        {
            request(HttpMethod.POST);
        }
        else
        {
            if(PARAMS != null)
            {
                throw new RuntimeException("PostRAW要求空参数！");
            }
            request(HttpMethod.POST_RAW);
        }
    }

    public final void put()
    {
        if(BODY == null)
        {
            request(HttpMethod.PUT);
        }
        else
        {
            if(PARAMS != null)
            {
                throw new RuntimeException("PutRAW要求空参数！");
            }
            request(HttpMethod.PUT_RAW);
        }

    }

    public final void delete()
    {
        request(HttpMethod.DELETE);
    }

    public final void upload()
    {
        request(HttpMethod.UPLOAD);
    }

    public final void download()
    {
        new DownloadHandler(URL,REQUEST,SUCCESS,ERROR,FAILURE,DOWNLOAD_DIR,EXTENSION,NAME).handleDownload();
    }
}
