package com.adalaachref.iot4kids.model;

/**
 * Created by Maroua on 17-12-04.
 */

public class Score {
    int id;
    int score;
    int id_user;

    public int getId() {
        return id;
    }

    public int setId(int id) {
        this.id = id;
        return id;
    }

    public int getScore() {
        return score;
    }

    public int setScore(int score) {
        this.score = score;
        return score;
    }

    public int getId_user() {
        return id_user;
    }

    public int setId_user(int id_user) {
        this.id_user = id_user;
        return id_user;
    }

    public Score() {
    }

    public Score(int id, int score, int id_user) {
        this.id = id;
        this.score = score;
        this.id_user = id_user;
    }

    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", score=" + score +
                ", id_user=" + id_user +
                '}';
    }
}
