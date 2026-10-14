package com.example.auto_cole;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AjoutCandidat extends AppCompatActivity implements View.OnClickListener {
    private DatePickerDialog datePickerDialog;
    private Button DateButton;
    private EditText editTextUsername,editTextPassword,editTextNom,editTextPrenom,editTextAddress,editTextNumTel, editTextdatenais, editTextlieunais, editTextmontantpayé, editTextmontantrestant;
    private Button buttonRegister;
    private RadioGroup gp;
    private ProgressDialog progressDialog;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_candidat);
        DateButton = findViewById(R.id.datePickernaiss);
        DateButton.setText(getTodaysDate());
        editTextUsername = (EditText) findViewById(R.id.username);
        editTextPassword = (EditText) findViewById(R.id.password);
        editTextNom = (EditText) findViewById(R.id.enom);
        editTextPrenom = (EditText) findViewById(R.id.eprenom);
        editTextAddress = (EditText) findViewById(R.id.adress);
        editTextNumTel = (EditText) findViewById(R.id.tlf);

        editTextlieunais = (EditText) findViewById(R.id.lieun);
        editTextmontantpayé = (EditText) findViewById(R.id.montp);
        editTextmontantrestant = (EditText) findViewById(R.id.montr);
        gp = (RadioGroup) findViewById(R.id.radio_group_gender);
        initDatePicker();
        buttonRegister = (Button) findViewById(R.id.ajoucan);

        progressDialog = new ProgressDialog(this);

        buttonRegister.setOnClickListener(this);
    }

    private void registerUser(){

        final String username = editTextUsername.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        final String Nom = editTextNom.getText().toString().trim();
        final String Prénom = editTextPrenom.getText().toString().trim();
        final String date_nai = DateButton.getText().toString().trim();
        final String lieu_nai = editTextlieunais.getText().toString().trim();
        // final String sexef = femme.getText().toString().trim();
        // final String sexeh = homme.getText().toString().trim();
        int sexeid = gp.getCheckedRadioButtonId();
        RadioButton selected_sexe = gp.findViewById(sexeid);
        final String selectsexe = selected_sexe.getText().toString().trim();
        final String Address = editTextAddress.getText().toString().trim();
        final String NumTel = editTextNumTel.getText().toString().trim();

        final String Mont_payé = editTextmontantpayé.getText().toString().trim();
        final String Mont_restant = editTextmontantrestant.getText().toString().trim();




        progressDialog.setTitle("Registering user ...");
        progressDialog.show();



        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
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


        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);
                params.put("Nom", Nom);
                params.put("Prénom", Prénom);
                params.put("date_nai", date_nai);
                params.put("lieu_nai", lieu_nai);
                params.put("sexe",selectsexe);
                params.put("Address", Address);
                params.put("NumTel", NumTel);
                params.put("Mont_payé", Mont_payé);
                params.put("Mont_restant", Mont_restant);
                return params ;
            }


        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

    }


    @Override
    public void onClick(View view) {
        Intent i = new Intent(AjoutCandidat.this,ListUser.class);
        startActivity(i);
        if (view == buttonRegister)
            registerUser();
    }

    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day, month, year);
                DateButton.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month) {
        if (month == 1)
            return "JAN";
        if (month == 2)
            return "FEB";
        if (month == 3)
            return "MAR";
        if (month == 4)
            return "APR";
        if (month == 5)
            return "MAY";
        if (month == 6)
            return "JUN";
        if (month == 7)
            return "JUL";
        if (month == 8)
            return "AUG";
        if (month == 9)
            return "SEP";
        if (month == 10)
            return "OCT";
        if (month == 11)
            return "NOV";
        if (month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }

    public void openDatePicker(View view) {
        datePickerDialog.setTitle("Date de naissance");
        datePickerDialog.show();}
}