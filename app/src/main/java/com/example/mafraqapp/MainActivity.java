package com.example.mafraqapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView rec;
    List<city> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arrayList = new ArrayList<>();
        rec = findViewById(R.id.rec);
        rec.setItemAnimator(new DefaultItemAnimator());
        rec.setHasFixedSize(true);
        rec.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        FirebaseDatabase.getInstance().getReference().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                   arrayList.add( new city(ds.getKey(),"",ds.child("desc").getValue().toString(),ds.child("lat").getValue().toString(),ds.child("lng").getValue().toString()));
                }
                Citys_adapter citys_adapter = new Citys_adapter(arrayList,MainActivity.this);
                rec.setAdapter(citys_adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
