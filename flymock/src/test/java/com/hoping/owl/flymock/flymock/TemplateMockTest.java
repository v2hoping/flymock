package com.hoping.owl.flymock.flymock;

import com.alibaba.fastjson.JSON;
import com.hoping.owl.flymock.TypeReference;
import com.hoping.owl.flymock.annotation.FiledMock;
import com.hoping.owl.flymock.object.Template;
import com.hoping.owl.flymock.object.TemplateMock;
import com.hoping.owl.flymock.placeholder.PlaceholderHandle;
import com.hoping.owl.flymock.strategy.StrategyType;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by houping wang on 2019/4/16
 *
 * @author houping wang
 */
public class TemplateMockTest {

    private void printTest(TypeReference<?> type) {
        Template<?> template = TemplateMock.template(type);
        Object o = template.mockType();
        System.out.println("模板---" + template.getOriginTemplate());
        System.out.println("结果---" + o);
        System.out.println("结果JSON---" + JSON.toJSONString(o));
    }

    /**
     * 基本类型转换
     */
    @Test
    public void basic() {
        printTest(new TypeReference<Boolean>() {
        });
        printTest(new TypeReference<Character>() {
        });
        printTest(new TypeReference<Byte>() {
        });
        printTest(new TypeReference<Short>() {
        });
        printTest(new TypeReference<Integer>() {
        });
        printTest(new TypeReference<Long>() {
        });
        printTest(new TypeReference<Float>() {
        });
        printTest(new TypeReference<Double>() {
        });
        printTest(new TypeReference<String>() {
        });
        printTest(new TypeReference<StrategyType>() {
        });
    }

    @Test
    public void bigNumber() {
        printTest(new TypeReference<BigDecimal>() {
        });
        printTest(new TypeReference<BigInteger>() {
        });
    }

    @Test
    public void annotation() {
        printTest(new TypeReference<PlaceholderHandle>() {
        });
    }

    @Test
    public void date() {
        printTest(new TypeReference<Timestamp>() {
        });
        printTest(new TypeReference<Date>() {
        });
        printTest(new TypeReference<Calendar>() {
        });
    }

    @Test
    public void array() {
        printTest(new TypeReference<String[]>() {
        });
        printTest(new TypeReference<Integer[]>() {
        });
        printTest(new TypeReference<Double[]>() {
        });
        printTest(new TypeReference<User[]>() {
        });
        printTest(new TypeReference<User[][]>() {
        });
    }

    @Test
    public void list() {
        printTest(new TypeReference<List<String>>() {
        });
        printTest(new TypeReference<List<Integer>>() {
        });
        printTest(new TypeReference<Set<String>>() {
        });
        printTest(new TypeReference<Set<Integer>>() {
        });
        printTest(new TypeReference<ArrayList<String>>() {
        });
        printTest(new TypeReference<Map<String, Integer>>() {
        });
    }

    /**
     * 多级嵌套集合
     */
    @Test
    public void listNestingT() {
        printTest(new TypeReference<List<List<String>>>() {
        });
        printTest(new TypeReference<List<Set<Map<String, String>>>>() {
        });
    }

    @Test
    public void object() {
        printTest(new TypeReference<User>() {
        });
    }

    /**
     * 复杂嵌套对象
     */
    @Test
    public void objectNesting() {
        printTest(new TypeReference<UserNesting>() {
        });
        printTest(new TypeReference<UserT>() {
        });
        printTest(new TypeReference<UserK>() {
        });
    }

    @Test
    public void annotationTest() {
        printTest(new TypeReference<AnnotationUser>(){});
    }

    @Test
    public void objectNestingT() {
        printTest(new TypeReference<UserT<UserNM<String, Integer>, UserK, Integer, UserK<Integer>>>() {
        });
    }

    @Test
    public void userA() {
        printTest(new TypeReference<UserA<String>>() {
        });
    }

//    public void

    public static class UserA<A> {
        private UserNM<A, A> t;

        public UserNM<A, A> getT() {
            return t;
        }

        public void setT(UserNM<A, A> t) {
            this.t = t;
        }
    }

    private static class UserT<T, K, E, L> {

        private E[][] ls;

        private T t;

        private K k;

        private E e;

        private L l;

        private Map<E, L> map;

        private List<L> list;

        private Set<T> sets;

        public E[][] getLs() {
            return ls;
        }

        public void setLs(E[][] ls) {
            this.ls = ls;
        }

        public Map<E, L> getMap() {
            return map;
        }

        public void setMap(Map<E, L> map) {
            this.map = map;
        }

