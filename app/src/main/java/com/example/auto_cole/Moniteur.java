package com.example.auto_cole;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Moniteur extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moniteur);
        CardView plngcrm= findViewById(R.id.scn);
        plngcrm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent plngcm = new Intent(Moniteur.this, Planningmoniteurcour.class);
                startActivity(plngcm);
            }
        });
        CardView gere = findViewById(R.id.gerer);
        gere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m =new Intent(Moniteur.this,Gerercandidat.class);
                startActivity(m);
            }
        });
        CardView plngexam= findViewById(R.id.pcour1);
        plngexam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent plgexm= new Intent(Moniteur.this,Planningexamenmoniteur.class);
                startActivity(plgexm);
            }
        });
        CardView resultat= findViewById(R.id.resultats);
        resultat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resuly = new Intent(Moniteur.this,afficherresultm.class);
                startActivity(resuly);
            }
        });

    }

    public void clickLogout1(View view) {
        SharedPrefManager.getInstance(this).logout();

        final Intent kk= new Intent(Moniteur.this,MainActivity2.class);
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle("Voulez vous se déconnecter?");
        builder.setPositiveButton("Oui",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(kk);
                    }
                });
        builder.setNegativeButton("Non",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

         builder.show();
    }
}