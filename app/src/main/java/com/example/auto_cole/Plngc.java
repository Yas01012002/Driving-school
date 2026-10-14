package com.example.auto_cole;

public class Plngc {
   private int id;
    private final String type;
    private String nom;
    private String prenom;
    private String date;
    private String heure;

    public String getType() {
        return type;    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Plngc(int id, String nom, String prenom, String date, String heure, String type) {
         this.id=id;
        this.nom = nom;
        this.prenom = prenom;
        this.date = date;
        this.heure = heure;
        this.type = type;

    }

}
