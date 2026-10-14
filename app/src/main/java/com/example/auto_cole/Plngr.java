package com.example.auto_cole;

public class Plngr {
    private int id_examen;
     private final String type_examen;
        private String nomcon;
        private String prenomcon;
        private String resultat;

    public String getType_examen() {
        return type_examen;
    }

    public String getNomcon() {
        return nomcon;
    }

    public void setNomcon(String nomcon) {
        this.nomcon = nomcon;
    }

    public String getPrenomcon() {
        return prenomcon;
    }

    public void setPrenomcon(String prenomcon) {
        this.prenomcon = prenomcon;
    }

    public String getResultat() {
        return resultat;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
    }

    public int getId_examen() {
        return id_examen;
    }

    public void setId_examen(int id_examen) {
        this.id_examen = id_examen;
    }

    public Plngr(int id_examen, String type_examen, String nomcon, String prenomcon, String resultat) {
        this.id_examen=id_examen;
        this.type_examen = type_examen;
        this.nomcon = nomcon;
        this.prenomcon = prenomcon;
        this.resultat = resultat;
    }
}
