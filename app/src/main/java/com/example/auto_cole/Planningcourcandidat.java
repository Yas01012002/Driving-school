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

public class Planningcourcandidat extends AppCompatActivity {
    private static final String URL_LISTplang = "http://172.22.192.1/MyApi1/apii.php";
    RecyclerView recyclerView;
    PlngcconAdapter adapter;
    List<Plngc> PlgncconList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planningcourcandidat);
        PlgncconList = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView4);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadcandidat();

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
                                PlgncconList.add(candidats1);

                            }
                            adapter = new PlngcconAdapter(Planningcourcandidat.this, PlgncconList);
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Planningcourcandidat.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        Volley.newRequestQueue(this).add(stringRequest);

    }
}
