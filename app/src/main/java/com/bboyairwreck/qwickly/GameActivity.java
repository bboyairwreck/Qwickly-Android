package com.bboyairwreck.qwickly;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.firebase.client.Firebase;

public class GameActivity extends Activity {
    public static final String TAG = GameActivity.class.getSimpleName();
    View mDecorView;
    boolean isBAMActive = false;
    private Firebase gameFireBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gameFireBase = QwicklyApplication.getInstance().getGameFirebase();

        mDecorView = getWindow().getDecorView();
        hideSystemUI();

        isBAMActive = true;

        ImageButton ibBAM = (ImageButton) findViewById(R.id.ibBAM);
        ibBAM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "BAM Clicked!");
                if (isBAMActive) {
                    Firebase bamTime = gameFireBase.child("round1").push();
                    bamTime.setValue(QwicklyApplication.getInstance().getSelfPlayerID());
                    isBAMActive = false;
                }
            }
        });
    }

    private void hideSystemUI() {
        // Set the IMMERSIVE flag.
        // Set the content to appear under the system bars so that the content
        // doesn't resize when the system bars hide and show.
        mDecorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
    }
}
