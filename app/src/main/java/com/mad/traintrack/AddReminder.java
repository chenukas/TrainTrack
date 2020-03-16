package com.mad.traintrack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class AddReminder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_add_reminder);
    }
}
