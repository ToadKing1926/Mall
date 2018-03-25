package clouddev.com.czy.mall.ui.mine.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import butterknife.BindView;
import butterknife.OnClick;
import clouddev.com.czy.fragment.CoreFragment;
import clouddev.com.czy.mall.R;
import clouddev.com.czy.mall.R2;
import clouddev.com.czy.mall.ui.mine.profile.UserProfileFragment;

/**
 * Created by 29737
 */

public class NameFragment extends CoreFragment
{
    @BindView(R2.id.et_name)
    EditText mName = null;
    @OnClick(R2.id.name_submit)
    void onSubmitClick()
    {
        final String name = mName.getText().toString();
        if(name.isEmpty())
        {
            Toast.makeText(getContext(),"姓名必须非空！",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Bundle bundle = new Bundle();
            bundle.putString("name",name);
            setFragmentResult(RESULT_OK,bundle);
            //TODO:请求服务器
            pop();
        }
    }

    @Override
    public Object setLayout()
    {
        return R.layout.name_fragment;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView)
    {

    }
}
