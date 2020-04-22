package com.mad.traintrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PurchasedTickets extends AppCompatActivity {

    ArrayList<String> ticketIds;
    ArrayAdapter ticketId;
    ListView ticketList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchased_tickets);

        final DatabaseReference dbOutputRef = FirebaseDatabase.getInstance().getReference("ticket");

        ticketIds = new ArrayList<>();
        ticketList = findViewById(R.id.ticketList);

        ticketIds.clear();

        dbOutputRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot dSnapshot:dataSnapshot.getChildren()) {
                        TicketDescription ticketDescription = dSnapshot.getValue(TicketDescription.class);
                        assert ticketDescription != null;
                        ticketIds.add(ticketDescription.getTicketId());
                    }
                    ticketId = new ArrayAdapter<>(getApplicationContext(),R.layout.custom_list_item,ticketIds);
                    ticketList.setAdapter(ticketId);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        ticketList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedTicketId = ticketIds.get(position);
                Intent intent = new Intent(PurchasedTickets.this,Ticket.class);
                intent.putExtra("selectedTicketId",selectedTicketId);
                startActivity(intent);
            }
        });
    }
}
