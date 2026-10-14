package com.example.auto_cole;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class preparation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preparation);
       CardView code= findViewById(R.id.code1);
        code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b1=new Intent(preparation.this,Code.class);
                startActivity(b1);
            }
        });
        CardView creneau=findViewById(R.id.creneau1);
        creneau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent crne = new Intent(preparation.this,Creneau.class);
                startActivity(crne);

            }
        });
        CardView circul = findViewById(R.id.circulation1);
        circul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cir = new Intent(preparation.this,Circulation.class);
                startActivity(cir);
            }
        });
    }
}