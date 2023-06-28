package org.example.mst;

public class Pair {
    private int first;
    private int second;

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public Pair() {
    }

    @Override
    public String toString() {
        return "("+this.getFirst() +", "+this.getSecond()+")";
    }
}
