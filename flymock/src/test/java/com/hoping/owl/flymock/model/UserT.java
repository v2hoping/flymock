package com.hoping.owl.flymock.model;


/**
 * Created by houping wang on 2019/4/12
 *
 * @author houping wang
 */
public class UserT<K,T> {

    private T t;

    private K v;

    private String name;

    public UserT(T t, String name) {
        this.t = t;
        this.name = name;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public K getV() {
        return v;
    }

    public void setV(K v) {
        this.v = v;
    }
}
