package clouddev.com.czy.mall.ui.mine.address;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.citywheel.CityPickerView;

import butterknife.BindView;
import butterknife.OnClick;
import clouddev.com.czy.fragment.CoreFragment;
import clouddev.com.czy.mall.R;
import clouddev.com.czy.mall.R2;
import clouddev.com.czy.ui.recycler.MultipleFields;
import clouddev.com.czy.ui.recycler.MultipleItemEntity;
import clouddev.com.czy.util.callback.CallBackManager;
import clouddev.com.czy.util.callback.CallBackType;
import clouddev.com.czy.util.callback.iGlobalCallback;

import static clouddev.com.czy.mall.converter.AddressDataConverter.ADDRESS_ITEM_TYPE;

/**
 * Created by 29737
 */

public class AddressDetailsFragment extends CoreFragment
{
    @BindView(R2.id.address_name_view)
    EditText mName = null;
    @BindView(R2.id.address_phone_view)
    EditText mPhoneNum = null;
    @BindView(R2.id.address_location_view)
    TextView mLocation = null;
    @BindView(R2.id.address_details_view)
    EditText mDetailsAddress = null;
    @BindView(R2.id.confirm_address_change)
    FloatingActionButton mConfirmButton = null;

    private MultipleItemEntity entity = null;
    private boolean isOK = false;

    @OnClick(R2.id.address_location_view)
    void onPick()
    {
        CityConfig cityConfig=new CityConfig.Builder(getContext())
                .setCityInfoType(CityConfig.CityInfoType.BASE)
                .build();
        CityPickerView cityPicker = new CityPickerView(cityConfig);
        cityPicker.show();
        cityPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district)
            {
                super.onSelected(province, city, district);
                final String Pro=province.getName();
                final String Cit=city.getName();
                if(Pro.equals("香港")||Pro.equals("澳门")||Pro.equals("台湾"))
                {

                    mLocation.setText(Pro.trim() + "-" + Cit.trim());
                }
                else
                {
                    String Dis=district.getName();
                    mLocation.setText(Pro.trim() + "-" + Cit.trim() + "-" + Dis.trim());
                }
                isOK=true;

            }

            @Override
            public void onCancel()
            {
                super.onCancel();
            }
        });
    }

    @Override
    public Object setLayout()
    {
        return R.layout.address_details_fragment;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView)
    {
       mConfirmButton.setOnClickListener(new View.OnClickListener()
       {
           @Override
           public void onClick(View view)
           {
               final String name = mName.getText().toString();
               final String phone = mPhoneNum.getText().toString();
               final String address = new StringBuilder().append(mLocation.getText().toString().replaceAll("-","")).append(mDetailsAddress.getText().toString()).toString();
               entity = MultipleItemEntity.builder()
                                                  .setItemType(ADDRESS_ITEM_TYPE)
                                                  .setField(MultipleFields.ID,5)
                                                  .setField(MultipleFields.NAME,name)
                                                  .setField(MultipleFields.PHONE,phone)
                                                  .setField(MultipleFields.TEXT,address)
                                                  .setField(MultipleFields.TAG,false)
                                                  .build();
               final iGlobalCallback<MultipleItemEntity> callback = CallBackManager
                       .getInstance()
                       .getCallback(CallBackType.ADDRESS);
               if(callback != null)
               {
                   callback.executeCallback(entity);
                   getSupportDelegate().pop();
               }
           }
       });
    }
}
