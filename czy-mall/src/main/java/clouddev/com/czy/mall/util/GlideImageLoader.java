package clouddev.com.czy.mall.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by 29737 on 2018/1/1.
 */

public class GlideImageLoader extends ImageLoader {

    @Override
    public void displayImage(Context context, Object path, ImageView imageView)
    {
        Glide.with(context).load(path).into(imageView);
    }
}
