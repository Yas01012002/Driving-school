package com.example.auto_cole;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlngcxAdapter extends RecyclerView.Adapter<PlngcxAdapter.PlngcxAdapterViewHolder> {
    private Context mctx;
    private List<Plnge> PlgncxList;

    public PlngcxAdapter(Context mctx, List<Plnge> PlgncxList) {
        this.mctx = mctx;
        this.PlgncxList = PlgncxList;
    }

    @NonNull
    @Override
    public PlngcxAdapter.PlngcxAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mctx);
        View view = inflater.inflate(R.layout.list_planningexamencandidat, null);
        return new PlngcxAdapter.PlngcxAdapterViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull PlngcxAdapter.PlngcxAdapterViewHolder holder, int position) {
        Plnge plnge = PlgncxList.get(position);

        holder.nom.setText("nom:"+" "+plnge.getNom());
        holder.prenom.setText("prenom:"+plnge.getPrenom());

        holder.date.setText("date:"+" "+plnge.getDate());
        holder.heure.setText("heure:"+" "+plnge.getHeure());
        holder.type.setText("type:"+" "+plnge.getType());

    }



    @Override
    public int getItemCount() {
        return PlgncxList.size();
    }

    class PlngcxAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView nom, prenom, date, heure, type;

        public PlngcxAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.iddatecx);
            heure = itemView.findViewById(R.id.idheurecx);
            nom = itemView.findViewById(R.id.idnomcx);
            prenom = itemView.findViewById(R.id.idprenomcx);
            type = itemView.findViewById(R.id.idtypecx);

        }
    }
}


