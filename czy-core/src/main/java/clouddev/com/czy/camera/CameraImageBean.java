package clouddev.com.czy.camera;

import android.net.Uri;

/**
 * Created by 29737
 */

public class CameraImageBean
{
    private Uri mPath = null;

    private static final CameraImageBean INSTANCE = new CameraImageBean();

    public static CameraImageBean getInstance()
    {
        return INSTANCE;
    }

    public Uri getPath()
    {
        return mPath;
    }

    public void setPath(Uri path)
    {
        this.mPath = path;
    }
}