        public T getT() {
            return t;
        }

        public void setT(T t) {
            this.t = t;
        }

        public K getK() {
            return k;
        }

        public void setK(K k) {
            this.k = k;
        }

        public E getE() {
            return e;
        }

        public void setE(E e) {
            this.e = e;
        }

        public L getL() {
            return l;
        }

        public void setL(L l) {
            this.l = l;
        }

        public Set<T> getSets() {
            return sets;
        }

        public void setSets(Set<T> sets) {
            this.sets = sets;
        }

        public List<L> getList() {
            return list;
        }

        public void setList(List<L> list) {
            this.list = list;
        }
    }

    private static class UserNM<N, M> {
        private N n;

        private M m;

        public N getN() {
            return n;
        }

        public void setN(N n) {
            this.n = n;
        }

        public M getM() {
            return m;
        }

        public void setM(M m) {
            this.m = m;
        }
    }

    private static class UserK<J> {
        private J tQ;

        private User userQ;

        private List<User> listQ;

        private Map<String, User> mapQ;

        private Set<User> setsQ;

        private List<Set<User>> aQ;

        private LinkedHashSet<User> linkedHashSetQ;

        public J gettQ() {
            return tQ;
        }

        public void settQ(J tQ) {
            this.tQ = tQ;
        }

        public User getUserQ() {
            return userQ;
        }

        public void setUserQ(User userQ) {
            this.userQ = userQ;
        }

        public List<User> getListQ() {
            return listQ;
        }

        public void setListQ(List<User> listQ) {
            this.listQ = listQ;
        }

        public Map<String, User> getMapQ() {
            return mapQ;
        }

        public void setMapQ(Map<String, User> mapQ) {
            this.mapQ = mapQ;
        }

        public Set<User> getSetsQ() {
            return setsQ;
        }

        public void setSetsQ(Set<User> setsQ) {
            this.setsQ = setsQ;
        }

        public List<Set<User>> getaQ() {
            return aQ;
        }

        public void setaQ(List<Set<User>> aQ) {
            this.aQ = aQ;
        }

        public LinkedHashSet<User> getLinkedHashSetQ() {
            return linkedHashSetQ;
        }

        public void setLinkedHashSetQ(LinkedHashSet<User> linkedHashSetQ) {
            this.linkedHashSetQ = linkedHashSetQ;
        }
    }

    public static class UserNesting {
        private User user;

        private List<User> list;

        private Map<String, User> map;

        private Set<User> sets;

        private List<Set<User>> a;

        private LinkedHashSet<User> linkedHashSet;

        public List<User> getList() {
            return list;
        }

        public void setList(List<User> list) {
            this.list = list;
        }

        public Map<String, User> getMap() {
            return map;
        }

        public void setMap(Map<String, User> map) {
            this.map = map;
        }

        public Set<User> getSets() {
            return sets;
        }

        public void setSets(Set<User> sets) {
            this.sets = sets;
        }

        public List<Set<User>> getA() {
            return a;
        }

        public void setA(List<Set<User>> a) {
            this.a = a;
        }

        public LinkedHashSet<User> getLinkedHashSet() {
            return linkedHashSet;
        }

        public void setLinkedHashSet(LinkedHashSet<User> linkedHashSet) {
            this.linkedHashSet = linkedHashSet;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }
    }

    /**
     * 简单对象
     */
    public static class User {

        private String name;

        private List<String> list;

        private ArrayList<String> arrayList;

        private LinkedList<String> linkedList;

        private LinkedHashSet<String> linkedHashSet;

        private Set<Integer> set;

        private Map<String, String> map;

        private Double money;

        private Byte abyte;

        private Short aShort;

        private Integer aInteger;

        private Float aFloat;

        private Character character;

        private Boolean aBoolean;

        private Long aLong;

        public ArrayList<String> getArrayList() {
            return arrayList;
        }

        public void setArrayList(ArrayList<String> arrayList) {
            this.arrayList = arrayList;
        }

        public LinkedList<String> getLinkedList() {
            return linkedList;
        }

        public void setLinkedList(LinkedList<String> linkedList) {
            this.linkedList = linkedList;
        }

        public LinkedHashSet<String> getLinkedHashSet() {
            return linkedHashSet;
        }

        public void setLinkedHashSet(LinkedHashSet<String> linkedHashSet) {
            this.linkedHashSet = linkedHashSet;
        }

        public Double getMoney() {
            return money;
        }

