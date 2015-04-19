package com.example.kammy.quiz;

/**
 * Created by damel on 19/04/15.
 */
public class Pair implements Comparable<Pair>{

    private double a;
    private String b;

    public Pair(double aa,String bb){
        a = aa;
        b = bb;
    }

    public double getA(){
        return a;
    }
    public String getB(){
        return b;
    }
    @Override
    public int compareTo(Pair other) {
        return (int)(a - other.getA());
    }

    @Override
    public String toString(){
        return " ("+a+":"+b+") ";
    }
}
