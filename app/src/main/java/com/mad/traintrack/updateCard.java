package com.mad.traintrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class updateCard extends AppCompatActivity { EditText cardNo, expDate, cvv, name;
    Button submit;
    PaymentHandle paymentHandle;
    DatabaseReference dbRef;
    String cardID, id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_card);

        cardNo = (EditText) findViewById(R.id.editText);
        cvv = (EditText) findViewById(R.id.editText2);
        expDate = (EditText) findViewById(R.id.editText4);
        name = (EditText) findViewById(R.id.editText5);
        submit = findViewById(R.id.button);
        paymentHandle = new PaymentHandle();

        final Intent intent = getIntent();
        cardID = intent.getStringExtra("id");
        final DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("paymentHandle").child("cardID");

       //DatabaseReference viewRef = FirebaseDatabase.getInstance().getReference("PaymentHandle");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference updRef = FirebaseDatabase.getInstance().getReference().child("PaymentHandle");
                updRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild(paymentHandle.getCardID())) {
                            try {
                                paymentHandle.setCardNo(Integer.parseInt(cardNo.getText().toString().trim()));
                                paymentHandle.setDate(expDate.getText().toString().trim());
                                paymentHandle.setCvv((Integer.parseInt(cvv.getText().toString().trim())));
                                paymentHandle.setName(name.getText().toString().trim());

                               // dbRef = FirebaseDatabase.getInstance().getReference().child("PaymentHandle").child("paymentHandle1");
                                dbRef.setValue(paymentHandle);


                                Toast.makeText(getApplicationContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();

                            } catch (NumberFormatException e) {
                                Toast.makeText(getApplicationContext(), "Invalid ", Toast.LENGTH_SHORT).show();

                            }
                        } else
                            Toast.makeText(getApplicationContext(), "No Source to Update", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        /*submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseReference updateRef = FirebaseDatabase.getInstance().getReference().child("PaymentHandle");
                updateRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild("cardID")){
                            try{
                                paymentHandle.setCardNo(Integer.parseInt(cardNo.getText().toString().trim()));
                                paymentHandle.setDate(expDate.getText().toString().trim());
                                paymentHandle.setCvv((Integer.parseInt(cvv.getText().toString().trim())));
                                paymentHandle.setName(name.getText().toString().trim());

                               // dbRef = FirebaseDatabase.getInstance().getReference().child("PaymentHandle").child("cardID");
                                Toast.makeText(getApplicationContext(),"updated successfully",Toast.LENGTH_SHORT).show();
                            }catch (NumberFormatException e){
                                Toast.makeText(getApplicationContext(),"Invalid",Toast.LENGTH_SHORT).show();
                            }

                            // dbRef = FirebaseDatabase.getInstance().getReference().child("PaymentHandle").child("cardID");
                            //dbRef.setValue("CardID");



                            Toast.makeText(getApplicationContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });*/

    }




}
