package com.company.beijing.app.eventbus;

/**
 * Created by jun wei on 8/4/2016.
 */
public class Sender {

    public void send(){
        EventBusManager.dispatchEvent(this, EventCode.ON_SIGNED_IN, "signed already");
    }

}