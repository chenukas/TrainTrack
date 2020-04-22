package com.mad.traintrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class AddNewCard extends AppCompatActivity {

    EditText cardNo, cvv, expDate, name;
    Button  btndone;
    Switch save;
    DatabaseReference dbRef;
    PaymentHandle paymentHandle;
    long maxId = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_card);

        cardNo = (EditText) findViewById(R.id.editText8);
        cvv = (EditText) findViewById(R.id.editText10);
        expDate = (EditText) findViewById(R.id.editText13);
        name = (EditText) findViewById(R.id.editText3);
        save = (Switch) findViewById(R.id.switch1);
        btndone = (Button) findViewById(R.id.button4);

        paymentHandle = new PaymentHandle();
        dbRef = FirebaseDatabase.getInstance().getReference().child("PaymentHandle");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    maxId = (dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





        btndone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Float cardno = Float.parseFloat(cardNo.getText().toString().trim());
                Float cvv0 = Float.parseFloat(cvv.getText().toString().trim());
                paymentHandle.setDate(expDate.getText().toString().trim());
                paymentHandle.setName(name.getText().toString().trim());
                paymentHandle.setCardNo(cardno);
                paymentHandle.setCvv(cvv0);
                dbRef.child(String.valueOf(maxId+1)).setValue(paymentHandle);

                save.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked == true){
                            Intent intent = new Intent(AddNewCard.this,Payment.class);
                            intent.putExtra("cardNo", cardno );

                        }

                    }
                });

                Toast.makeText(AddNewCard.this, "card inserted successfully", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(AddNewCard.this, Payment.class);
                startActivity(intent);
            }

        });


    }
}