        public void setMoney(Double money) {
            this.money = money;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Byte getAbyte() {
            return abyte;
        }

        public void setAbyte(Byte abyte) {
            this.abyte = abyte;
        }

        public Short getaShort() {
            return aShort;
        }

        public void setaShort(Short aShort) {
            this.aShort = aShort;
        }

        public Integer getaInteger() {
            return aInteger;
        }

        public void setaInteger(Integer aInteger) {
            this.aInteger = aInteger;
        }

        public Float getaFloat() {
            return aFloat;
        }

        public void setaFloat(Float aFloat) {
            this.aFloat = aFloat;
        }

        public Character getCharacter() {
            return character;
        }

        public void setCharacter(Character character) {
            this.character = character;
        }

        public Boolean getaBoolean() {
            return aBoolean;
        }

        public void setaBoolean(Boolean aBoolean) {
            this.aBoolean = aBoolean;
        }

        public Long getaLong() {
            return aLong;
        }

        public void setaLong(Long aLong) {
            this.aLong = aLong;
        }

        public List<String> getList() {
            return list;
        }

        public void setList(List<String> list) {
            this.list = list;
        }

        public Set<Integer> getSet() {
            return set;
        }

        public void setSet(Set<Integer> set) {
            this.set = set;
        }

        public Map<String, String> getMap() {
            return map;
        }

        public void setMap(Map<String, String> map) {
            this.map = map;
        }
    }

    public static class AnnotationUser {

        @FiledMock(strategy = "1-5", value = "你好中国")
        private String name;

        @FiledMock(strategy = "2-5", value = "你好中国")
        private List<String> list;

        @FiledMock(strategy = "2")
        private ArrayList<String> arrayList;

        @FiledMock(strategy = "2")
        private LinkedList<String> linkedList;

        @FiledMock(strategy = "2")
        private LinkedHashSet<String> linkedHashSet;

        @FiledMock(strategy = "2")
        private Set<Integer> set;

        private Map<String, String> map;

        @FiledMock(strategy = "10", value = "@float(0,10,3)")
        private Double money;

        @FiledMock(strategy = "10", value = "@integer(100,100)")
        private Byte abyte;

        @FiledMock(strategy = "10", value = "@integer(100,100)")
        private Short aShort;

        @FiledMock(strategy = "10", value = "@integer(100,100)")
        private Integer aInteger;

        @FiledMock(strategy = "10", value = "@integer(100,100)")
        private Float aFloat;

        @FiledMock(strategy = "10", value = "@integer(100,100)")
        private Character character;

        @FiledMock(value = "@boolean(true)")
        private Boolean aBoolean;

        private Long aLong;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<String> getList() {
            return list;
        }

        public void setList(List<String> list) {
            this.list = list;
        }

        public ArrayList<String> getArrayList() {
            return arrayList;
        }

        public void setArrayList(ArrayList<String> arrayList) {
            this.arrayList = arrayList;
        }

        public LinkedList<String> getLinkedList() {
            return linkedList;
        }

        public void setLinkedList(LinkedList<String> linkedList) {
            this.linkedList = linkedList;
        }

        public LinkedHashSet<String> getLinkedHashSet() {
            return linkedHashSet;
        }

        public void setLinkedHashSet(LinkedHashSet<String> linkedHashSet) {
            this.linkedHashSet = linkedHashSet;
        }

        public Set<Integer> getSet() {
            return set;
        }

        public void setSet(Set<Integer> set) {
            this.set = set;
        }

        public Map<String, String> getMap() {
            return map;
        }

        public void setMap(Map<String, String> map) {
            this.map = map;
        }

        public Double getMoney() {
            return money;
        }

        public void setMoney(Double money) {
            this.money = money;
        }

        public Byte getAbyte() {
            return abyte;
        }

        public void setAbyte(Byte abyte) {
            this.abyte = abyte;
        }

        public Short getaShort() {
            return aShort;
        }

        public void setaShort(Short aShort) {
            this.aShort = aShort;
        }

        public Integer getaInteger() {
            return aInteger;
        }

        public void setaInteger(Integer aInteger) {
            this.aInteger = aInteger;
        }

        public Float getaFloat() {
            return aFloat;
        }

        public void setaFloat(Float aFloat) {
            this.aFloat = aFloat;
        }

        public Character getCharacter() {
            return character;
        }

        public void setCharacter(Character character) {
            this.character = character;
        }

        public Boolean getaBoolean() {
            return aBoolean;
        }

        public void setaBoolean(Boolean aBoolean) {
            this.aBoolean = aBoolean;
        }

        public Long getaLong() {
            return aLong;
        }

        public void setaLong(Long aLong) {
            this.aLong = aLong;
        }
    }

}
