package com.adalaachref.iot4kids.model;

import java.util.Map;

/**
 * Created by dell on 25/11/2017.
 */

public class Solution {

    private int idSolution;
    private Map<Integer,Block> mb;

    public Solution(int idSolution, Map<Integer, Block> mb) {
        this.idSolution = idSolution;
        this.mb = mb;
    }

    public int getIdSolution() {
        return idSolution;
    }

    public void setIdSolution(int idSolution) {
        this.idSolution = idSolution;
    }

    public Map<Integer, Block> getMb() {
        return mb;
    }

    public void setMb(Map<Integer, Block> mb) {
        this.mb = mb;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Solution solution = (Solution) o;

        if (idSolution != solution.idSolution) return false;
        return mb != null ? mb.equals(solution.mb) : solution.mb == null;

    }

    @Override
    public int hashCode() {
        int result = idSolution;
        result = 31 * result + (mb != null ? mb.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Solution{" +
                "idSolution=" + idSolution +
                ", mb=" + mb +
                '}';
    }
}
