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

public class TicketDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    String selectedRouteId, selectedClass;
    ArrayAdapter<CharSequence> adapterClass;
    Spinner spinnerClass;
    Button proceed;
    String trainNo, startTime, from, to;
    double firstClass, secondClass, thirdClass, noOfPassengers, total, tempTotal ;
    EditText inputName, inputNic, inputMobile, inputDate, inputNoPassengers;
    String displayName, nic, mobile, date;
    String id;
    TicketDescription ticketDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_details);

        ticketDescription = new TicketDescription();
        total = 0;
        inputName = findViewById(R.id.editText8);
        inputNic = findViewById(R.id.editText13);
        inputMobile = findViewById(R.id.editText10);
        inputDate = findViewById(R.id.editText3);
        inputNoPassengers = findViewById(R.id.editText21);
        proceed = findViewById(R.id.button4);


        spinnerClass = findViewById(R.id.spinner3);
        adapterClass = ArrayAdapter.createFromResource(this, R.array.classType, android.R.layout.simple_spinner_item);
        adapterClass.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerClass.setAdapter(adapterClass);
        spinnerClass.setOnItemSelectedListener(this);

        Intent intent = getIntent();
        selectedRouteId = intent.getStringExtra("selectedRouteId");
        //Toast.makeText(getApplicationContext(), selectedRouteId, Toast.LENGTH_SHORT).show();
        final DatabaseReference dbInputRef = FirebaseDatabase.getInstance().getReference("ticket");
        final DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("route");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dSnapshot:dataSnapshot.getChildren()) {
                    Route route = dSnapshot.getValue(Route.class);
                    assert route != null;
                    if (selectedRouteId.equals(route.getRouteId())) {
                        trainNo = route.getTrainNo();
                        startTime = route.getStartTime();
                        from = route.getFrom();
                        to = route.getTo();
                        firstClass = route.getFirstClass();
                        secondClass = route.getSecondClass();
                        thirdClass = route.getThirdClass();
                        break;
                    }
                }
                //Toast.makeText(getApplicationContext(),"TrainNo: " + trainNo "\nStartTime: " + startTime +"\nFrom: " + from +"\nTo: " + to, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(inputName.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Enter your name with initials", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(inputNic.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Enter your NIC number", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(inputMobile.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Enter your mobile", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(inputDate.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Enter your journey date", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(inputNoPassengers.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Enter the number of passengers", Toast.LENGTH_SHORT).show();
                } else {

                    displayName = inputName.getText().toString();
                    nic = inputNic.getText().toString();
                    mobile = inputMobile.getText().toString();
                    date = inputDate.getText().toString();
                    total = calcTotal();

                    id = dbInputRef.push().getKey();

                    ticketDescription.setTicketId(id);
                    ticketDescription.setDisplayName(displayName);
                    ticketDescription.setNic(nic);
                    ticketDescription.setMobile(mobile);
                    ticketDescription.setDate(date);
                    ticketDescription.setTrainNo(trainNo);
                    ticketDescription.setStartTime(startTime);
                    ticketDescription.setFrom(from);
                    ticketDescription.setTo(to);
                    ticketDescription.setClassType(selectedClass);
                    ticketDescription.setNoOfPassengers(Integer.parseInt(inputNoPassengers.getText().toString()));
                    ticketDescription.setPurchasedBy("");
                    ticketDescription.setPaymentStatus("Pending");
                    ticketDescription.setTotal(total);

                    dbInputRef.child(id).setValue(ticketDescription);
                    Toast.makeText(getApplicationContext(),"Saved: "+ id, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(TicketDetails.this, Payment.class);
                    startActivity(intent);
                    clearFields();
                }
            }
        });
    }

    public double calcTotal() {
        noOfPassengers = Double.parseDouble(inputNoPassengers.getText().toString());

        tempTotal = 0;
        if (selectedClass.equals("First")) {
            tempTotal = firstClass * noOfPassengers;
        } else if (selectedClass.equals("Second")) {
            tempTotal = secondClass * noOfPassengers;
        } else {
            tempTotal = thirdClass * noOfPassengers;
        }

        return tempTotal;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedClass = spinnerClass.getSelectedItem().toString();
        //Toast.makeText(getApplicationContext(),selectedClass,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void clearFields() {
        inputName.setText("");
        inputNic.setText("");
        inputMobile.setText("");
        inputDate.setText("");
        inputNoPassengers.setText("");
    }
}
