package clouddev.com.czy.camera;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.blankj.utilcode.util.FileUtils;

import java.io.File;

import clouddev.com.czy.R;
import clouddev.com.czy.fragment.PermissionCheckFragment;
import clouddev.com.czy.util.FileUtil;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;


/**
 * Created by 29737
 */

public class CameraHandler implements View.OnClickListener
{
    private final AlertDialog DIALOG;
    private final PermissionCheckFragment FRAGMENT;

    public CameraHandler(PermissionCheckFragment fragment)
    {
        this.FRAGMENT = fragment;
        DIALOG = new AlertDialog.Builder(fragment.getContext()).create();
    }


    public final void startCameraDialog()
    {
        DIALOG.show();
        final Window window = DIALOG.getWindow();
        if(window != null)
        {
            window.setContentView(R.layout.dialog_camera_panel);
            window.setGravity(Gravity.BOTTOM);
            window.setWindowAnimations(R.style.anim_panel_up_from_bottom);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            final WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            params.dimAmount = 0.5f;
            window.setAttributes(params);
            window.findViewById(R.id.photodialog_cancel).setOnClickListener(this);
            window.findViewById(R.id.photodialog_take).setOnClickListener(this);
            window.findViewById(R.id.photodialog_native).setOnClickListener(this);
        }
    }

    private String getPhotoName()
    {
        return FileUtil.getFileNameByTime("IMG","jpg");
    }


    private void takePhoto()
    {
        final String curPhotoName = getPhotoName();
        final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        final File temp = new File(FileUtil.CAMERA_PHOTO_DIR,curPhotoName);

        //安卓7.0以上
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
        {
            final ContentValues contentValues = new ContentValues(1);
            contentValues.put(MediaStore.Images.Media.DATA,temp.getPath());
            final Uri uri = FRAGMENT.getContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,contentValues);

            final File realFile = FileUtils.getFileByPath(FileUtil.getRealFilePath(FRAGMENT.getContext(),uri));
            final Uri realUri = Uri.fromFile(realFile);
            CameraImageBean.getInstance().setPath(realUri);
            intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
        }
        else
        {
            final Uri fileuri = Uri.fromFile(temp);
            CameraImageBean.getInstance().setPath(fileuri);
            intent.putExtra(MediaStore.EXTRA_OUTPUT,fileuri);
        }
        FRAGMENT.startActivityForResult(intent,RequestCode.TAKE_PHOTO);
    }

    private void pickPhoto()
    {
        final Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        FRAGMENT.startActivityForResult(Intent.createChooser(intent,"照片"),RequestCode.PICK_PHOTO);
    }

    @Override
    public void onClick(View view)
    {
        int id = view.getId();
        if(id == R.id.photodialog_cancel)
        {
            DIALOG.cancel();
        }
        else if(id == R.id.photodialog_native)
        {
            pickPhoto();
            DIALOG.cancel();
        }
        else if(id == R.id.photodialog_take)
        {
            takePhoto();
            DIALOG.cancel();
        }
    }

}
