package com.mad.traintrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Ticket extends AppCompatActivity {

    TextView viewTicketId, viewName, viewNic, viewMobile, viewDate, viewTrainNo, viewStartTime, viewFrom, viewTo, viewClassType, viewNoOfPassengers, viewTotal;
    String selectedTicketId;
    String ticketId, displayName, nic, mobile, date, trainNo, startTime, from, to, classType, noOfPassengers, total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);

        viewTicketId = findViewById(R.id.textView7);
        viewName = findViewById(R.id.textView19);
        viewNic = findViewById(R.id.textView20);
        viewMobile = findViewById(R.id.textView21);
        viewDate = findViewById(R.id.textView26);
        viewTrainNo = findViewById(R.id.textView24);
        viewStartTime = findViewById(R.id.textView25);
        viewFrom = findViewById(R.id.textView22);
        viewTo = findViewById(R.id.textView23);
        viewClassType = findViewById(R.id.textView27);
        viewNoOfPassengers = findViewById(R.id.textView28);
        viewTotal = findViewById(R.id.textView18);

        Intent intent = getIntent();
        selectedTicketId = intent.getStringExtra("selectedTicketId");
        final DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("ticket");

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dSnapshot:dataSnapshot.getChildren()) {
                    TicketDescription ticketDescription = dSnapshot.getValue(TicketDescription.class);
                    assert ticketDescription != null;
                    if (selectedTicketId.equals(ticketDescription.getTicketId())) {
                        ticketId = ticketDescription.getTicketId();
                        displayName = ticketDescription.getDisplayName();
                        nic = ticketDescription.getNic();
                        mobile = ticketDescription.getMobile();
                        date = ticketDescription.getDate();
                        trainNo = ticketDescription.getTrainNo();
                        startTime = ticketDescription.getStartTime();
                        from = ticketDescription.getFrom();
                        to = ticketDescription.getTo();
                        classType = ticketDescription.getClassType();
                        noOfPassengers = String.valueOf(ticketDescription.getNoOfPassengers());
                        total = Double.toString(ticketDescription.getTotal());
                        break;
                    }
                }
                //Toast.makeText(getApplicationContext(),displayName,Toast.LENGTH_SHORT).show();
                viewTicketId.setText(ticketId);
                viewName.setText(displayName);
                viewNic.setText(nic);
                viewMobile.setText(mobile);
                viewDate.setText(date);
                viewTrainNo.setText(trainNo);
                viewStartTime.setText(startTime);
                viewFrom.setText(from);
                viewTo.setText(to);
                viewClassType.setText(classType + " Class");
                viewNoOfPassengers.setText(noOfPassengers);
                viewTotal.setText(total);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
