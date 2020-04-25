package com.mad.traintrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateReminder extends AppCompatActivity {

    String currentRemindId;
    EditText reminderName, reminderContent, date;
    TextView from, where;
    Button dltButton, updButton;
    String remindId, remindName,remindContent, start, end, reminderDate;
    Reminder reminder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_reminder);

        reminder = new Reminder();

        final Intent intent = getIntent();
        currentRemindId = intent.getStringExtra("currentRemindId");
        Toast.makeText(getApplicationContext(),"Current Remind Id: " +currentRemindId, Toast.LENGTH_SHORT).show();

        reminderName = findViewById(R.id.remindName);
        reminderContent = findViewById(R.id.remindContent);
        date = findViewById(R.id.date);
        from = findViewById(R.id.editText3);
        where = findViewById(R.id.toWhere);
        dltButton = findViewById(R.id.dltRemind);
        updButton = findViewById(R.id.saveRemind);

        final DatabaseReference dbRefView = FirebaseDatabase.getInstance().getReference("reminder");
        dbRefView.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dSnapshot:dataSnapshot.getChildren()) {
                    Reminder reminder = dSnapshot.getValue(Reminder.class);
                    assert reminder != null;
                    if (currentRemindId.equals(reminder.getRemindID())) {
                        remindId = reminder.getRemindID();
                        remindName = reminder.getName();
                        remindContent = reminder.getContent();
                        start = reminder.getFrom();
                        end = reminder.getWhere();
                        reminderDate = reminder.getDate();
                        break;
                    }
                }
                reminderName.setText(remindName);
                reminderContent.setText(remindContent);
                date.setText(reminderDate);
                from.setText(start);
                where.setText(end);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        updButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseReference updReminderRef = FirebaseDatabase.getInstance().getReference("reminder");
                updReminderRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        reminder.setRemindID(remindId);
                        reminder.setName(reminderName.getText().toString());
                        reminder.setContent(reminderContent.getText().toString());
                        reminder.setFrom(start);
                        reminder.setWhere(end);
                        reminder.setDate(date.getText().toString());

                        updReminderRef.child(currentRemindId).setValue(reminder);
                        Toast.makeText(getApplicationContext(), "Reminder is updated", Toast.LENGTH_SHORT).show();
                        Intent intent3 = new Intent(UpdateReminder.this,ViewSingleReminder.class);
                        intent3.putExtra("selectedRemindId",remindId);
                        startActivity(intent3);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

        dltButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference dltReminder = FirebaseDatabase.getInstance().getReference("reminder");
                dltReminder.child(remindId).removeValue();
                Toast.makeText(getApplicationContext(), "Reminder deleted successfully", Toast.LENGTH_LONG).show();
                Intent intent2 = new Intent(UpdateReminder.this,ViewReminder.class);
                startActivity(intent2);
            }
        });


    }
}
