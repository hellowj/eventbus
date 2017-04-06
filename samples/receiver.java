package com.company.beijing.app.eventbus;

/**
 * Created by jun wei on 8/4/2016.
 */
public class Receiver {

    public Receiver(){
        EventBusManager.addListener(this, EventCode.ON_SIGNED_IN, "onSignedIn");
    }

    public void onSignedIn(Object o){
        Log.d("message is " + String.valueOf(o));
        //print: message is signed already
    }

}