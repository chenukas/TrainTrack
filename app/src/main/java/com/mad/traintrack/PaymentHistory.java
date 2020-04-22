package com.mad.traintrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PaymentHistory extends AppCompatActivity {

    ListView listView;
    FirebaseDatabase database;
    DatabaseReference dbRef;
    ArrayList<PaymentHandle> list;
    ArrayAdapter<PaymentHandle> adapter;
    private Button clearAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_history);


        listView = (ListView) findViewById(R.id.list);
        database = FirebaseDatabase.getInstance();
        dbRef = database.getReference("PaymentHandle");
        list = new ArrayList<>();
        adapter = new ArrayAdapter<PaymentHandle>(this,R.layout.activity_add_new_card);
        clearAll = (Button)findViewById(R.id.clr_all);


    }


}
