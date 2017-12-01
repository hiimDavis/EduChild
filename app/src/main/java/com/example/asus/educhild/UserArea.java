package com.example.asus.educhild;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class UserArea extends AppCompatActivity {

    private DatabaseReference mDatabase=FirebaseDatabase.getInstance().getReference();
    DatabaseReference mParName = mDatabase.child("01");
    DatabaseReference mParPhone = mDatabase.child("02");
    DatabaseReference mParEmail = mDatabase.child("03");
    DatabaseReference mKidName = mDatabase.child("04");
    DatabaseReference mKidClass = mDatabase.child("05");
    DatabaseReference mKidAge = mDatabase.child("06");
    final TextView parname = (TextView) findViewById(R.id.parname);
    final TextView parphone = (TextView) findViewById(R.id.parphone);
    final TextView parmail = (TextView) findViewById(R.id.parmail);
    final TextView kidname = (TextView) findViewById(R.id.kidname);
    final TextView kidclass = (TextView) findViewById(R.id.kidclass);
    final TextView kidage = (TextView) findViewById(R.id.kidage);
    final ImageButton KID = (ImageButton) findViewById(R.id.kidPIC);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        KID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kidIntent = new Intent(UserArea.this, kidprofile.class);
                UserArea.this.startActivity(kidIntent);
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();

        mParName.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                parname.setText(text);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mParPhone.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                parphone.setText(text);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mParEmail.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                parmail.setText(text);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mKidName.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                kidname.setText(text);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mKidClass.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                kidclass.setText(text);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mKidAge.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                kidage.setText(text);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
