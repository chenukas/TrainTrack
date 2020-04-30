package com.mad.traintrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

    EditText cardNo,cvv,expDate,name;
    String displayCard, displayCvv, displayExp, displayName;
    Button btndone, btnupdate;
    PaymentHandle paymentHandle;
    String id;
    String customerName, ticketId;
    Double total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_card);

        Intent intentGet = getIntent();
        customerName = intentGet.getStringExtra("customerName");
        ticketId = intentGet.getStringExtra("ticketId");
        total = intentGet.getDoubleExtra("total",0);

        cardNo = (EditText) findViewById(R.id.editText8);
        cvv = (EditText) findViewById(R.id.editText10);
        expDate = (EditText) findViewById(R.id.editText13);
        name = (EditText) findViewById(R.id.editText3);
        btndone = (Button) findViewById(R.id.button4);
        btnupdate = (Button)findViewById(R.id.crdUpdate);


        paymentHandle = new PaymentHandle();

        final DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("PaymentHandle");

        btndone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(cardNo.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Enter the card number", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(expDate.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Enter the expiration date", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(cvv.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Enter cvv", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(name.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Enter your name ", Toast.LENGTH_SHORT).show();
                } else {

                   displayCard = cardNo.getText().toString();
                   displayExp = expDate.getText().toString();
                   displayCvv = cvv.getText().toString();
                   displayName = name.getText().toString();

                    id = dbRef.push().getKey();

                    paymentHandle.setCardNo(displayCard);
                    paymentHandle.setDate(displayExp);
                    paymentHandle.setCvv(displayCvv);
                    paymentHandle.setName(displayName);
                    paymentHandle.setCardID(id);

                    dbRef.child(id).setValue(paymentHandle);
                    //dbRef.child(cardNum).setValue(paymentHandle);

                    Toast.makeText(getApplicationContext(), "Saved ", Toast.LENGTH_SHORT).show();

                    //cardNum = cardNo.getText().toString();

                    clearFields();
                   Intent intent = new Intent(AddNewCard.this, Payment.class);
                   intent.putExtra("cardID", id);
                   intent.putExtra("cardNo", displayCard);
                    intent.putExtra("customerName", customerName);
                    intent.putExtra("ticketId",ticketId);
                    intent.putExtra("total", total);

                   startActivity(intent);

                }
            }
        });

        updateCardDetails();

    }

    public void updateCardDetails(){
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddNewCard.this, updateCard.class);
                intent.putExtra("cardID", id);
                intent.putExtra("cardNo", displayCard);
                intent.putExtra("date", displayExp);
                intent.putExtra("cvv", displayCvv);
                intent.putExtra("name",displayName);


                startActivity(intent);

            }
        });
    }

    private void clearFields() {
        cardNo.setText("");
        cvv.setText("");
        expDate.setText("");
        name.setText("");
    }
}


