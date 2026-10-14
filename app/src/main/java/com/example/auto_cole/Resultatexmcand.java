package com.example.auto_cole;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
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

public class Resultatexmcand extends AppCompatActivity {
    private static final String URL_LISTplangr = "http://172.22.192.1/mmyapi/mmyapi.php";
    RecyclerView recyclerView;
    PlngrcandAdapter adapter;
    List<Plngr> PlgnrcandList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultatexmcand);
        PlgnrcandList = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView5);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadcandidat();
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
                                PlgnrcandList.add(candidats1);

                            }
                            adapter = new PlngrcandAdapter(Resultatexmcand.this, PlgnrcandList);
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Resultatexmcand.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        Volley.newRequestQueue(this).add(stringRequest);

    }
}