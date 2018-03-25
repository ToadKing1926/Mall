package clouddev.com.czy.util.callback;

import java.util.WeakHashMap;

/**
 * Created by 29737
 */

public class CallBackManager
{
    private static final WeakHashMap<Object,iGlobalCallback> CALLBACKS = new WeakHashMap<>();

    private static class Holder
    {
        private static final CallBackManager INSTANCE = new CallBackManager();
    }

    public static CallBackManager getInstance()
    {
        return Holder.INSTANCE;
    }

    public CallBackManager addCallback(Object tag,iGlobalCallback callback)
    {
        CALLBACKS.put(tag,callback);
        return this;
    }

    public iGlobalCallback getCallback(Object tag)
    {
        return CALLBACKS.get(tag);
    }
}
