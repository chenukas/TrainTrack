package com.mad.traintrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewSingleReminder extends AppCompatActivity {

    String selectedRemindId;
    String remindId, remindName, remindContent, from, where, date;
    TextView displayName, displayContent, displayFrom, displayTo, displayDate;
    Button updateBtn, deleteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_single_reminder);

        displayName = findViewById(R.id.textView38);
        displayContent = findViewById(R.id.textView44);
        displayFrom = findViewById(R.id.textView45);
        displayTo = findViewById(R.id.textView46);
        displayDate = findViewById(R.id.textView47);
        updateBtn = findViewById(R.id.updateRemind);
        deleteBtn = findViewById(R.id.deleteRemind);

        Intent intent = getIntent();
        selectedRemindId = intent.getStringExtra("selectedRemindId");

        final DatabaseReference dbRefViewRemind = FirebaseDatabase.getInstance().getReference("reminder");

        dbRefViewRemind.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dSnapshot:dataSnapshot.getChildren()) {
                    Reminder reminder = dSnapshot.getValue(Reminder.class);
                    assert reminder != null;
                    if (selectedRemindId.equals(reminder.getRemindID())) {
                        remindId = reminder.getRemindID();
                        remindName = reminder.getName();
                        remindContent = reminder.getContent();
                        from = reminder.getFrom();
                        where = reminder.getWhere();
                        date = reminder.getDate();
                        break;
                    }
                }
                displayName.setText(remindName);
                displayContent.setText(remindContent);
                displayFrom.setText(from);
                displayTo.setText(where);
                displayDate.setText(date);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(ViewSingleReminder.this,UpdateReminder.class);
                intent3.putExtra("currentRemindId", selectedRemindId);
                startActivity(intent3);
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference dltReminder = FirebaseDatabase.getInstance().getReference("reminder");
                dltReminder.child(selectedRemindId).removeValue();
                Toast.makeText(getApplicationContext(), "Reminder deleted successfully", Toast.LENGTH_LONG).show();
                Intent intent4 = new Intent(ViewSingleReminder.this,ViewReminder.class);
                startActivity(intent4);
            }
        });
    }
}
