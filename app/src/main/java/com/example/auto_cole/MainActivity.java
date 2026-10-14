package com.example.auto_cole;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
 ImageView img;
 TextView txt;
 Animation topanimm;
 Animation bottanim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        topanimm= AnimationUtils.loadAnimation(this,R.anim.animation_top);
        bottanim= AnimationUtils.loadAnimation(this,R.anim.animation_bottom);
        img=findViewById(R.id.img);
        img.setAnimation(topanimm);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent l= new Intent(MainActivity.this,MainActivity2.class);
                finish();
                startActivity(l);
            }

        },3000);

    }
}