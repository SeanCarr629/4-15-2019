package com.example.addnewinventoryitem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.ArrayList;

public class ViewOrderInfo extends AppCompatActivity implements Serializable {


    ListView orderInfo;
    ProductAdapter adapter2;
    DatabaseReference reff;
    FirebaseDatabase database;
    ArrayList<Inventory> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order_info);

        Intent i = getIntent();
        Order order = (Order)i.getSerializableExtra("object");





        orderInfo = (ListView)findViewById(R.id.lvOrderInfo);


        adapter2 = new ProductAdapter(this, order.getInventory());

        database = FirebaseDatabase.getInstance();
        reff = database.getReference("Inventory");

        orderInfo.setAdapter(adapter2);




    }
}
