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

public class PlngrAdapter  extends RecyclerView.Adapter<PlngrAdapter.PlngrAdapterViewHolder> {
    private Context mctx;
    private List<Plngr> PlgnrList;

    public PlngrAdapter(Context mctx, List<Plngr> PlgnrList) {
        this.mctx = mctx;
        this.PlgnrList = PlgnrList;
    }

    @NonNull
    @Override
    public PlngrAdapter.PlngrAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mctx);
        View view = inflater.inflate(R.layout.layout_plngr, null);
        return new PlngrAdapter.PlngrAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlngrAdapterViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Plngr plngr = PlgnrList.get(position);
        holder.id.setText("ID:" + "   " + String.valueOf(plngr.getId_examen()));
        holder.type.setText("type:" + " " + plngr.getType_examen());
        holder.nom.setText("nom:" + " " + plngr.getNomcon());
        holder.prenom.setText("prenom:" + plngr.getPrenomcon());

        holder.resultat.setText("resultat:" + " " + plngr.getResultat());
        holder.Edit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                LayoutInflater inflater = LayoutInflater.from(mctx);
                View editLayout = inflater.inflate(R.layout.editrsult, null);
                @SuppressLint({"MissingInflatedId", "LocalSuppress"}) EditText ID = editLayout.findViewById(R.id.idcr);
                EditText Nom = editLayout.findViewById(R.id.Nomcr);
                EditText Prenom = editLayout.findViewById(R.id.Prenomcr);
                EditText type = editLayout.findViewById(R.id.typecr);

                EditText resultat = editLayout.findViewById(R.id.resultcr);

                ID.setText(String.valueOf(plngr.getId_examen()));
                Nom.setText(plngr.getNomcon());
                Prenom.setText(plngr.getPrenomcon());
                type.setText(plngr.getType_examen());
                resultat.setText(plngr.getResultat());
                AlertDialog.Builder builder = new AlertDialog.Builder(mctx);
                builder.setTitle("Modifier " + plngr.getNomcon() + " " + plngr.getPrenomcon());
                builder.setView(editLayout);
                builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        final int IDD = Integer.parseInt(ID.getText().toString());
                        final String nom = Nom.getText().toString();
                        final String prenom = Prenom.getText().toString();
                        final String Type = type.getText().toString();
                        final String reslt = resultat.getText().toString();


                        if (nom.isEmpty() || prenom.isEmpty() || Type.isEmpty() || reslt.isEmpty()) {
                            Toast.makeText(mctx, "Some feilds are empty", Toast.LENGTH_SHORT).show();
                        } else {
                            StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_EDITAFF,
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
                                    params.put("id_examen", String.valueOf(IDD));
                                    params.put("type_examen", Type);
                                    params.put("nomcon", nom);

                                    params.put("prenomcon", prenom);
                                    params.put("resultat", reslt);

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
                AlertDialog.Builder builder = new AlertDialog.Builder(mctx);
                builder.setTitle("DELETE Condidtas");
                builder.setMessage("conform to Delete" + plngr.getNomcon() + " " + plngr.getPrenomcon());
                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_DELETER,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            JSONObject object = new JSONObject(response);
                                            String check = object.getString("message");
                                            if (check.equals("User deleted successfully")) {
                                                Delete(position);
                                                Toast.makeText(mctx, "User deleted successfully", Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(mctx, response, Toast.LENGTH_SHORT).show();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(mctx, error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }) {
                            @Nullable
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                HashMap<String, String> deleteParms = new HashMap<>();
                                deleteParms.put("id_examen", String.valueOf(plngr.getId_examen()));
                                return deleteParms;
                            }
                        };
                        RequestQueue requestQueue = Volley.newRequestQueue(mctx);
                        requestQueue.add(stringRequest);
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return PlgnrList.size();
    }

    class PlngrAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView id, nom, prenom, resultat, type;
        ImageButton Edit, supp;

        public PlngrAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            resultat = itemView.findViewById(R.id.idresultat);
            nom = itemView.findViewById(R.id.idnom3);
            prenom = itemView.findViewById(R.id.idprenom3);
            type = itemView.findViewById(R.id.idtype3);
            id = itemView.findViewById(R.id.id3);
            Edit = itemView.findViewById(R.id.Editreslt);
            supp = itemView.findViewById(R.id.supp4);

        }
    }
    public void Delete(int item) {
        PlgnrList.remove(item);
        notifyItemRemoved(item);
    }
}

