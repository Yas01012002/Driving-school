package com.example.auto_cole;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

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
import java.util.List;
import java.util.Map;

public class CondidatAdapter extends RecyclerView.Adapter<CondidatAdapter.CondidatViewHolder> {
    private Context mctx;
    private List<Candidats> condidatsList;

    public CondidatAdapter(Context mctx, List<Candidats> condidatsList) {
        this.mctx = mctx;
        this.condidatsList = condidatsList;
    }

    @NonNull
    @Override
    public CondidatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mctx);
        View view=inflater.inflate(R.layout.list_layout,null);
        return new CondidatViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CondidatViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Candidats condidats=condidatsList.get(position);
        holder.id.setText("ID:"+"   "+String.valueOf(condidats.getID()));
        holder.username.setText("Username:"+"    "+condidats.getUsername());
        holder.password.setText("Password:"+"    "+condidats.getPassword());
        holder.nom.setText("Nom:"+"    "+condidats.getNom());
        holder.prenom.setText("Prénom:"+"    "+condidats.getPrénom());
        holder.sexe.setText("Sexe:"+"     "+condidats.getSexe());
        holder.date_nai.setText("date de naissance:"+"      "+condidats.getDate_nai());
        holder.lieu_nai.setText("lieu de naissance:"+"    "+condidats.getLieu_nai());
        holder.adress.setText("Adresse:"+"           "+condidats.getAddress());
        holder.numTel.setText("Num de telephone:"+"  "+condidats.getNumTel());
        holder.mon_pay.setText("Montant payé:"+"     "+condidats.getMont_payé());
        holder.mon_res.setText("Montant restant:"+"  "+condidats.getMont_restant());
        holder.Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater=LayoutInflater.from(mctx);
                View editLayout=inflater.inflate(R.layout.edituser,null);
                EditText ID=editLayout.findViewById(R.id.idd);
                EditText username=editLayout.findViewById(R.id.idusername);
                EditText password=editLayout.findViewById(R.id.idpassword);
                EditText nom=editLayout.findViewById(R.id.idnom);
                EditText prenom=editLayout.findViewById(R.id.idprenom);
                EditText sexe=editLayout.findViewById(R.id.idsexe);
                EditText address=editLayout.findViewById(R.id.address);
                EditText date_nai=editLayout.findViewById(R.id.iddatenai);
                EditText lieu_nai=editLayout.findViewById(R.id.idlieunai);
                EditText numTel=editLayout.findViewById(R.id.NumTel);
                EditText monta_res=editLayout.findViewById(R.id.idrestant);
                EditText mon_paye=editLayout.findViewById(R.id.idpaye);


                ID.setText(String.valueOf(condidats.getID()));
                username.setText(condidats.getUsername());
                password.setText(condidats.getPassword());
                nom.setText(condidats.getNom());
                prenom.setText(condidats.getPrénom());
                sexe.setText(condidats.getSexe());
                address.setText(condidats.getAddress());
                date_nai.setText(condidats.getDate_nai());
                lieu_nai.setText(condidats.getLieu_nai());
                numTel.setText(condidats.getNumTel());
                mon_paye.setText(condidats.getMont_payé());
                monta_res.setText(condidats.getMont_restant());




                AlertDialog.Builder builder=new AlertDialog.Builder(mctx);
                builder.setTitle("Modifier "+condidats.getNom()+" "+condidats.getPrénom());
                builder.setView(editLayout);
                builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        final int Id = Integer.parseInt(ID.getText().toString());
                        final String Username = username.getText().toString();
                        final String Password = password.getText().toString();
                        final String Nom = nom.getText().toString();
                        final String Prénom = prenom.getText().toString();
                        final String Date_nai =date_nai.getText().toString();
                        final String Lieu_nai = lieu_nai.getText().toString();
                        final String Sexe = sexe.getText().toString();
                        final String Address =address.getText().toString();
                        final String NumTel = numTel.getText().toString();
                        final String Mont_payé = mon_paye.getText().toString();
                        final String Mont_restant = monta_res.getText().toString();
                        if(Username.isEmpty() || Password.isEmpty()|| Nom.isEmpty()|| Prénom.isEmpty()|| Date_nai.isEmpty()|| Lieu_nai.isEmpty()|| Sexe.isEmpty()|| Address.isEmpty()||NumTel.isEmpty()||Mont_payé.isEmpty()||Mont_restant.isEmpty()){
                            Toast.makeText(mctx,"Some feilds are empty", Toast.LENGTH_SHORT).show();
                        }else{
                            StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_EDIT,
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            Toast.makeText(mctx,response,Toast.LENGTH_SHORT).show();

                                        }
                                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(mctx,error.toString(),Toast.LENGTH_SHORT).show();
                                }
                            }){
                                @Nullable
                                @Override
                                protected Map<String, String> getParams() throws AuthFailureError {
                                    HashMap<String,String> params =new HashMap<>();
                                    params.put("ID", String.valueOf(Id));
                                    params.put("username",Username);
                                    params.put("password",Password);
                                    params.put("Nom",Nom);
                                    params.put("Prénom",Prénom);
                                    params.put("date_nai",Date_nai);
                                    params.put("lieu_nai",Lieu_nai);
                                    params.put("sexe",Sexe);
                                    params.put("Address", Address);
                                    params.put("NumTel",NumTel);
                                    params.put("Mont_payé",Mont_payé);
                                    params.put("Mont_restant",Mont_restant);

                                    return params;

                                }
                            };
                            RequestQueue queue= Volley.newRequestQueue(mctx);
                            queue.add(stringRequest);
                        }
                    }
                });
                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog dialog= builder.create();
                dialog.show();


            }
        });
        holder.supp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(mctx);
                builder.setTitle("DELETE Condidtas");
                builder.setMessage("conform to Delete"+condidats.getNom()+" "+condidats.getPrénom());
                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        StringRequest stringRequest=new StringRequest(Request.Method.POST, Constants.URL_DELETE  ,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            JSONObject object = new JSONObject(response);
                                            String check=object.getString("message");
                                            if(check.equals("User deleted successfully")){
                                                Delete(position);
                                                Toast.makeText(mctx,"User deleted successfully",Toast.LENGTH_SHORT).show();
                                            }else {
                                                Toast.makeText(mctx,response,Toast.LENGTH_SHORT).show();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(mctx,error.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }){
                            @Nullable
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                HashMap<String,String> deleteParms =new HashMap<>();
                                deleteParms.put("ID", String.valueOf(condidats.getID()));
                                return deleteParms;
                            }
                        };
                        RequestQueue requestQueue=Volley.newRequestQueue(mctx);
                        requestQueue.add(stringRequest);
                    }
                });
                AlertDialog dialog=builder.create();
                dialog.show();
            }
        });


    }


    @Override
    public int getItemCount() {
        return condidatsList.size();
    }

    class CondidatViewHolder extends RecyclerView.ViewHolder{
        ImageButton Edit,supp;
        TextView id,username,password,nom,prenom,sexe,date_nai,lieu_nai,adress,numTel,mon_pay,mon_res;
        public CondidatViewHolder(@NonNull View itemView) {
            super(itemView);
            username=itemView.findViewById(R.id.idusername);
            password=itemView.findViewById(R.id.idpassword);
            nom=itemView.findViewById(R.id.idnom);
            prenom=itemView.findViewById(R.id.idprenom);
            sexe=itemView.findViewById(R.id.idsexe);
            date_nai=itemView.findViewById(R.id.iddatenai);
            lieu_nai=itemView.findViewById(R.id.idlieunai);
            adress=itemView.findViewById(R.id.address);
            numTel=itemView.findViewById(R.id.NumTel);
            mon_pay=itemView.findViewById(R.id.idpaye);
            mon_res=itemView.findViewById(R.id.idrestant);
            id=itemView.findViewById(R.id.idd);
            Edit=itemView.findViewById(R.id.Edit);
            supp=itemView.findViewById(R.id.supp);

        }
    }
    public void Delete(int item){
        condidatsList.remove(item);
        notifyItemRemoved(item);
    }
}
