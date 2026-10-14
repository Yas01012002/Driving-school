package com.example.auto_cole;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Candidat extends AppCompatActivity {
CardView plannings,planninge,pquiz,pcour,presult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidat);
        pquiz=findViewById(R.id.pquiz);
        pquiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pq= new Intent(Candidat.this,quiz.class);
                startActivity(pq);
            }
        });
        pcour=findViewById(R.id.pcour);
        pcour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cour=new Intent(Candidat.this,preparation.class);
                startActivity(cour);
            }
        });
        CardView pseance= findViewById(R.id.sceance);
        pseance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent seance=new Intent(Candidat.this,Planningcourcandidat.class);
                startActivity(seance);
            }
        });
        CardView pexamen = findViewById(R.id.pexam);
        pexamen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pexam=new Intent(Candidat.this,Planningexamencandidat.class);
                startActivity(pexam);
            }
        });
     CardView presult=findViewById(R.id.presult);
     presult.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent prest= new Intent(Candidat.this,Resultatexmcand.class);
             startActivity(prest);
         }
     });
    }

    public void clickLogout(View view) {
        SharedPrefManager.getInstance(this).logout();

        final Intent kkk= new Intent(Candidat.this,MainActivity2.class);
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle("Voulez vous se déconnecter?");
        builder.setPositiveButton("Oui",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(kkk);
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