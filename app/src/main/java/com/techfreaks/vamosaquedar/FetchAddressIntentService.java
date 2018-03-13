package com.techfreaks.vamosaquedar;

import android.app.IntentService;
import android.content.Intent;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.os.ResultReceiver;

import java.util.Locale;

/**
 * Created by Azra on 2/9/2018.
 */

public class FetchAddressIntentService extends IntentService {

    protected ResultReceiver mReceiver;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public FetchAddressIntentService(String name) {
        super(name);
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param intent Used to name the worker thread, important only for debugging.
     */

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
    }

    public FetchAddressIntentService(String name, ResultReceiver mReceiver) {
        super(name);
        this.mReceiver = mReceiver;
    }

    // ...
    private void deliverResultToReceiver(int resultCode, String message) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.RESULT_DATA_KEY, message);
        mReceiver.send(resultCode, bundle);
    }
}