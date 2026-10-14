package com.example.auto_cole;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Planningmoniteurcour extends AppCompatActivity {

    Button ajout;
    ProgressDialog dialog;
    private static final String URL_LISTplang = "http://172.22.192.1/MyApi1/apii.php";
    RecyclerView recyclerView;
    PlngcAdapter adapter;
    List<Plngc> PlgncList;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planningmoniteurcour);
        PlgncList = new ArrayList<>();
        ajout=findViewById(R.id.ajoutercour);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadcandidat();


        ajout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n1 = new Intent(Planningmoniteurcour.this,Enrgstcour.class);
                startActivity(n1);

            }
        });

    }
    private void loadcandidat(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_LISTplang,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray plg = new JSONArray(response);

                            for(int i = 0; i<plg.length(); i++){

                                JSONObject plgJSONObject = plg.getJSONObject(i);

                                int id = plgJSONObject.getInt("id");
                                String nom = plgJSONObject.getString("nom");
                                String prenom = plgJSONObject.getString("prenom");
                                String date = plgJSONObject.getString("date");
                                String heure = plgJSONObject.getString("heure");
                                String type = plgJSONObject.getString("type");


                                 Plngc candidats1 = new Plngc(id,nom,prenom, date,heure,type);
                                PlgncList.add(candidats1);

                            }
                            adapter = new PlngcAdapter(Planningmoniteurcour.this, PlgncList);
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Planningmoniteurcour.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        Volley.newRequestQueue(this).add(stringRequest);

    }
}