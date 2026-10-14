package com.example.auto_cole;



import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
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

import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Enrgstcour extends AppCompatActivity {
    EditText Nom;
    EditText Prenm;
    Spinner Type;
    Button ADD;
    private DatePickerDialog datePickerDialog;
    private Button DateButton;

    Button TimeButton;
    int hour, minute;
    private ProgressDialog progressDialog;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrgstcour);
        Nom = findViewById(R.id.nomc);
        Prenm = findViewById(R.id.prenomc);
        Type = findViewById(R.id.typec);
        ADD = findViewById(R.id.ADD);
        initDatePicker();
        DateButton = findViewById(R.id.datePickerButton);
        DateButton.setText(getTodaysDate());
        TimeButton = findViewById(R.id.timeButton);
        progressDialog = new ProgressDialog(this);
        ArrayAdapter<String> adapter1= new ArrayAdapter<>(this,R.layout.item_spinner,getResources().getStringArray(R.array.spinnerlist));
         adapter1.setDropDownViewResource(R.layout.mydrapdown);
         Type.setAdapter(adapter1);



        ADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();


                finish();




            }
        });


    }

    private void registerUser(){
        final String nom = Nom.getText().toString().trim();
        final String prenom = Prenm.getText().toString().trim();
        final String date = DateButton.getText().toString().trim();
        final String heure = TimeButton.getText().toString().trim();
        final String type= Type.getSelectedItem().toString().trim();

        progressDialog.setTitle("Enregistrement séance ...");
        progressDialog.show();


        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_PLANNINGC,
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


        ){
            @Nullable
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("nom", nom);
                params.put("prenom", prenom);
                params.put("date", date);
                params.put("heure", heure);
                params.put("type", type);
                return params ;
            }


        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

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
        datePickerDialog.setTitle("Selectionner date");
        datePickerDialog.show();
    }
    public void popTimePicker(View view)
    {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener()
        {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute)
            {
                hour = selectedHour;
                minute = selectedMinute;
                TimeButton.setText(String.format(Locale.getDefault(), "%02d:%02d",hour, minute));
            }
        };

        // int style = AlertDialog.THEME_HOLO_DARK;

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, /*style,*/ onTimeSetListener, hour, minute, true);

        timePickerDialog.setTitle("Selectionner heure");
        timePickerDialog.show();
    }


}

