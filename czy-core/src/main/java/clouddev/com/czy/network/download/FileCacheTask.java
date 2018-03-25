package clouddev.com.czy.network.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import java.io.File;
import java.io.InputStream;

import clouddev.com.czy.app.appInit;
import clouddev.com.czy.network.callback.iError;
import clouddev.com.czy.network.callback.iFailure;
import clouddev.com.czy.network.callback.iRequest;
import clouddev.com.czy.network.callback.iSuccess;
import clouddev.com.czy.util.FileUtil;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by 29737
 */

public class FileCacheTask extends AsyncTask<Object,Void,File>
{
    private final iRequest REQUEST;
    private final iSuccess SUCCESS;
    private final iError ERROR;
    private final iFailure FAILURE;

    public FileCacheTask(iRequest REQUEST, iSuccess SUCCESS, iError ERROR, iFailure FAILURE)
    {
        this.REQUEST = REQUEST;
        this.SUCCESS = SUCCESS;
        this.ERROR = ERROR;
        this.FAILURE = FAILURE;
    }

    @Override
    protected File doInBackground(Object... params)
    {
        String downloadDir = (String)params[0];
        String extension = (String)params[1];
        final ResponseBody body = (ResponseBody)params[2];
        final String name = (String)params[3];
        final InputStream is = body.byteStream();

        if(downloadDir == null || downloadDir.equals("") )
        {
            downloadDir = "down_loads";
        }
        if(extension == null || extension.equals(""))
        {
            extension = "aloha";
        }
        if(name == null)
        {
            return FileUtil.writeToDisk(is,downloadDir,extension.toUpperCase(),extension);
        }
        else
        {
            return FileUtil.writeToDisk(is,downloadDir,name);
        }
    }

    @Override
    protected void onPostExecute(File file)
    {
        super.onPostExecute(file);
        if(SUCCESS!=null)
        {
            SUCCESS.onSuccess(file.getPath());
        }
        if(REQUEST!=null)
        {
            REQUEST.onRequestEnd();
        }
        updateAPK(file);
    }

    private void updateAPK(File file)
    {
        if(FileUtil.getExtension(file.getPath()).equals("apk"))
        {
            final Intent install =new Intent();
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                   .setAction(Intent.ACTION_VIEW)
                    .setDataAndType(Uri.fromFile(file),"");
            appInit.getApplication().startActivity(install);

        }
    }
}
