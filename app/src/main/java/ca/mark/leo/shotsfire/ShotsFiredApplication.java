package ca.mark.leo.shotsfire;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by tsd010 on 2016-02-19.
 */
public class ShotsFiredApplication extends Application {

    private static Firebase mFirebaseRef;

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
        mFirebaseRef = new Firebase("https://sizzling-inferno-7743.firebaseio.com/");
    }

    public static Firebase getFirebaseRef() {
        return mFirebaseRef;
    }
}
