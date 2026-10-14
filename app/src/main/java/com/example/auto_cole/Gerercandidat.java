package com.example.auto_cole;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Gerercandidat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerercandidat);
        CardView ajout =findViewById(R.id.ajout);
        ajout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(Gerercandidat.this,AjoutCandidat.class);
                startActivity(a);
            }
        });
        CardView list = findViewById(R.id.liste);
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b = new Intent(Gerercandidat.this,ListUser.class);
                startActivity(b);
            }
        });
    }
}