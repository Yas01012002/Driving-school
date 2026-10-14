package com.example.auto_cole;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Enrgtresultat extends AppCompatActivity {

    EditText Nom;
    EditText Prenm;
    Spinner Type;
    Button ADDE;
    private RadioGroup gp;
    private ProgressDialog progressDialog;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrgtresultat);
        Nom = findViewById(R.id.nomcon);
        Prenm = findViewById(R.id.prenomcon);
        Type = findViewById(R.id.typecon);
        ADDE = findViewById(R.id.ADDR);
        gp = (RadioGroup) findViewById(R.id.radio_gr);
        ArrayAdapter<String> adapter1= new ArrayAdapter<>(this,R.layout.item_spinner,getResources().getStringArray(R.array.spinnerlist));
        adapter1.setDropDownViewResource(R.layout.mydrapdown);
        Type.setAdapter(adapter1);

        progressDialog = new ProgressDialog(this);
        ADDE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
                finish();
            }
        });
    }

    private void registerUser() {
        final String nomcon = Nom.getText().toString().trim();
        final String prenomcon = Prenm.getText().toString().trim();
        final String type_examen = Type.getSelectedItem().toString().trim();
        int sexeid = gp.getCheckedRadioButtonId();
        RadioButton selected_sexe = gp.findViewById(sexeid);
        final String selectsexe = selected_sexe.getText().toString().trim();

        progressDialog.setTitle("Enregistrement examen ...");
        progressDialog.show();


        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_RES,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        String errorMessage = "Unknown error";
                        if (error != null && error.getMessage() != null) {
                            errorMessage = error.getMessage();
                        } else {
                            errorMessage = error.toString();
                        }
                        Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_LONG).show();
                    }
                }


        ) {
            @Nullable
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("nomcon", nomcon);
                params.put("type_examen", type_examen);
                params.put("prenomcon", prenomcon);
                params.put("resultat",selectsexe);
                return params;
            }


        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}