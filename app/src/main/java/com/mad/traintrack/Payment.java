package com.mad.traintrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Payment extends AppCompatActivity {


    Button plus,payNow;
    TextView card1, txtDescription, txttotal;
    RadioButton cardCheck;
    private ArrayList<String> description;
    private ArrayList<String> cardResult;
    String purchasedTicketId, savedCard;
    double total;
    DatabaseReference dbRef;
    Payments payments;



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        txtDescription = findViewById(R.id.textView13);
        txttotal = findViewById(R.id.textView14);
        card1 = findViewById(R.id.savedCard);
        cardCheck = findViewById(R.id.radioButtonCard);
        plus = findViewById(R.id.button5);
        payNow = findViewById(R.id.button6);
        dbRef = FirebaseDatabase.getInstance().getReference().child("Payments");
        payments = new Payments();
        description = new ArrayList<String>();
        cardResult = new ArrayList<String>();


        viewDetails();
        saveValues();


        addNewCard();
        tripDetails();
        pay();


    }

    public void saveValues(){
        ArrayList description =  new ArrayList();
        description.add(purchasedTicketId);
        //description.add();


    }


    public void viewDetails(){



    }

    public void addNewCard(){


        Intent i = getIntent();
        String tid = i.getStringExtra("ticketId");
        double total = i.getDoubleExtra("total", 0);



        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Payment.this, AddNewCard.class);
                startActivity(intent);
            }
        });


        Intent intent = getIntent();
        String cardID = intent.getStringExtra("cardID");
        int cardNo = intent.getIntExtra("cardNo", 0);

        card1.setText(String.valueOf(cardNo));
        //cardNo = intent.getStringExtra("cardNo");

        Toast.makeText(getApplicationContext(), "Card ID: " +cardID + "\nTotal: "
                + Double.valueOf(total).toString(),Toast.LENGTH_SHORT).show();

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


        final DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("ticket");//.child(purchasedTicketId);
        //final DatabaseReference dbinput = FirebaseDatabase.getInstance().getReference().child("Payments");
        Toast.makeText(getApplicationContext(), "Ticket ID: " +purchasedTicketId + "\nTotal: "
                + Double.valueOf(total).toString(),Toast.LENGTH_SHORT).show();



        //final DatabaseReference dbRef1 = FirebaseDatabase.getInstance().getReference("ticket").child("Payments");
         dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dSnapshot : dataSnapshot.getChildren()) {
                    TicketDescription ticketDescription = dSnapshot.getValue(TicketDescription.class);
                   //assert payments != null;
                    //ticketDescription.getTotal();
                    String ticketId = ticketDescription.getTicketId();
                    Double total = ticketDescription.getTotal();


                }
            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


       dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dSnapshot:dataSnapshot.getChildren()) {
                    PaymentHandle paymentHandle = dSnapshot.getValue(PaymentHandle.class);
                    assert payments != null;
                    Integer cardNo = paymentHandle.getCardNo();


                }
                //Toast.makeText(getApplicationContext(),"TrainNo: " + trainNo "\nStartTime: " + startTime +"\nFrom: " + from +"\nTo: " + to, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


   public void tripDetails(){

        final  DatabaseReference dbinput = FirebaseDatabase.getInstance().getReference("Payments");
        Payments payments = new Payments();
        payments.setTicketId(purchasedTicketId);
        payments.setTotal(total);
        payments.setCardNo(savedCard);


    }



}
