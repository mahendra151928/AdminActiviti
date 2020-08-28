package com.example.adminactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class Register extends AppCompatActivity {
    EditText rename,reemail,repass;
    Spinner respn;
    Button resubmit;
    Dbmanager dbmanager;

    ArrayList<String>arrayList=new ArrayList<>(Arrays.asList("Computer","Design","Electrical","Producton"));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        rename=findViewById(R.id.rname);
        reemail=findViewById(R.id.remail);
        repass=findViewById(R.id.rpass);
        respn=findViewById(R.id.rspnr);
        resubmit=findViewById(R.id.rsub);
        ArrayAdapter<String>arrayAdapter=new ArrayAdapter<>(Register.this,android.R.layout.simple_list_item_1,arrayList);
        respn.setAdapter(arrayAdapter);

        dbmanager=new Dbmanager(getApplicationContext());
        dbmanager.open();

        resubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String regname=rename.getText().toString();
                String regemail=reemail.getText().toString();
                String regpass=repass.getText().toString();


                Pojo pojo=new Pojo();
                pojo.setAname(regname);
                pojo.setAemail(regemail);
                pojo.setApass(regpass);
                dbmanager.insert(pojo);

                if (regname.equals("")||regemail.equals("")||regpass.equals("")){
                    Toast.makeText(Register.this, "All fields are required", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent=new Intent(Register.this,Recycle.class);
                    startActivity(intent);
                    Toast.makeText(Register.this, "Data successfully register", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
