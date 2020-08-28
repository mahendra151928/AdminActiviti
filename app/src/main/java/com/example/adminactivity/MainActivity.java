package com.example.adminactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    TextView spntxt;
    Button lgn;
    String name;
    ArrayList<String>arrayList=new ArrayList<>(Arrays.asList("Admin","Faculty"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner=findViewById(R.id.spn);
        spntxt=findViewById(R.id.tname);
        lgn=findViewById(R.id.btnlgn);

        ArrayAdapter<String>arrayAdapter=new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_list_item_1,arrayList);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                  name=adapterView.getItemAtPosition(i).toString();
                 spntxt.setText(name);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

       lgn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if (name.equals("Admin")){
                   Intent intent=new Intent(MainActivity.this,Buttons4.class);
                   startActivity(intent);
                   Toast.makeText(MainActivity.this, "Admin", Toast.LENGTH_SHORT).show();
               }
               else {
                   Toast.makeText(MainActivity.this, "Faculty", Toast.LENGTH_SHORT).show();
               }
           }
       });
    }

}
