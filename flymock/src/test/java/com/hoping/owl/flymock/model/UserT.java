package com.hoping.owl.flymock.model;


/**
 * Created by houping wang on 2019/4/12
 *
 * @author houping wang
 */
public class UserT<K,T> {

    private T t;

    private K aaa;

    private String name;

    public UserT(T t, String name) {
        this.t = t;
        this.name = name;
    }

    public UserT(T t, K aaa, String name) {
        this.t = t;
        this.aaa = aaa;
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

    public K getAaa() {
        return aaa;
    }

    public void setAaa(K aaa) {
        this.aaa = aaa;
    }
}
