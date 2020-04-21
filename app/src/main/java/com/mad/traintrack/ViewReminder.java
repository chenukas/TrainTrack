package com.mad.traintrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ViewReminder extends AppCompatActivity {

    Button viewreminder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reminder);

        OpenViewReminder();
    }

    public void OpenViewReminder() {
        viewreminder = (Button) findViewById(R.id.newRemind);

        viewreminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewReminder.this, AddReminder.class);
                startActivity(intent);
            }
        });
    }
}
