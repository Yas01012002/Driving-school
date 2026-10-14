package com.example.auto_cole;

public class Candidats {
    private int ID;
    private String username,password,Nom,Prénom,date_nai,lieu_nai,sexe,address,NumTel,Mont_payé,Mont_restant;

    public Candidats(int ID, String numTel, String mont_payé, String mont_restant, String username, String password, String nom, String prénom, String date_nai, String lieu_nai, String sexe, String address) {
        this.ID = ID;
        NumTel = numTel;
        Mont_payé = mont_payé;
        Mont_restant = mont_restant;
        this.username = username;
        this.password = password;
        Nom = nom;
        Prénom = prénom;
        this.date_nai = date_nai;
        this.lieu_nai = lieu_nai;
        this.sexe = sexe;
        this.address = address;
    }

    public int getID() {
        return ID;
    }

    public String getNumTel() {
        return NumTel;
    }

    public String getMont_payé() {
        return Mont_payé;
    }

    public String getMont_restant() {
        return Mont_restant;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNom() {
        return Nom;
    }

    public String getPrénom() {
        return Prénom;
    }

    public String getDate_nai() {
        return date_nai;
    }

    public String getLieu_nai() {
        return lieu_nai;
    }

    public String getSexe() {
        return sexe;
    }

    public String getAddress() {
        return address;
    }
}
