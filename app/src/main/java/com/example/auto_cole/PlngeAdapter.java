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

public class PlngeAdapter extends RecyclerView.Adapter<PlngeAdapter.PlngeAdapterViewHolder> {
    private Context mctx;
    private List<Plnge> PlgneList;

    public PlngeAdapter(Context mctx, List<Plnge> PlgneList) {
        this.mctx = mctx;
        this.PlgneList = PlgneList;
    }

    @NonNull
    @Override
    public PlngeAdapter.PlngeAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mctx);
        View view = inflater.inflate(R.layout.list_layoutplanninge, null);
        return new PlngeAdapter.PlngeAdapterViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull PlngeAdapter.PlngeAdapterViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Plnge plnge = PlgneList.get(position);
        holder.id.setText("ID:"+"   "+String.valueOf(plnge.getId()));

        holder.nom.setText("nom:"+" "+plnge.getNom());
        holder.prenom.setText("prenom:"+plnge.getPrenom());

        holder.date.setText("date:"+" "+plnge.getDate());
        holder.heure.setText("heure:"+" "+plnge.getHeure());
        holder.type.setText("type:"+" "+plnge.getType());

        holder.Edit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                LayoutInflater inflater = LayoutInflater.from(mctx);
                View editLayout = inflater.inflate(R.layout.editplanninge, null);
                @SuppressLint({"MissingInflatedId", "LocalSuppress"}) EditText ID=editLayout.findViewById(R.id.idce);
                EditText Nom = editLayout.findViewById(R.id.Nomce);
                EditText Prenom = editLayout.findViewById(R.id.Prenomce);
                EditText date = editLayout.findViewById(R.id.datece);
                EditText heure = editLayout.findViewById(R.id.heurece);
                EditText type = editLayout.findViewById(R.id.typece);

                ID.setText(String.valueOf(plnge.getId()));
                Nom.setText(plnge.getNom());
                Prenom.setText(plnge.getPrenom());
                date.setText(plnge.getDate());
                heure.setText(plnge.getHeure());
                type.setText(plnge.getType());
                AlertDialog.Builder builder = new AlertDialog.Builder(mctx);
                builder.setTitle("Modifier " + plnge.getNom() + " " + plnge.getPrenom());
                builder.setView(editLayout);
                builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        final int IDD = Integer.parseInt(ID.getText().toString());
                        final String nom = Nom.getText().toString();
                        final String prenom = Prenom.getText().toString();
                        final String Date = date.getText().toString();
                        final String Heure = heure.getText().toString();
                        final String Type = type.getText().toString();

                        if (nom.isEmpty() || prenom.isEmpty() || Date.isEmpty() || Heure.isEmpty() || Type.isEmpty()) {
                            Toast.makeText(mctx, "Some feilds are empty", Toast.LENGTH_SHORT).show();
                        } else {
                            StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_EDITPLANNINGE,
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            Toast.makeText(mctx, response, Toast.LENGTH_SHORT).show();

                                        }
                                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(mctx, error.toString(), Toast.LENGTH_SHORT).show();
                                }
                            }) {
                                @Nullable
                                @Override
                                protected Map<String, String> getParams() throws AuthFailureError {
                                    HashMap<String, String> params = new HashMap<>();
                                    params.put("id", String.valueOf(IDD));
                                    params.put("nom", nom);
                                    params.put("prenom", prenom);
                                    params.put("date", Date);
                                    params.put("heure", Heure);
                                    params.put("type", Type);

                                    return params;

                                }
                            };
                            RequestQueue queue = Volley.newRequestQueue(mctx);
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
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        holder.supp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(mctx);
                builder.setTitle("DELETE Condidtas");
                builder.setMessage("conform to Delete"+plnge.getNom()+" "+plnge.getPrenom());
                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        StringRequest stringRequest=new StringRequest(Request.Method.POST, Constants.URL_DELETEPE  ,
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
                                deleteParms.put("id", String.valueOf(plnge.getId()));
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
        return PlgneList.size();
    }

    class PlngeAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView id, nom, prenom, date, heure, type;
        ImageButton Edit,supp;

        public PlngeAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.iddate2);
            heure = itemView.findViewById(R.id.idheure2);
            nom = itemView.findViewById(R.id.idnom2);
            prenom = itemView.findViewById(R.id.idprenom2);
            type = itemView.findViewById(R.id.idtype2);
            id = itemView.findViewById(R.id.id2);
            Edit = itemView.findViewById(R.id.Editexm);
            supp = itemView.findViewById(R.id.supp2);
        }
    }
    public void Delete(int item){
        PlgneList.remove(item);
        notifyItemRemoved(item);
    }
}
