package com.group_project_1_x;

//used for pairs of data (such as percents and value name)
public class Pair<F,S> {
    public F f;
    public S s;
    public Pair(F f, S s){
        this.f = f;
        this.s = s;
    }
    public F getF(){ return f; }
    public S getS(){ return s; }
    public void setF(F f){ this.f = f; }
    public void setS(S s){ this.s = s; }
}