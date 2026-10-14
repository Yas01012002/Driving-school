package com.example.auto_cole;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlngrcandAdapter  extends RecyclerView.Adapter<PlngrcandAdapter.PlngrcandAdapterViewHolder> {
    private Context mctx;
    private List<Plngr> PlgnrcandList;

    public PlngrcandAdapter(Context mctx, List<Plngr> PlgnrcandList) {
        this.mctx = mctx;
        this.PlgnrcandList = PlgnrcandList;
    }

    @NonNull
    @Override
    public PlngrcandAdapter.PlngrcandAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mctx);
        View view = inflater.inflate(R.layout.list_plngrcand, null);
        return new PlngrcandAdapter.PlngrcandAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlngrcandAdapter.PlngrcandAdapterViewHolder holder, int position) {
        Plngr plngr = PlgnrcandList.get(position);
        holder.type.setText("type:"+" "+plngr.getType_examen());
        holder.nom.setText("nom:"+" "+plngr.getNomcon());
        holder.prenom.setText("prenom:"+plngr.getPrenomcon());

        holder.resultat.setText("resultat:"+" "+plngr.getResultat());
    }





    @Override
    public int getItemCount() {
        return PlgnrcandList.size();
    }

    class PlngrcandAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView nom, prenom, resultat, type;

        public PlngrcandAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            resultat = itemView.findViewById(R.id.idresultat4);
            nom = itemView.findViewById(R.id.idnom4);
            prenom = itemView.findViewById(R.id.idprenom4);
            type= itemView.findViewById(R.id.idtype4);

        }
    }
}
