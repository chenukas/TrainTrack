package com.mad.traintrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewReminder extends AppCompatActivity {

    Button addReminder;
    ArrayList<String> reminderIds;
    ArrayAdapter remindId;
    ListView reminderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reminder);

        addReminder = findViewById(R.id.newRemind);

        final DatabaseReference dbOutputRef = FirebaseDatabase.getInstance().getReference("reminder");

        reminderIds = new ArrayList<>();
        reminderList = findViewById(R.id.reminderlist);

        reminderIds.clear();

        dbOutputRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dSnapshot:dataSnapshot.getChildren()) {
                    Reminder reminder = dSnapshot.getValue(Reminder.class);
                    assert reminder != null;
                    reminderIds.add(reminder.getRemindID());
                }
                remindId = new ArrayAdapter<>(getApplicationContext(),R.layout.custom_list_item,reminderIds);
                reminderList.setAdapter(remindId);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        reminderList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedRemindId = reminderIds.get(position);
                Intent intent = new Intent(ViewReminder.this,ViewSingleReminder.class);
                intent.putExtra("selectedRemindId",selectedRemindId);
                startActivity(intent);
            }
        });

        addReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(ViewReminder.this, AddReminder.class);
                startActivity(intent2);
            }
        });
    }

}
