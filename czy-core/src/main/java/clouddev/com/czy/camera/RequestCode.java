package clouddev.com.czy.camera;

import com.yalantis.ucrop.UCrop;

/**
 * Created by 29737
 */

public class RequestCode
{
    public static final int TAKE_PHOTO = 4;
    public static final int PICK_PHOTO = 5;
    public static final int CROP_PHOTO = UCrop.REQUEST_CROP;
    public static final int CROP_PHOTO_ERROR = UCrop.RESULT_ERROR;
    public static final int SCAN = 7;
    public static final int WRITE_STORAGE = 8;
}
