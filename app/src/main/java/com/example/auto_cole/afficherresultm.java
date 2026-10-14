package com.example.auto_cole;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class afficherresultm extends AppCompatActivity {
    Button ajouter;

    ProgressDialog dialog;
    private static final String URL_LISTplangr = "http://172.22.192.1/mmyapi/mmyapi.php";
    RecyclerView recyclerView;
    PlngrAdapter adapter;
    List<Plngr> PlgnrList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficherresultm);
        ajouter= findViewById(R.id.ajouterresult);
        PlgnrList = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView3);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadcandidat();
        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(afficherresultm.this,Enrgtresultat.class);
                startActivity(k);
            }
        });
    }
    private void loadcandidat(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_LISTplangr,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray plg = new JSONArray(response);

                            for(int i = 0; i<plg.length(); i++){

                                JSONObject plgJSONObject = plg.getJSONObject(i);
                                int id = plgJSONObject.getInt("id_examen");
                                String type_examen = plgJSONObject.getString("type_examen");
                                String nomcon = plgJSONObject.getString("nomcon");
                                String prenomcon = plgJSONObject.getString("prenomcon");
                                String resultat = plgJSONObject.getString("resultat");



                                Plngr candidats1 = new Plngr(id,type_examen,nomcon,prenomcon,resultat);
                                PlgnrList.add(candidats1);

                            }
                            adapter = new PlngrAdapter(afficherresultm.this, PlgnrList);
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(afficherresultm.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        Volley.newRequestQueue(this).add(stringRequest);

    }
}