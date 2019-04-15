package com.example.addnewinventoryitem;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ViewInventory extends AppCompatActivity {

    ListView viewInventory;
    DatabaseReference reff;
    FirebaseDatabase database;
    ArrayList<Inventory> list = new ArrayList<Inventory>();
    EditText etItemNumber;
    ProductAdapter adapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_inventory);


        viewInventory = (ListView) findViewById(R.id.ListView);


        //   final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1);

       adapter2 = new ProductAdapter(this, list);

        database = FirebaseDatabase.getInstance();
        reff = database.getReference("Inventory");


        reff.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Inventory post = dataSnapshot.getValue(Inventory.class);
                // System.out.println(post.getItemName() + post.getItemQuantity());

                list.add(post);


                viewInventory.setAdapter(adapter2);


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


        viewInventory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, final int position, long arg3) {


                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        ViewInventory.this);

                // set title


                final EditText input = new EditText(ViewInventory.this);
                input.setHeight(100);
                input.setWidth(340);
                input.setGravity(Gravity.LEFT);
                input.setInputType(InputType.TYPE_CLASS_NUMBER);

                input.setImeOptions(EditorInfo.IME_ACTION_DONE);
                alertDialogBuilder.setView(input);

                // set dialog message
              /*  alertDialogBuilder
                        .setMessage("Click yes to exit!")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // if this button is clicked, close
                                // current activity
                                ViewInventory.this.finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // if this button is clicked, just close
                                // the dialog box and do nothing
                                dialog.cancel();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create(); */



                LayoutInflater inflater = getLayoutInflater();
                 View dialoglayout = inflater.inflate(R.layout.dialog_inventoryedit, null);
                final AlertDialog.Builder builder = new AlertDialog.Builder(ViewInventory.this);
                builder.setView(dialoglayout);
              final  AlertDialog dialog = builder.create();
               etItemNumber = dialoglayout.findViewById(R.id.etItemNumber);

                dialog.show();

                Button btnSubmitQuan = (Button) dialoglayout.findViewById( R.id.btnSumbitQuan );
                Button btnExit = (Button) dialoglayout.findViewById(R.id.btnExit);
                Button btnDelete = (Button) dialoglayout.findViewById(R.id.btnDelete);

                btnSubmitQuan.setOnClickListener(new View.OnClickListener(){


                    @Override
                    public void onClick(View v) {

                        final Integer quantity = Integer.parseInt(etItemNumber.getText().toString());

                        reff.addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                                Inventory post = dataSnapshot.getValue(Inventory.class);
                                // System.out.println(post.getItemName() + post.getItemQuantity());

                                if(post.itemName == list.get(position).itemName)
                                {
                                    post.setItemQuantity(quantity);
                                    reff.child(dataSnapshot.getKey()).setValue(post);
                                    adapter2.notifyDataSetChanged();
                                }


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










                      /*  Integer quantity = Integer.parseInt(etItemNumber.getText().toString());

                        list.get(position).setItemQuantity(quantity);

                        Toast.makeText(ViewInventory.this, "Item Updated",
                                Toast.LENGTH_LONG).show(); */

                    }

                });

                btnExit.setOnClickListener(new View.OnClickListener(){


                    @Override
                    public void onClick(View v) {

                        dialog.dismiss();



                        reff.addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                                Inventory post = dataSnapshot.getValue(Inventory.class);
                                // System.out.println(post.getItemName() + post.getItemQuantity());

                                list.add(post);
                                viewInventory.setAdapter(adapter2);





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
                        adapter2.refreshEvents(list);


                    }



                });



                btnDelete.setOnClickListener(new View.OnClickListener(){


                    @Override
                    public void onClick(View v) {



                        reff.addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                                Inventory post = dataSnapshot.getValue(Inventory.class);
                                // System.out.println(post.getItemName() + post.getItemQuantity());

                                if(post.itemName == list.get(position).itemName)
                                {
                                    dataSnapshot.getRef().removeValue();
                                    adapter2.notifyDataSetChanged();
                                }


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
                });







                // show it
               // alertDialog.show();


               // TextView msgTxt = (TextView) alertDialog.findViewById(android.R.id.message);
               // msgTxt.setTextSize(40);
            }
        });


    }


}


