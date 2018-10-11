package com.adalaachref.iot4kids.model;

/**
 * Created by dell on 25/11/2017.
 */

public class Block {

    private int idBlock;
    private String nom;

    public Block(int idBlock, String nom) {
        this.idBlock = idBlock;
        this.nom = nom;
    }

    public int getIdBlock() {
        return idBlock;
    }

    public void setIdBlock(int idBlock) {
        this.idBlock = idBlock;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Block block = (Block) o;

        if (idBlock != block.idBlock) return false;
        return nom != null ? nom.equals(block.nom) : block.nom == null;

    }

    @Override
    public int hashCode() {
        int result = idBlock;
        result = 31 * result + (nom != null ? nom.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Block{" +
                "idBlock=" + idBlock +
                ", nom='" + nom + '\'' +
                '}';
    }
}
