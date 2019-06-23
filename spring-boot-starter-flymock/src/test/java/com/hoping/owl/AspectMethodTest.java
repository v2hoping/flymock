package com.hoping.owl;

import com.alibaba.fastjson.JSON;
import com.hoping.owl.flymock.placeholder.PlaceholderHandle;
import com.hoping.owl.flymock.strategy.StrategyType;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by houping wang on 2019/4/24
 *
 * @author houping wang
 */
public class AspectMethodTest extends Base{

    @Autowired
    private AspectMethod aspectMethod;

    @Autowired
    private SomeMethod someMethod;

    @Autowired
    private SingleMethod singleMethod;

    @Test
    public void hello() {
        List<Integer> hello = aspectMethod.hello("11");
        Assert.assertNotNull(hello);
    }

    @Test
    public void boolean1() {
        Boolean aBoolean = aspectMethod.boolean1();
        Assert.assertNotNull(aBoolean);
    }

    @Test
    public void character() {
        Character character = aspectMethod.character();
        Assert.assertNotNull(character);
    }

    @Test
    public void byte1() {
        Byte aByte = aspectMethod.byte1();
        Assert.assertNotNull(aByte);
    }

    @Test
    public void integer() {
        Integer integer = aspectMethod.integer();
        Assert.assertNotNull(integer);
    }

    @Test
    public void aLong() {
        Long aLong = aspectMethod.aLong();
        Assert.assertNotNull(aLong);
    }

    @Test
    public void aFloat() {
        Float aFloat = aspectMethod.aFloat();
        Assert.assertNotNull(aFloat);
    }

    @Test
    public void aDouble() {
        Double aDouble = aspectMethod.aDouble();
        System.out.println("测试---" + aDouble);
        Assert.assertNotNull(aDouble);
    }

    @Test
    public void placeholderHandle() {
        PlaceholderHandle value = aspectMethod.placeholderHandle();
    }

    @Test
    public void strategyType() {
        StrategyType value = aspectMethod.strategyType();
        Assert.assertNotNull(value);
    }

    @Test
    public void bigInteger() {
        BigInteger value = aspectMethod.bigInteger();
        Assert.assertNotNull(value);
    }

    @Test
    public void bigDecimal() {
        BigDecimal value = aspectMethod.bigDecimal();
        Assert.assertNotNull(value);
    }

    @Test
    public void timestamp() {
        Timestamp timestamp = aspectMethod.timestamp();
        Assert.assertNotNull(timestamp);
    }

    @Test
    public void date() {
        Date date = aspectMethod.date();
        Assert.assertNotNull(date);
    }

    @Test
    public void calendar() {
        Calendar calendar = aspectMethod.calendar();
        Assert.assertNotNull(calendar);
    }

    @Test
    public void strings() {
        String[] strings = aspectMethod.strings();
        Assert.assertNotNull(strings);
    }

    @Test
    public void integers() {
        Integer[] integers = aspectMethod.integers();
        Assert.assertNotNull(integers);
    }

    @Test
    public void doubles() {
        Double[] doubles = aspectMethod.doubles();
        Assert.assertNotNull(doubles);
    }

    @Test
    public void users() {
        AspectMethod.User[] users = aspectMethod.users();
        Assert.assertNotNull(users);
    }

    @Test
    public void userss() {
        AspectMethod.User[][] userss = aspectMethod.userss();
        Assert.assertNotNull(userss);
    }

    @Test
    public void stringList() {
        List<String> strings = aspectMethod.stringList();
        Assert.assertNotNull(strings);
    }

    @Test
    public void integerList() {
        List<Integer> integers = aspectMethod.integerList();
        Assert.assertNotNull(integers);
    }

    @Test
    public void stringSet() {
        Set<String> strings = aspectMethod.stringSet();
        Assert.assertNotNull(strings);
    }

    @Test
    public void integerSet() {
        Set<Integer> integers = aspectMethod.integerSet();
        Assert.assertNotNull(integers);
    }

    @Test
    public void arrayList() {
        ArrayList<String> strings = aspectMethod.arrayList();
        Assert.assertNotNull(strings);
    }

    @Test
    public void map() {
        Map<String, Integer> map = aspectMethod.map();
        Assert.assertNotNull(map);
    }

    @Test
    public void listList() {
        List<List<String>> lists = aspectMethod.listList();
        Assert.assertNotNull(lists);
    }

    @Test
    public void listSetMap() {
        List<Set<Map<String, String>>> sets = aspectMethod.listSetMap();
        Assert.assertNotNull(sets);
    }

    @Test
    public void user() {
        AspectMethod.User user = aspectMethod.user();
        Assert.assertNotNull(user);
    }

    @Test
    public void userNesting() {
        AspectMethod.UserNesting userNesting = aspectMethod.userNesting();
        Assert.assertNotNull(userNesting);
    }

    @Test
    public void userT() {
        AspectMethod.UserT userT = aspectMethod.userT();
        Assert.assertNotNull(userT);
    }

    @Test
    public void userK() {
        AspectMethod.UserK userK = aspectMethod.userK();
        Assert.assertNotNull(userK);
    }

    @Test
    public void userTNM() {
        AspectMethod.UserT<AspectMethod.UserNM<String, Integer>, AspectMethod.UserK, Integer, AspectMethod.UserK<Integer>> userNMUserKIntegerUserKUserT = aspectMethod.userTNM();
        Assert.assertNotNull(userNMUserKIntegerUserKUserT);
    }

    @Test
    public void userA() {
        AspectMethod.UserA<String> stringUserA = aspectMethod.userA();
        Assert.assertNotNull(stringUserA);
    }

    @Test
    public void annotationUser() {
        AspectMethod.AnnotationUser annotationUser = aspectMethod.annotationUser();
        Assert.assertNotNull(annotationUser);
    }

    @Test
    public void sayString() {
        String str = someMethod.sayString();
        User user = someMethod.sayUser();
        System.out.println(str);
        System.out.println(JSON.toJSONString(user));
    }

    @Test
    public void sayUser() {

    }
}