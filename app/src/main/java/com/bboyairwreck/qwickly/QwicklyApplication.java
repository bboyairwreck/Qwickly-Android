package com.bboyairwreck.qwickly;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;

import com.firebase.client.Firebase;

/**
 * Created by eric on 2/27/16.
 */
public class QwicklyApplication extends Application {
    private final String TAG = QwicklyApplication.class.getSimpleName();
    public static QwicklyApplication instance;    // singleton
    private String username = "guest";
    private Firebase qFirebase;
    private Firebase gameFirebase;
    private Firebase selfPlayerFirebase;

    public QwicklyApplication() {
        if (instance == null) {
            instance = this;
        } else {
            Log.e(TAG, "There is an error. You tried to create more than 1 " + TAG);
        }
    }

    public static QwicklyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Firebase.setAndroidContext(this);
        qFirebase = new Firebase(Constants.FIREBASE_URL);

        // Get username from preferences
        String username = getQSharedPreferences().getString("username", "guest");
        this.setUsername(username);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        if (gameFirebase != null) {
            // TODO remove thyself from any games
            gameFirebase = null;
        }
    }

    public void setUsername(String username) {
        this.username = username;
        getQSharedPreferences().edit().putString("username", username);
    }

    public String getUsername() {
        if (this.username == null) {
            this.username = getQSharedPreferences().getString("username", "guest");
        }
        return this.username;
    }

    public Firebase getQFirebase() {
        return qFirebase;
    }

    public void setGameFirebase(String gameID) {
        this.gameFirebase = this.qFirebase.child("games").child(gameID);
    }

    public Firebase getGameFirebase() {
        return this.gameFirebase;
    }

    public void setSelfPlayerFirebase(Firebase selfPlayerFirebase) {
        this.selfPlayerFirebase = selfPlayerFirebase;
    }

    public String getSelfPlayerID() {
        return selfPlayerFirebase.getKey();
    }

    public SharedPreferences getQSharedPreferences() {
        return getSharedPreferences(getString(R.string.shared_preference_key), MODE_PRIVATE);
    }
}
