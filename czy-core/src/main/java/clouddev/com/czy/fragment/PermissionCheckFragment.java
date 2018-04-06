package clouddev.com.czy.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.yalantis.ucrop.UCrop;

import clouddev.com.czy.camera.CameraImageBean;
import clouddev.com.czy.camera.RequestCode;
import clouddev.com.czy.camera.czyCamera;
import clouddev.com.czy.ui.scanner.ScannerFragment;
import clouddev.com.czy.util.callback.CallBackManager;
import clouddev.com.czy.util.callback.CallBackType;
import clouddev.com.czy.util.callback.iGlobalCallback;
import me.dm7.barcodescanner.zbar.ZBarScannerView;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * Created by 29737
 */

@RuntimePermissions
public abstract class PermissionCheckFragment extends BaseFragment
{
    @NeedsPermission(Manifest.permission.CAMERA)
    void startCamera()
    {
        czyCamera.start(this);
    }

    @NeedsPermission(Manifest.permission.CAMERA)
    void startScan(BaseFragment fragment)
    {
        fragment.getSupportDelegate().startForResult(new ScannerFragment(),RequestCode.SCAN);
    }

    @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void applyForStorage()
    {

    }

    @OnPermissionDenied(Manifest.permission.CAMERA)
    void onCameraDenied()
    {
        Toast.makeText(getContext(),"不允许拍照",Toast.LENGTH_SHORT).show();
    }

    @OnShowRationale(Manifest.permission.CAMERA)
    void onCameraRationale(PermissionRequest request)
    {
        showRationaleDialog(request);
    }

    private void showRationaleDialog(final PermissionRequest request)
    {
        new AlertDialog.Builder(getContext())
                        .setPositiveButton("同意使用", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                                request.proceed();
                            }
                        })
                        .setNegativeButton("不同意", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                                request.cancel();
                            }
                        })
                        .setTitle("权限")
                        .show();
    }

    public void startCameraWithCheck()
    {
        PermissionCheckFragmentPermissionsDispatcher.startCameraWithPermissionCheck(this);
        PermissionCheckFragmentPermissionsDispatcher.applyForStorageWithPermissionCheck(this);
    }

    public void startScanWithCheck(BaseFragment fragment)
    {
        PermissionCheckFragmentPermissionsDispatcher.startScanWithPermissionCheck(this,fragment);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionCheckFragmentPermissionsDispatcher.onRequestPermissionsResult(this, requestCode,grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK)
        {
            switch (requestCode)
            {
                case RequestCode.TAKE_PHOTO:
                    final Uri resultUri = CameraImageBean.getInstance().getPath();
                    UCrop.of(resultUri,resultUri)
                            .withMaxResultSize(400,400)
                            .start(getContext(),this);
                    break;
                case RequestCode.PICK_PHOTO:
                    if (data != null)
                    {
                        final Uri pickPath = data.getData();
                        final String pickCropPath = czyCamera.createCropFile().getPath();
                        UCrop.of(pickPath,Uri.parse(pickCropPath))
                                .withMaxResultSize(400,400)
                                .start(getContext(),this);
                    }
                    break;
                case RequestCode.CROP_PHOTO:
                    final Uri cropUri = UCrop.getOutput(data);
                    @SuppressWarnings("unchecked")
                    final iGlobalCallback<Uri> callback = CallBackManager
                                                           .getInstance()
                                                           .getCallback(CallBackType.ON_CROP);
                    final iGlobalCallback<Uri> mineCallback = CallBackManager
                                                          .getInstance()
                                                          .getCallback(CallBackType.ON_CHANGE_AVATAR);

                    if(callback != null && mineCallback != null)
                    {
                        callback.executeCallback(cropUri);
                        mineCallback.executeCallback(cropUri);
                    }
                    break;
                case RequestCode.CROP_PHOTO_ERROR:
                    Toast.makeText(getContext(),"图片剪裁错误",Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;

            }
        }
    }
}
