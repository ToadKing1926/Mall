package clouddev.com.czy.mall.ui.mine.address;

import android.view.View;
import android.widget.TextView;

import java.util.List;

import clouddev.com.czy.mall.R;
import clouddev.com.czy.mall.converter.AddressDataConverter;
import clouddev.com.czy.ui.recycler.MultipleFields;
import clouddev.com.czy.ui.recycler.MultipleItemEntity;
import clouddev.com.czy.ui.recycler.MultipleRecyclerViewAdapter;
import clouddev.com.czy.ui.recycler.MultipleViewHolder;

/**
 * Created by 29737
 */

public class AddressAdapter extends MultipleRecyclerViewAdapter
{
    public AddressAdapter(List<MultipleItemEntity> data)
    {
        super(data);
        addItemType(AddressDataConverter.ADDRESS_ITEM_TYPE, R.layout.item_address);
    }

    @Override
    protected void convert(final MultipleViewHolder holder, MultipleItemEntity entity)
    {
        super.convert(holder, entity);
        switch (holder.getItemViewType())
        {
            case AddressDataConverter.ADDRESS_ITEM_TYPE:
                final String name = entity.getField(MultipleFields.NAME);
                final String phone = entity.getField(MultipleFields.PHONE);
                final String address = entity.getField(MultipleFields.TEXT);
                final boolean isDefault = entity.getField(MultipleFields.TAG);
                final int id = entity.getField(MultipleFields.ID);

                final TextView nameText = holder.getView(R.id.address_name);
                final TextView phoneText = holder.getView(R.id.address_phone);
                final TextView addressText = holder.getView(R.id.address_address);
                final TextView deleteText = holder.getView(R.id.address_delete);

                deleteText.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                       //TODO:请求服务器
                        remove(holder.getLayoutPosition());
                    }
                });

                nameText.setText(name);
                phoneText.setText(phone);
                addressText.setText(address);
                break;
            default:
                break;
        }
    }
}
