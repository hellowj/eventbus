package com.company.beijing.app.eventbus;

import java.util.EventObject;

/**
 * Created by jun wei on 8/2/2016.
 */
public class EventMessage extends EventObject {

    private static final long serialVersionUID = 6255664332581555248L;
    private Object  mSource;
    private String mEventId = "";
    private Object  mParameters;

    public EventMessage(Object source, String eventId, Object params)
    {
        super(source);
        this.mSource = source;
        this.mEventId = eventId;
        this.mParameters = params;
    }
    public Object getSource()
    {
        return mSource;
    }
    public String getEventId()
    {
        return mEventId;
    }
    public Object getParameters()
    {
        return mParameters;
    }

}
