package com.example.asus.educhild;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class kidprofile extends AppCompatActivity {

    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mKidName = mDatabase.child("04");
    final TextView kidname = (TextView) findViewById(R.id.kidname);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kidprofile);
    }

    @Override
    protected void onStart() {
        super.onStart();
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
    }
}
