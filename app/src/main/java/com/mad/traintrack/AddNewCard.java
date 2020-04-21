package com.mad.traintrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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


public class AddNewCard extends AppCompatActivity {

    EditText cardNo, cvv, expDate, name;
    Button btnsave,btndone;
    FirebaseDatabase database;
    DatabaseReference dbRef;
    PaymentHandle paymentHandle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_card);

        cardNo = (EditText) findViewById(R.id.editText8);
        cvv = (EditText) findViewById(R.id.editText13);
        expDate = (EditText) findViewById(R.id.editText10);
        name = (EditText) findViewById(R.id.editText3);
        btnsave = (Button) findViewById(R.id.switch1);
        btndone = (Button) findViewById(R.id.button4);

        paymentHandle = new PaymentHandle();
        database = FirebaseDatabase.getInstance();
        dbRef = database.getReference().child("PaymentHandle").child("card01");


        /*DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("PaymentHandle").child("card1");
        /readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    cardNo.setText(dataSnapshot.child("cardNo").getValue().toString());
                    cvv.setText(dataSnapshot.child("cvv").getValue().toString());
                    expDate.setText(dataSnapshot.child("date").getValue().toString());
                    name.setText(dataSnapshot.child("name").getValue().toString());

                } else
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/

    }
    private void getValues() {
        cardNo.setVisibility(View.VISIBLE);
        cvv.setVisibility(View.VISIBLE);
        expDate.setVisibility(View.VISIBLE);
        name.setVisibility(View.VISIBLE);

    }

    public void btndone(View view){
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dbRef.child("card01").setValue(paymentHandle);
                Toast.makeText(AddNewCard.this, "Card details inserted", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    /*private void clearControls() {
        cardNo.setText("");
        cvv.setText("");
        expDate.setText("");
        name.setText("");

    }*/
}
