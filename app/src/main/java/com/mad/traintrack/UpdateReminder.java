package com.mad.traintrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class UpdateReminder extends AppCompatActivity {

    String currentRemindId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_reminder);

        Intent intent = getIntent();
        currentRemindId = intent.getStringExtra("currentRemindId");
        Toast.makeText(getApplicationContext(),"Current Remind Id: " +currentRemindId, Toast.LENGTH_SHORT).show();
    }
}
