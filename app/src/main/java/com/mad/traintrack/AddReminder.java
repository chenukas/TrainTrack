package com.mad.traintrack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class AddReminder extends AppCompatActivity {

    EditText txtName, txtContent, txtFrom, txtTo, txtDate, txtTime, txtSetTime;
    Button buttonAddRemind, buttonSave;
    //DatabaseReference dbRef;
    Reminder remind;
    String name, content, from, where, date;
    String id;

    private void clearControls(){
        txtName.setText("");
        txtContent.setText("");
        txtFrom.setText("");
        txtTo.setText("");
        txtDate.setText("");
        txtTime.setText("");
        txtSetTime.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);

        txtName = findViewById(R.id.remindName);
        txtContent = findViewById(R.id.remindContent);
        txtFrom = findViewById(R.id.from);
        txtTo = findViewById(R.id.toWhere);
        txtDate = findViewById(R.id.date);
        txtTime = findViewById(R.id.time);
        txtSetTime = findViewById(R.id.setTime);

        buttonAddRemind = findViewById(R.id.addRemind);
        buttonSave = findViewById(R.id.saveRemind);

        remind = new Reminder();

        final DatabaseReference dbInputRef = FirebaseDatabase.getInstance().getReference("reminder");

        //Add Remind

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if (TextUtils.isEmpty(txtName.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter a name", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtContent.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter a content", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtFrom.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter the place", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtTo.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter your destination", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtDate.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter the date", Toast.LENGTH_SHORT).show();
                    else {
                        name = txtName.getText().toString();
                        content = txtContent.getText().toString();
                        from = txtFrom.getText().toString();
                        where = txtTo.getText().toString();
                        date = txtDate.getText().toString();

                        id = dbInputRef.push().getKey();

                        remind.setName(name);
                        remind.setContent(content);
                        remind.setFrom(from);
                        remind.setWhere(where);
                        remind.setDate(date);

                        dbInputRef.child(id).setValue(remind);
                        Toast.makeText(getApplicationContext(), "Created Reminder successfully", Toast.LENGTH_SHORT).show();
                        clearControls();
                    }
                }
        });

        /*public void createNotification() {
            Intent myIntent = new Intent(getApplicationContext() , NotifyService. class ) ;
            AlarmManager alarmManager = (AlarmManager) getSystemService( ALARM_SERVICE ) ;
            PendingIntent pendingIntent = PendingIntent. getService ( this, 0 , myIntent , 0 ) ;
            Calendar calendar = Calendar. getInstance () ;
            calendar.set(Calendar. SECOND , 0 ) ;
            calendar.set(Calendar. MINUTE , 0 ) ;
            calendar.set(Calendar. HOUR , 0 ) ;
            calendar.set(Calendar. AM_PM , Calendar. AM ) ;
            calendar.add(Calendar. DAY_OF_MONTH , 1 ) ;
            alarmManager.setRepeating(AlarmManager. RTC_WAKEUP , calendar.getTimeInMillis() , 1000 * 60 * 60 * 24 , pendingIntent) ;
        }*/
    }

}
