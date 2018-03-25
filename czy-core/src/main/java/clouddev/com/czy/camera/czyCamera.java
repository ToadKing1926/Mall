package clouddev.com.czy.camera;

import android.net.Uri;

import clouddev.com.czy.fragment.PermissionCheckFragment;
import clouddev.com.czy.util.FileUtil;

/**
 * Created by 29737
 */

public class czyCamera
{
    public static Uri createCropFile()
    {
        return Uri.parse(FileUtil.createFile("crop_image",FileUtil.getFileNameByTime("IMG","jpg")).getPath());
    }

    public static void start(PermissionCheckFragment fragment)
    {
        new CameraHandler(fragment).startCameraDialog();
    }
}
