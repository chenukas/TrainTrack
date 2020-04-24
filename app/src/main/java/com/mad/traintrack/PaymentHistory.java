package com.mad.traintrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PaymentHistory extends AppCompatActivity {

      ListView listView;
      Button clearAll;
      ArrayList<String> list = new ArrayList<>( );
      PaymentHandle paymentHandle;
      DatabaseReference dbref;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_history);

        listView = findViewById(R.id.payList);
        clearAll = findViewById(R.id.clr_all);
        paymentHandle = new PaymentHandle();

        final ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("PaymentHandle");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    //list.add(snapshot.getValue().toString());
                    PaymentHandle paymentHandle = snapshot.getValue(PaymentHandle.class);
                    String txt = paymentHandle.getDate()+"  "+paymentHandle.getCardID()+"-"+paymentHandle.getCvv().toString()+ "  total- "+paymentHandle.getCardNo().toString();
                    list.add(txt);
                }
                   adapter.notifyDataSetChanged();
                }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

        clearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference deleteRef = FirebaseDatabase.getInstance().getReference().child("PaymentHandle");
                deleteRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChildren()) {
                            dbref = FirebaseDatabase.getInstance().getReference().child("PaymentHandle");
                            dbref.removeValue();
                            Toast.makeText(getApplicationContext(), "You have successfully removed", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }


}
