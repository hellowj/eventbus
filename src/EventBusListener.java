package com.company.beijing.app.eventbus;

/**
 * Created by jun wei on 8/2/2016.
 */
public interface EventBusListener {
    public void onReceive(String eventId, Object parameters);
}
