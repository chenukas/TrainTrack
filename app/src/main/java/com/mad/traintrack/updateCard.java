package com.mad.traintrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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



public class updateCard extends AppCompatActivity {


    EditText newCardNo, newExp, newName, newCvv;
    Integer cardNo, cvv;
    String id, cardNum, cardCvv, name, date;
    Button submit, delete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_card);

        newCardNo = findViewById(R.id.editText20);
        newExp = findViewById(R.id.editText2);
        newCvv = findViewById(R.id.editText4);
        newName = findViewById(R.id.editText5);
        submit = findViewById(R.id.button2);
        PaymentHandle paymentHandle = new PaymentHandle();


       // getData(id);
        //editData(id);


    }

    /*public void getData(String id) {

        Intent intent = getIntent();
        id = intent.getStringExtra("cardID");
        cardNum = intent.getStringExtra("cardNo");
        cardCvv = intent.getStringExtra("cvv");
        name = intent.getStringExtra("name");
        date = intent.getStringExtra("date");

        Toast.makeText(getApplicationContext(), "card ID: " + id + "\nTotal: ", Toast.LENGTH_SHORT).show();

    }

    public void editData(final String id) {

        DatabaseReference editdata = FirebaseDatabase.getInstance().getReference("PaymentHandle").child("cardID");
        editdata.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dSnapshot : dataSnapshot.getChildren()) {
                    PaymentHandle paymentHandle = dSnapshot.getValue(PaymentHandle.class);

                    if (id.equals(paymentHandle.getCardID())) {
                        cardNum = String.valueOf(paymentHandle.getCardNo());
                        date = paymentHandle.getDate();
                        cardCvv = String.valueOf(paymentHandle.getCvv());
                        name =String.valueOf(paymentHandle.getName());

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    } */





}
