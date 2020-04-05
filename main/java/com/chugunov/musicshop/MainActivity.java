package com.chugunov.musicshop;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    int quatity = 0;
    Spinner spinner;
    ArrayList spinnerArrayList;
    ArrayAdapter spinnerAdapter;
    HashMap goodsMap;
    String goodsName;
    double price;
    EditText userNameEdiText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNameEdiText = findViewById(R.id.namEditText);

        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        spinnerArrayList = new ArrayList();

        spinnerArrayList.add("guitar");
        spinnerArrayList.add("drums");
        spinnerArrayList.add("keyboard");

        spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerArrayList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        goodsMap = new HashMap();

        goodsMap.put("guitar", 500.0);
        goodsMap.put("drums", 1500.0);
        goodsMap.put("keyboard", 1000.0);

    }



    public void increaseQuantity(View view) {
        quatity = quatity + 1;
        TextView quantityTextView = findViewById(R.id.quantityTextView);
        quantityTextView.setText("" + quatity);
        TextView priceTextView = findViewById(R.id.priceTextView);
        priceTextView.setText("" + quatity * price);
    }

    public void decreaseQuantity(View view) {
        quatity = quatity - 1;
        if (quatity < 0) {
            quatity = 0;
        }
        TextView quantityTextView = findViewById(R.id.quantityTextView);
        quantityTextView.setText("" + quatity);
        TextView priceTextView = findViewById(R.id.priceTextView);
        priceTextView.setText("" + quatity * price);



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        goodsName = spinner.getSelectedItem().toString();
        price = (double) goodsMap.get(goodsName);
        TextView priceTextView = findViewById(R.id.priceTextView);
        priceTextView.setText("" + quatity * price);

        ImageView goodsImageView = findViewById(R.id.goodsImageView);

        if (goodsName.equals("guitar")) {
            goodsImageView.setImageResource(R.drawable.electric_guitar);
        }
            else if (goodsName.equals("drums")) {
            goodsImageView.setImageResource(R.drawable.drums);
        }
        else if (goodsName.equals("keyboard")) {
            goodsImageView.setImageResource(R.drawable.keyboard);
        }


        }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void addToCart(View view) {

        Order order = new Order();
        order.userName = userNameEdiText.getText().toString();

        order.goodsName = goodsName;

        order.quantity = quatity;

        order.price = price;

        order.orderPrice = quatity * price;

        Intent orderIntent = new Intent(MainActivity.this, OderActivity.class);
        orderIntent.putExtra("userNameForIntent", order.userName);
        orderIntent.putExtra("goodsName", order.goodsName);
        orderIntent.putExtra("quantity", order.quantity);
        orderIntent.putExtra("price", order.price);
        orderIntent.putExtra("orderPrice", order.orderPrice);

        startActivity(orderIntent);

    }
}
