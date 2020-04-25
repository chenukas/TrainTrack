package com.mad.traintrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Payment extends AppCompatActivity {

    Button plus,payNow;
    RadioButton cardCheck;
    private ArrayList<String> description;
    private ArrayList<String> cardResult;
    ListView description1;
    String purchasedTicketId, from,to,date;
    double total;
    DatabaseReference dbRef;
    String s;
    Payments payments;



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        cardCheck = findViewById(R.id.radioButtonCard);
        plus = findViewById(R.id.button5);
        payNow = findViewById(R.id.button6);
        dbRef = FirebaseDatabase.getInstance().getReference().child("TripDetails");
        payments = new Payments();

        //cardResult = new ArrayList<>();



        addNewCard();
        pay();
        tripDetail();

       /* cardCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(cardCheck.isChecked())
                   final String cardNo = i.getStringExtra("s");
               else
                   cardResult.add("");
            }
        });*/
    }


    //Intent intent = getIntent();

    //final String card1 = intent.getStringExtra("card");



    public void addNewCard(){


        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Payment.this, AddNewCard.class);
                startActivity(intent);
            }
        });
    }

    public void pay(){
        payNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Payment.this, PaymentHistory.class);
                startActivity(intent);
            }
        });
        Intent intent = getIntent();
        purchasedTicketId = intent.getStringExtra("ticketId");
        final DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("ticket").child(purchasedTicketId);
        final DatabaseReference dbinput = FirebaseDatabase.getInstance().getReference().child("TripDetails");
        Toast.makeText(getApplicationContext(), "Ticket ID: " +purchasedTicketId + "\nTotal: "
                + Double.valueOf(total).toString(),Toast.LENGTH_SHORT).show();


        //final DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("ticket").child("TripDetails");
         dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dSnapshot:dataSnapshot.getChildren()) {
                    TicketDescription ticketDescription = dSnapshot.getValue(TicketDescription.class);
                    assert payments != null;
                    from = ticketDescription.getFrom();
                    to = ticketDescription.getTo();
                    total = ticketDescription.getTotal();





                }
                //Toast.makeText(getApplicationContext(),"TrainNo: " + trainNo "\nStartTime: " + startTime +"\nFrom: " + from +"\nTo: " + to, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void tripDetail(){
        payments.setTicketId(purchasedTicketId);
        payments.setTotal(total);


    }



}
