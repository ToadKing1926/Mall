package clouddev.com.czy.mall.product;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import butterknife.BindView;
import clouddev.com.czy.fragment.CoreFragment;
import clouddev.com.czy.mall.R;
import clouddev.com.czy.mall.R2;
import clouddev.com.czy.ui.recycler.ItemType;
import clouddev.com.czy.ui.recycler.MultipleFields;
import clouddev.com.czy.ui.recycler.MultipleItemEntity;

/**
 * Created by 29737
 */

public class ImageTabFragment extends CoreFragment
{
    @BindView(R2.id.image_tab_container)
    RecyclerView mRecyclerView = null;

    private static final String ARG_PICTURES = "ARG_PICTURES";

    public static ImageTabFragment create(ArrayList<String> pictures)
    {
        final Bundle args = new Bundle();
        args.putStringArrayList(ARG_PICTURES,pictures);
        final ImageTabFragment fragment = new ImageTabFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Object setLayout()
    {
        return R.layout.image_tab_fragment;
    }
    private void initImages()
    {
        final ArrayList<String> pictureArray = getArguments().getStringArrayList(ARG_PICTURES);
        final ArrayList<MultipleItemEntity> entities = new ArrayList<>();
        final int size;
        if(pictureArray != null)
        {
            size = pictureArray.size();
            for(int i = 0;i < size; i++)
            {
               final String imageUrl = pictureArray.get(i);
               final MultipleItemEntity entity = MultipleItemEntity.builder()
                                                                    .setItemType(ItemType.IMAGE)
                                                                    .setField(MultipleFields.IMAGE_URL,imageUrl)
                                                                    .build();
               entities.add(entity);
            }
            final ImageTabAdapter adapter = new ImageTabAdapter(entities);
            mRecyclerView.setAdapter(adapter);
        }

    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView)
    {
       final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
       mRecyclerView.setLayoutManager(layoutManager);
       initImages();
    }
}
