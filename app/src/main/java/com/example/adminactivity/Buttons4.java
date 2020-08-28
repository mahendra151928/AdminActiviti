package com.example.adminactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Buttons4 extends AppCompatActivity {

    Button add,show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buttons4);
        add=findViewById(R.id.addstu);
        show=findViewById(R.id.showstu);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Buttons4.this,Register.class);
                startActivity(intent);
            }
        });
    }
}
