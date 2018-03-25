package clouddev.com.czy.fragment;

/**
 * Created by 29737
 */

public abstract class CoreFragment extends PermissionCheckFragment
{
    @SuppressWarnings("unchecked")
     public <T extends CoreFragment> T getParentfragment()
     {
         return (T)getParentFragment();
     }
}
