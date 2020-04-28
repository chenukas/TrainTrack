package com.mad.traintrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddReminder extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText txtName, txtContent, txtDate;
    Button buttonAdd;
    Reminder remind;
    String name, content, from, where, date;
    String id;
    ArrayAdapter<CharSequence> adapterBeginning, adapterDestination;
    Spinner spinnerBeginning, spinnerDestination;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);

        spinnerBeginning = findViewById(R.id.editText3);
        spinnerDestination = findViewById(R.id.toWhere);

        adapterBeginning = ArrayAdapter.createFromResource(this, R.array.stationsFrom,android.R.layout.simple_spinner_item);
        adapterBeginning.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBeginning.setAdapter(adapterBeginning);
        adapterDestination = ArrayAdapter.createFromResource(this,R.array.stationsTo, android.R.layout.simple_spinner_item);
        adapterDestination.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDestination.setAdapter(adapterDestination);
        spinnerBeginning.setOnItemSelectedListener(this);
        spinnerDestination.setOnItemSelectedListener(this);

        txtName = findViewById(R.id.remindName);
        txtContent = findViewById(R.id.remindContent);
        txtDate = findViewById(R.id.date);

        buttonAdd = findViewById(R.id.saveRemind);

        remind = new Reminder();

        final DatabaseReference dbInputRef = FirebaseDatabase.getInstance().getReference("reminder");

        //Add Remind

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if (TextUtils.isEmpty(txtName.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter a name", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtContent.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter a content", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtDate.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter the date", Toast.LENGTH_SHORT).show();
                    else {
                        name = txtName.getText().toString();
                        content = txtContent.getText().toString();
                        date = txtDate.getText().toString();

                        id = dbInputRef.push().getKey();

                        remind.setRemindID(id);
                        remind.setName(name);
                        remind.setContent(content);
                        remind.setFrom(from);
                        remind.setWhere(where);
                        remind.setDate(date);

                        dbInputRef.child(id).setValue(remind);
                        Toast.makeText(getApplicationContext(), "Created Reminder successfully", Toast.LENGTH_SHORT).show();
                        clearControls();
                        Intent intent = new Intent(AddReminder.this,ViewReminder.class);
                        startActivity(intent);
                    }
                }
        });
    }

    private void clearControls(){
        txtName.setText("");
        txtContent.setText("");
        txtDate.setText("");

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        from = spinnerBeginning.getSelectedItem().toString();
        where = spinnerDestination.getSelectedItem().toString();
       // Toast.makeText(getApplicationContext(), "From: "+from + "\nWhere: "+where, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
