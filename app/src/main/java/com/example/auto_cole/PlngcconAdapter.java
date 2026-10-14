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

public class PlngcconAdapter extends RecyclerView.Adapter<PlngcconAdapter.PlngcconAdapterViewHolder> {
    private Context mctx;
    private List<Plngc> PlgncconList;

    public PlngcconAdapter(Context mctx, List<Plngc> PlgncconList) {
        this.mctx = mctx;
        this.PlgncconList = PlgncconList;
    }

    @NonNull
    @Override
    public PlngcconAdapter.PlngcconAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mctx);
        View view = inflater.inflate(R.layout.list_planningcourcandidat, null);
        return new PlngcconAdapter.PlngcconAdapterViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull PlngcconAdapter.PlngcconAdapterViewHolder holder, int position) {
        Plngc plngc = PlgncconList.get(position);

        holder.nom.setText("nom:"+" "+plngc.getNom());
        holder.prenom.setText("prenom:"+plngc.getPrenom());

        holder.date.setText("date:"+" "+plngc.getDate());
        holder.heure.setText("heure:"+" "+plngc.getHeure());
        holder.type.setText("type:"+" "+plngc.getType());

    }



    @Override
    public int getItemCount() {
        return PlgncconList.size();
    }

    class PlngcconAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView nom, prenom, date, heure, type;

        public PlngcconAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.iddatecc);
            heure = itemView.findViewById(R.id.idheurecc);
            nom = itemView.findViewById(R.id.idnomcc);
            prenom = itemView.findViewById(R.id.idprenomcc);
            type = itemView.findViewById(R.id.idtypecc);

        }
    }
}


