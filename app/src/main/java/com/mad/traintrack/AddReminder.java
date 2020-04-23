package com.mad.traintrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddReminder extends AppCompatActivity {

    EditText txtName, txtContent, txtFrom, txtTo, txtDate, txtTime, txtSetTime;
    Button buttonUpdate, buttonAdd;
    DatabaseReference dbInputRef;
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
        txtFrom = findViewById(R.id.editText3);
        txtTo = findViewById(R.id.toWhere);
        txtDate = findViewById(R.id.date);
        txtTime = findViewById(R.id.time);
        txtSetTime = findViewById(R.id.setTime);

        buttonAdd = findViewById(R.id.saveRemind);
        buttonUpdate = findViewById(R.id.updateRemind);

        remind = new Reminder();

        dbInputRef = FirebaseDatabase.getInstance().getReference("reminder");

        //Add Remind

        buttonAdd.setOnClickListener(new View.OnClickListener() {
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

        DatabaseReference viewRef = FirebaseDatabase.getInstance().getReference("Reminder");
        viewRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()){
                    txtName.setText(dataSnapshot.child("name").getValue().toString());
                    txtContent.setText(dataSnapshot.child("content").getValue().toString());
                    txtFrom.setText(dataSnapshot.child("from").getValue().toString());
                    txtTo.setText(dataSnapshot.child("where").getValue().toString());
                    txtDate.setText(dataSnapshot.child("date").getValue().toString());
                }else{
                    Toast.makeText(getApplicationContext(), "No source to display", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 final DatabaseReference updateRef = FirebaseDatabase.getInstance().getReference().child("Reminder");
                 updateRef.addListenerForSingleValueEvent(new ValueEventListener() {
                     @Override
                     public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                         if(dataSnapshot.hasChild(id)){
                             remind.setName(txtName.getText().toString().trim());
                             remind.setContent(txtContent.getText().toString().trim());
                             remind.setFrom(txtFrom.getText().toString().trim());
                             remind.setWhere(txtTo.getText().toString().trim());
                             remind.setDate(txtDate.getText().toString().trim());

                             dbInputRef = FirebaseDatabase.getInstance().getReference().child("Reminder").child("Id");
                             dbInputRef.setValue("Id");
                             clearControls();

                             Toast.makeText(getApplicationContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                         }
                     }

                     @Override
                     public void onCancelled(@NonNull DatabaseError databaseError) {

                     }
                 });
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
