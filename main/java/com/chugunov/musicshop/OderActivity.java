package com.chugunov.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OderActivity extends AppCompatActivity {

    String[] addresses = {"v.4ugunov@gmail.com"};
    String subject = "Oder From Music Shop";
    String emailText;

    /*String phoneNo;
    String messageText;
    String attachment;
    String smsText;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oder);

        //setTitle("Your Oder");

        Intent receivedOrderIntent = getIntent();
        String userName = receivedOrderIntent.getStringExtra("userNameForIntent");
        String goodsName = receivedOrderIntent.getStringExtra("goodsName");
        int quantity = receivedOrderIntent.getIntExtra("quantity", 0);
        double price = receivedOrderIntent.getDoubleExtra("price", 0);
        double orderPrice = receivedOrderIntent.getDoubleExtra("orderPrice", 0);

        emailText = "Your name: " + userName + "\n" + "Goods name: " + goodsName + "\n" +
                "Quantity: " + quantity + "\n" +
                "Price: " + price + "$" + "\n" +
                "Oder Price: " + orderPrice + "$";

        TextView oderTextView = findViewById(R.id.oderTextView);
        oderTextView.setText(emailText);

        /*smsText = "Your name: " + userName + "\n" + "Goods name: " + goodsName + "\n" +
                "Quantity: " + quantity + "\n" +
                "Price: " + price + "$" + "\n" +
                "Oder Price: " + orderPrice + "$";
        oderTextView = findViewById(R.id.oderTextView);
        oderTextView.setText(smsText);*/


    }

    public void submitOder(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, emailText);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);


        }


    }


    /*public void smsOder(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("smsto:"));  // This ensures only SMS apps respond
        intent.putExtra("sms_body", messageText);
        intent.putExtra(Intent.EXTRA_STREAM, attachment);
        intent.putExtra(Intent.EXTRA_TEXT, smsText);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);*/



}