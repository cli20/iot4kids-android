package com.adalaachref.iot4kids.model;

import java.util.List;

/**
 * Created by dell on 25/11/2017.
 */

public class Program {
    private  int idApp;
    private  int idNiveau;
    private String nom;
    private List<Solution> ls;

    public Program(int idNiveau, String nom, List<Solution> ls) {

        this.idNiveau = idNiveau;
        this.nom = nom;
        this.ls = ls;
    }

    public int getIdApp() {
        return idApp;
    }

    public void setIdApp(int idApp) {
        this.idApp = idApp;
    }

    public int getIdNiveau() {
        return idNiveau;
    }

    public void setIdNiveau(int idNiveau) {
        this.idNiveau = idNiveau;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Solution> getLs() {
        return ls;
    }

    public void setLs(List<Solution> ls) {
        this.ls = ls;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Program that = (Program) o;

        if (idApp != that.idApp) return false;
        if (idNiveau != that.idNiveau) return false;
        if (nom != null ? !nom.equals(that.nom) : that.nom != null) return false;
        return ls != null ? ls.equals(that.ls) : that.ls == null;

    }

    @Override
    public int hashCode() {
        int result = idApp;
        result = 31 * result + idNiveau;
        result = 31 * result + (nom != null ? nom.hashCode() : 0);
        result = 31 * result + (ls != null ? ls.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Program{" +
                "idApp=" + idApp +
                ", idNiveau=" + idNiveau +
                ", nom='" + nom + '\'' +
                ", ls=" + ls +
                '}';
    }
}
