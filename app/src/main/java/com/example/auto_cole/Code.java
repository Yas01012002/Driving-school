package com.example.auto_cole;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Code extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);

        CardView pann= findViewById(R.id.panneau);
        pann.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pan= new Intent(Code.this,Panneau.class);
                startActivity(pan);
            }
        });
        CardView inter = findViewById(R.id.intersection);
        inter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intres=new Intent(Code.this,Intersection.class);
                startActivity(intres);
            }
        });


    }
}