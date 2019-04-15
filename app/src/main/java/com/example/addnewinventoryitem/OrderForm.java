package com.example.addnewinventoryitem;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class OrderForm extends AppCompatActivity implements Serializable {


   EditText etDate, etQuantity;
   Button btnSubmit;
   FirebaseDatabase database;
   DatabaseReference reff;
   String date, itemName, selectedItem, spinnerItem, date2;
   Spinner spinner;
   Integer quantity;
   DatePickerDialog.OnDateSetListener dateSetListener;
   Integer count = 0;
   ListView viewOrder;
   ArrayList<Order> list;
   OrderAdapter adapter;
   Boolean itemExists;
   DataSnapshot snapshot;
   String key;
    ArrayList<Order> dates2;
    Boolean sumbitClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_form);


        dates2 = new ArrayList<Order>();


        list = new ArrayList<Order>();
        viewOrder = findViewById(R.id.lvListView);
        adapter = new OrderAdapter(this, list);

        database = FirebaseDatabase.getInstance();
        reff = database.getReference("NewOrders");

        reff.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {




                    Order post = dataSnapshot.getValue(Order.class);
                    // System.out.println(post.getItemName() + post.getItemQuantity());

                    list.add(post);


                    viewOrder.setAdapter(adapter);
                }

                @Override
                public void onChildChanged (@NonNull DataSnapshot dataSnapshot, @Nullable String s){

                }

                @Override
                public void onChildRemoved (@NonNull DataSnapshot dataSnapshot){

                }

                @Override
                public void onChildMoved (@NonNull DataSnapshot dataSnapshot, @Nullable String s){

                }

                @Override
                public void onCancelled (@NonNull DatabaseError databaseError){

                }





});



        viewOrder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {

               Order orders = adapter.getItem(i);
                Intent intent = new Intent(OrderForm.this, ViewOrderInfo.class);
                intent.putExtra("object", orders);
                startActivity(intent);







            }
        });



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {







            }
        });

    }
}































