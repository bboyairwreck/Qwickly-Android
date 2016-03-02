package com.bboyairwreck.qwickly;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ModeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode);

        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        Button btnSetUsername = (Button) findViewById(R.id.btnSetUsername);

        if (!QwicklyApplication.getInstance().getUsername().equals("guest")) {
            etUsername.setText(QwicklyApplication.getInstance().getUsername());
            // set cursor at end
            int textLength = etUsername.getText().length();
            etUsername.setSelection(textLength, textLength);
        }

        // Update username if pressed SET
        btnSetUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String etUsernameValue = etUsername.getText().toString().trim();
                if (etUsernameValue.length() > 0) {
                    // Set username if field is not empty
                    QwicklyApplication.getInstance().setUsername(etUsernameValue);
                }
            }
        });


        Button btnCreate = (Button) findViewById(R.id.btnCreate);
        Button btnJoin = (Button) findViewById(R.id.btnJoin);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set username as player 1 for sender

                Intent collabJoinIntent = new Intent(ModeActivity.this, JoinActivity.class);
                collabJoinIntent.putExtra(Constants.PLAYER_TYPE, Constants.PLAYER_TYPE_CREATOR);
                startActivity(collabJoinIntent);
            }
        });

        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set username as player 2 for receiver

                Intent collabJoinIntent = new Intent(ModeActivity.this, JoinActivity.class);
                collabJoinIntent.putExtra(Constants.PLAYER_TYPE, Constants.PLAYER_TYPE_JOINER);
                startActivity(collabJoinIntent);
            }
        });
    }
}
