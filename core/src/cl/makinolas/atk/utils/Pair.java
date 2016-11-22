package cl.makinolas.atk.utils;

public class Pair<A,B> {

    private A a;
    private B b;

    public static Pair<Integer,Integer> fromInt(int a, int b){
        return new Pair<Integer, Integer>(a,b);
    }

    public Pair(A aa, B bb){
        a = aa;
        b = bb;
    }

    public A getFirst() {
        return a;
    }

    public B getSecond() {
        return b;
    }

}