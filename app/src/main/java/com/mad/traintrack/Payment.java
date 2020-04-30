package com.mad.traintrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ArrayAdapter;
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

  
    TextView textViewName, textViewTicketId, textViewTotal, card1;
    Button paynow, plus;
    String purchasedTicketId, customerName, savedCard, cardNo;
    private ArrayList<String> description;
    private ArrayList<String> cardResult; 
    RadioButton cardCheck;
    double total;
    DatabaseReference dbRef;
    Payments payments;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        Intent intent = getIntent();
        purchasedTicketId = intent.getStringExtra("ticketId");
        total = intent.getDoubleExtra("total",0);
        customerName = intent.getStringExtra("customerName");

        Intent intent2 = getIntent();
        savedCard = intent2.getStringExtra("cardID");
        cardNo = intent2.getStringExtra("cardNo");
        customerName = intent2.getStringExtra("customerName");
        purchasedTicketId = intent2.getStringExtra("ticketId");
        total = intent2.getDoubleExtra("total",0);


        textViewName = findViewById(R.id.textView50);
        textViewTicketId = findViewById(R.id.textView51);
        textViewTotal =findViewById(R.id.textView14);

        card1 = findViewById(R.id.savedCard);
        cardCheck = findViewById(R.id.radioButtonCard);
        plus = findViewById(R.id.button5);
        paynow = findViewById(R.id.button6);
        dbRef = FirebaseDatabase.getInstance().getReference("Payments");
        payments = new Payments();
        description = new ArrayList<String>();
        cardResult = new ArrayList<String>();

        textViewName.setText(customerName);
        textViewTicketId.setText(purchasedTicketId);
        textViewTotal.setText(String.valueOf(total));

        card1.setText(String.valueOf(cardNo));

        addNewCard();

        paynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Payment.this,PurchaseCompleted.class);
                intent2.putExtra("ticketId", purchasedTicketId);
                startActivity(intent2);

            }
        });
    }

    public void addNewCard(){
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Payment.this, AddNewCard.class);
                intent.putExtra("customerName", customerName);
                intent.putExtra("ticketId",purchasedTicketId);
                intent.putExtra("total", total);
                startActivity(intent);
            }
        });


        //Intent intent = getIntent();
        //String cardID = intent.getStringExtra("cardID");
        //int cardNo = intent.getIntExtra("cardNo", 0);

        //card1.setText(String.valueOf(cardNo));
        //cardNo = intent.getStringExtra("cardNo");

        //Toast.makeText(getApplicationContext(), "Card ID: " +cardID + "\nTotal: "+ Double.valueOf(total).toString(),Toast.LENGTH_SHORT).show();

    }
}
