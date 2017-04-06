package com.company.beijing.app.eventbus;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by jun wei on 8/2/2016.
 */
public class EventBusManager {

    private static String SEPARATOR = "@";
    private static ConcurrentHashMap<String, EventBusListener> mMapClassEventId = new ConcurrentHashMap<String, EventBusListener>();

    public static void addListener(final Object destination, final String eventId, final String method)
    {
        String key = getKey(destination,eventId,method);
        EventBusListener listener = new EventBusListener()
        {
            @Override
            public void onReceive(String eid, Object parameters) {
                if(eid != null && eventId !=null && eid.equals(eventId))
                {
                    Class<?> w = destination.getClass();
                    try
                    {
                        Method action=w.getMethod(method,Object.class);
                        action.invoke(destination,parameters);
                    } catch (Exception e) { e.printStackTrace(); }
                }
            }
        };
        mMapClassEventId.put(key, listener);
    }
    private static String getKey(final Object destination, final String eventId, final String method)
    {
        String className = destination.getClass().getName();
        String eid = eventId;
        String function = method;
        if(eid == null) eid = "";
        if(function == null) function = "";

        String key = className+SEPARATOR+eid+SEPARATOR+function;
        return key;
    }
    private static boolean isHas(String key, String eventId)
    {
        if(key == null) return false;
        if(key.indexOf(SEPARATOR+eventId+SEPARATOR) > -1)
            return true;
        else
            return false;
    }

    public static void removeListener(final Object destination, final String eventId, String method)
    {
        mMapClassEventId.remove(getKey(destination,eventId,method));
    }
    public static void dispatchEvent(Object source, String eventId, Object parameters)
    {
        EventMessage event = new EventMessage(source, eventId,parameters);
        notifyListeners(eventId, event);
    }

    private static void notifyListeners(String eventId, EventMessage event)
    {
        String key = "";
        Set<String> keys = mMapClassEventId.keySet();
        for (Iterator<String> it = keys.iterator(); it.hasNext();)
        {
            key = it.next();
            if(isHas(key, eventId))
            {
                EventBusListener listener = mMapClassEventId.get(key);
                listener.onReceive(event.getEventId(), event.getParameters());
            }
        }
    }
}
