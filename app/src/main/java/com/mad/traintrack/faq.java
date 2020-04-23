package com.mad.traintrack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class faq extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        Log.d(TAG, "onCreate: started");
        initImageBitmaps();
    }
    private void initImageBitmaps(){
        Log.d(TAG, "initImageBitmaps: started");
        mImageUrls.add("https://thumbs.dreamstime.com/z/faq-icon-vector-frequently-illustration-symbol-information-sign-logo-web-sites-mobile-faq-icon-vector-frequently-158402237.jpg");
        mNames.add("Purchase Ticket\n" +
                "For purchase tickets, please refer the Ticket purchase in Homepage");
        mImageUrls.add("https://thumbs.dreamstime.com/z/faq-icon-vector-frequently-illustration-symbol-information-sign-logo-web-sites-mobile-faq-icon-vector-frequently-158402237.jpg");
        mNames.add("View Ticket\n" +
                "For View tickets, please refer the View Tickets in Homepage");
        mImageUrls.add("https://thumbs.dreamstime.com/z/faq-icon-vector-frequently-illustration-symbol-information-sign-logo-web-sites-mobile-faq-icon-vector-frequently-158402237.jpg");
        mNames.add("Train Schedule\n" +
                "For Train schedule, Please Refer the View Schedule in Homepage");
        mImageUrls.add("https://thumbs.dreamstime.com/z/faq-icon-vector-frequently-illustration-symbol-information-sign-logo-web-sites-mobile-faq-icon-vector-frequently-158402237.jpg");
        mNames.add("Trip Reminder\n" +
                "In Trip Reminder, It will remind the upcoming Trips");
        mImageUrls.add("https://thumbs.dreamstime.com/z/faq-icon-vector-frequently-illustration-symbol-information-sign-logo-web-sites-mobile-faq-icon-vector-frequently-158402237.jpg");
        mNames.add("Recent Payments\n" +
                "In Recent Payments, You will be able to see the payments that you have done recently.");
        mImageUrls.add("https://thumbs.dreamstime.com/z/faq-icon-vector-frequently-illustration-symbol-information-sign-logo-web-sites-mobile-faq-icon-vector-frequently-158402237.jpg");
        mNames.add("User Profile\n" +
                "You can Delete, Update account details from User Profile in Homepage");
        mImageUrls.add("https://thumbs.dreamstime.com/z/faq-icon-vector-frequently-illustration-symbol-information-sign-logo-web-sites-mobile-faq-icon-vector-frequently-158402237.jpg");
        mNames.add ("Click the link to share the app among friends in User Profile");
        mImageUrls.add("https://thumbs.dreamstime.com/z/faq-icon-vector-frequently-illustration-symbol-information-sign-logo-web-sites-mobile-faq-icon-vector-frequently-158402237.jpg");
        mNames.add ("If you forget the password, click forgot password in the Login page");

        initRecyclerView();
    }
    private  void initRecyclerView(){
        Log.d(TAG, "initRecycleView: started");
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mNames,mImageUrls,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
