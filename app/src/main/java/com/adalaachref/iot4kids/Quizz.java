package com.adalaachref.iot4kids;

/**
 * Created by Maroua on 17-12-04.
 */

public class Quizz  {
    int id;

    String nom;
    String categorie;
    String question;
    String reponse;
    Integer id_niveau;
    public Quizz() {
    }

    public Quizz(int id, String nom, String categorie, String question, String reponse, Integer id_niveau) {
        this.id = id;
        this.nom = nom;
        this.categorie = categorie;
        this.question = question;
        this.reponse = reponse;
        this.id_niveau = id_niveau;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getQuestion(String question) {
        return this.question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public Integer getId_niveau() {
        return id_niveau;
    }

    public void setId_niveau(Integer id_niveau) {
        this.id_niveau = id_niveau;
    }

    @Override
    public String toString() {
        return "Quizz{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", categorie='" + categorie + '\'' +
                ", question='" + question + '\'' +
                ", reponse='" + reponse + '\'' +
                ", id_niveau=" + id_niveau +
                '}';
    }
}


