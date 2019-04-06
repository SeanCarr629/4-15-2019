package com.example.addnewinventoryitem;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ViewInventory extends AppCompatActivity {

    ListView viewInventory;
    DatabaseReference reff;
    FirebaseDatabase database;
    ArrayList<Inventory> list = new ArrayList<>();
    ArrayAdapter<Inventory> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_inventory);


        viewInventory = (ListView) findViewById(R.id.ListView);



        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1);

        database = FirebaseDatabase.getInstance();
        reff = database.getReference("Inventory");



        reff.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Inventory post = dataSnapshot.getValue(Inventory.class);
               // System.out.println(post.getItemName() + post.getItemQuantity());

                adapter.add(String.format( post.getItemName() + "\n " + post.getItemQuantity()));


                viewInventory.setAdapter(adapter);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }
}