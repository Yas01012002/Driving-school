package com.example.auto_cole;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class ListUser extends AppCompatActivity {
    private static final String URL_LIST = "http://172.22.192.1/MyApi/api.php";
    RecyclerView recyclerView;
    CondidatAdapter adapter;
    List<Candidats> candidatsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);
        candidatsList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadcandidat();

    }
    private void loadcandidat(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_LIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray candidats = new JSONArray(response);

                            for(int i = 0; i<candidats.length(); i++){

                                JSONObject candidatsobject = candidats.getJSONObject(i);
                                int ID = candidatsobject.getInt("ID");
                                String username = candidatsobject.getString("username");
                                String password = candidatsobject.getString("password");
                                String Nom = candidatsobject.getString("Nom");
                                String Prénom = candidatsobject.getString("Prénom");
                                String date_nai = candidatsobject.getString("date_nai");
                                String lieu_nai = candidatsobject.getString("lieu_nai");
                                String sexe = candidatsobject.getString("sexe");
                                String Address = candidatsobject.getString("Address");
                                String NumTel = candidatsobject.getString("NumTel");
                                String Mont_payé = candidatsobject.getString("Mont_payé");
                                String Mont_restant = candidatsobject.getString("Mont_restant");

                                Candidats candidats1 = new Candidats( ID,NumTel, Mont_payé,Mont_restant,username,password,
                                        Nom , Prénom,date_nai,lieu_nai,sexe,  Address);
                                candidatsList.add(candidats1);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapter = new CondidatAdapter(ListUser.this, candidatsList);
                        recyclerView.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ListUser.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        Volley.newRequestQueue(this).add(stringRequest);

    }
}