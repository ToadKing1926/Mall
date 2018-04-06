package clouddev.com.czy.ui.scanner;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import clouddev.com.czy.camera.RequestCode;
import clouddev.com.czy.fragment.CoreFragment;
import clouddev.com.czy.util.callback.CallBackManager;
import clouddev.com.czy.util.callback.CallBackType;
import clouddev.com.czy.util.callback.iGlobalCallback;
import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView.ResultHandler;

/**
 * Created by 29737
 */

public class ScannerFragment extends CoreFragment implements ResultHandler
{
    private ScanView mScanView = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if(mScanView == null)
        {
            mScanView = new ScanView(getContext());
        }
        mScanView.setAutoFocus(true);
        mScanView.setResultHandler(this);
    }

    @Override
    public Object setLayout()
    {
        return mScanView;
    }

    @Override
    public void onResume()
    {
        super.onResume();
        if(mScanView != null)
        {
            mScanView.startCamera();
        }
    }

    @Override
    public void onPause()
    {
        super.onPause();
        if(mScanView != null)
        {
            mScanView.stopCameraPreview();
            mScanView.stopCamera();
        }
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView)
    {
    }

    @Override
    public void handleResult(Result result)
    {
        final iGlobalCallback<String> callback = CallBackManager.getInstance().getCallback(CallBackType.SCAN);
        if(callback != null)
        {
            callback.executeCallback(result.getContents());
        }
        getSupportDelegate().pop();
    }
}
