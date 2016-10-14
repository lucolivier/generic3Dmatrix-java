package com.company;

import java.util.ArrayList;
import java.util.List;

class Matrix3DExceptions extends Exception {
    Matrix3DExceptions() {}
    Matrix3DExceptions(String message) { super(message); }
}
class M3DE_OutOfRange extends Matrix3DExceptions {}
class M3DE_BadObjectType extends Matrix3DExceptions {}

class ErrorBunch {
    Exception exception;
    String message;
    ErrorBunch(Exception exception, String message) {
        this.exception = exception; this.message = message;
    }
    public String toString() {
        return "ERR: "+exception+" \n"+message;
    }
}

class Matrix3D<T> {
    private int xs, ys, zs;
    private List<T> matrix = new ArrayList<>();
    Matrix3D(int xs, int ys, int zs) {
        this.xs = (xs == 0 ? 1 : xs);
        this.ys = (ys == 0 ? 1 : ys);
        this.zs = (zs == 0 ? 1 : zs);
        for (int i = 0; i< xs*ys*zs; i++) {
            matrix.add(null);
        }
    }
    public String toString() { return "["+xs+","+ys+","+zs+"]"; }
    public void printAll() {
        for (int z = 0; z<xs; z++) {
            for (int y = 0; y<ys; y++) {
                for (int x = 0; x<zs; x++) {
                    System.out.println(x+","+y+","+z+": "+matrix.get(  x+(y*xs)+(z*xs*ys)  ));
                }
            }
        }
    }
    ErrorBunch err = null;
    private boolean isValidCell(int x, int y, int z) throws M3DE_OutOfRange {
        if (x >= 0 && x < xs && y >= 0 && y < ys && z >= 0 && z < zs) { return true; }
        throw new M3DE_OutOfRange();
    }
    T get(int x, int y, int z) {
        err = null;
        try {
            if (isValidCell(x,y,z)) { return matrix.get( x+(y*xs)+(z*xs*ys) ); }
        } catch (Exception e) {
            err = new ErrorBunch(e, "while setting x: "+x+" y: "+y+" z: "+z);
        }
        return null;
    }
    void set(int x, int y, int z, T value) {
        err = null;
        try {
            if (isValidCell(x,y,z)) { matrix.set( x+(y*xs)+(z*xs*ys) , value); }
        } catch (Exception e) {
            err = new ErrorBunch(e, "while setting x: "+x+" y: "+y+" z: "+z+" to value: "+value);
        }
    }
}


public class Main {

    static void _l() { _p("----------------------"); }
    static void _p(Object obj) { System.out.println(obj); }

    public static void main(String[] args) {

        Matrix3D<Integer> m1 = new Matrix3D<Integer>(3,3,3);

        _p(m1.toString());

        //m1.printAll();

        m1.set(0,0,0,1);
        if (m1.err != null) { _p(">1- "+m1.err); }

        m1.set(2,2,0,1);
        m1.set(0,0,1,1);
        m1.set(2,2,2,1);

        m1.printAll();

//        _p("2- "+m1.get(0,0,0));
//        if (m1.err != null) { _p(">2- "+m1.err); }
//
//        m1.set(2,2,1,13456790);
//        if (m1.err != null) { _p(">3- "+m1.err); }
//
//        _p("4- "+m1.get(2,2,1));
//        if (m1.err != null) { _p(">4- "+m1.err); }
//        _p("5- "+m1.get(2,2,0));
//        if (m1.err != null) { _p(">5- "+m1.err); }
//        m1.set(2,2,0,-1);
//        if (m1.err != null) { _p(">6- "+m1.err); }
//
//        _p("7- "+m1.get(2,2,0));
//        if (m1.err != null) { _p(">7- "+m1.err); }
//
//        m1.set(2,2,0,null);
//        if (m1.err != null) { _p(">8- "+m1.err); }
//
//        _p("9- "+m1.get(2,2,0));
//        if (m1.err != null) { _p(">9- "+m1.err); }
//
//        _p("10- "+m1.get(2,1,0));
//        if (m1.err != null) { _p(">10- "+m1.err); }

    }
}
