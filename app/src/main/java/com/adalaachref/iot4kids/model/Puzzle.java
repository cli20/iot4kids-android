package com.adalaachref.iot4kids.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dell on 12/11/2017.
 */

public class Puzzle {
   private int idPuzzle;
    private int idLevel;
    private String Carte;
    private String completed;
  private Map<Position,Component> content;

    public Puzzle() {
        this.content = new HashMap<>();
    }

    public Puzzle(int idPuzzle, Map<Position, Component> content) {
      this.idPuzzle = idPuzzle;
      this.content =content;

   }

   public int getIdPuzzle() {
      return idPuzzle;
   }

   public void setIdPuzzle(int idPuzzle) {
      this.idPuzzle = idPuzzle;
   }

   public Map<Position, Component> getContent() {
      return content;
   }

   public void setContent(Map<Position, Component> content) {
      this.content = content;

   }

    public int getIdLevel() {
        return idLevel;
    }

    public void setIdLevel(int idLevel) {
        this.idLevel = idLevel;
    }

    public String getCarte() {
        return Carte;
    }

    public void setCarte(String carte) {
        Carte = carte;
    }

    public String getCompleted() {
        return completed;
    }

    public void setCompleted(String completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "Puzzle{" +
                "idPuzzle=" + idPuzzle +
                ", idLevel=" + idLevel +
                ", Carte='" + Carte + '\'' +
                ", completed='" + completed + '\'' +
                ", content=" + content +
                '}';
    }
}
