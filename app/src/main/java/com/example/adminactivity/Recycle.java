package com.example.adminactivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Recycle extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Pojo>arrayList;
    Dbmanager dbmanager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);
        recyclerView=findViewById(R.id.review);

        dbmanager=new Dbmanager(getApplicationContext());
        dbmanager.open();
        arrayList=dbmanager.fetchall();


        Recycleadapter recycleadapter=new Recycleadapter(Recycle.this,arrayList);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(Recycle.this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recycleadapter);
    }
}
