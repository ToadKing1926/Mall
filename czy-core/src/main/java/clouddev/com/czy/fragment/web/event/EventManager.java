package clouddev.com.czy.fragment.web.event;

import android.support.annotation.NonNull;

import java.util.HashMap;

/**
 * Created by 29737 on 2018/3/15.
 */

public class EventManager
{
    private static final HashMap<String,Event> EVENTS = new HashMap<>();

    private EventManager()
    {

    }

    private static class Holder
    {
        private static final EventManager INSTANCE = new EventManager();
    }

    public static EventManager getInstance()
    {
        return Holder.INSTANCE;
    }

    public EventManager addEvent(@NonNull String name, @NonNull Event event)
    {
        EVENTS.put(name,event);
        return this;
    }

    public Event createEvent(@NonNull  String action)
    {
        Event event = EVENTS.get(action);
        if(event == null)
        {
            throw new NullPointerException("事件未定义！");
        }
        return event;
    }
}
