package com.mad.traintrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SearchTrain extends AppCompatActivity {

    Button search;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_train);

        openTicketDetails();
    }

    public void openTicketDetails() {
        next = (Button) findViewById(R.id.button10);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchTrain.this, TicketDetails.class);
                startActivity(intent);
            }
        });
    }


}
