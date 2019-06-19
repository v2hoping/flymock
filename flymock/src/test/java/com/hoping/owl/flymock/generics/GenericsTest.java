package com.hoping.owl.flymock.generics;

import com.hoping.owl.flymock.Mock;
import com.hoping.owl.flymock.TypeReference;
import com.hoping.owl.flymock.strategy.StrategyWrapper;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.text.MessageFormat;
import java.util.List;

/**
 * Created by houping wang on 2019/3/8
 *
 * @author houping wang
 */
public class GenericsTest {

    private Logger logger = null;

    private static final Logger LOGGER = LoggerFactory.getLogger(GenericsTest.class);

    public static String hello(String... strs) {
        Class<? extends String[]> aClass = strs.getClass();
        System.out.println(aClass);
        return null;
    }

    /**
     * 测试FastJson泛型解析
     */
    @Test
    public void test() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        hello("111");
        String message = MessageFormat.format("三,{0}，小明", "你");
        System.out.println("测试---" + message);
        Class class1 = String.class;
        Method[] methods = class1.getMethods();
        BigInteger a = new BigInteger("11111");
        Integer i = a.intValue();
//        Generics<String> generics = new Generics<>();
//        generics.setValue("111");
//        generics.setNum(222);
//        String genericsJson = JSON.toJSONString(generics);
//        Generics<String> generics1 = JSON.parseObject(genericsJson, new TypeReference<Generics<String>>(){});
//        TypeReference<Generics<String>> typeReference = new TypeReference<Generics<String>>() {};
//        ParameterizedType a = (ParameterizedType)typeReference.getType();
//        Type rawType = a.getRawType();
//        Class<?> b = (Class<?>) rawType;
//        Object o = b.newInstance();
//        Generics<String> o1 = (Generics<String>) o;
    }

    //    @Test
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, InvocationTargetException {
        String message = MessageFormat.format("今天,{1},{0},小明", "你", "好");
//        System.out.println("测试---" + message);
    }

    @Test
    public void test1() {
//        Object mock = Mock.mock("[{\"name|2\":\"@boolean()\",\"url\":\"http://www.google.com@boolean()\"},{\"name|3\":\"Baidu\",\"url\":\"http://www.baidu.com\"},{\"name|4\":\"SoSo\",\"url\":\"http://www.SoSo.com\"}]");
//        System.out.println(mock);

        Object mock = Mock.mock("@natural(1,9)");
        System.out.println(mock);
    }

    @Test
    public void test2() {
        System.out.println(new StrategyWrapper("1-10"));
        System.out.println(new StrategyWrapper("6"));
        System.out.println(new StrategyWrapper("1-2.3-4"));
        System.out.println(new StrategyWrapper("2-3.5"));
        System.out.println(new StrategyWrapper("2.3-4"));
        System.out.println(new StrategyWrapper("5.6"));
        System.out.println(new StrategyWrapper("+2"));
        System.out.println(new StrategyWrapper("+++++"));
    }

    @Test
    public void test3() {
        //{"name|1-5":"BeJson","url":"http://www.bejson.com","page|+12":88,"isNonProfit|1":true,"address|1-2":{"street":"科技园路.","city":"江苏苏州","country":"中国"}}
        Object mock = Mock.mock("{\"name|2\":\"BeJson@boolean()\",\"url\":\"@user()13124141\",\"page|+12\":88,\"isNonProfit|1\":true,\"address|1-2\":{\"street\":\"科技园路.\",\"city\":\"江苏苏州\",\"country\":\"中国\"}}");
        System.out.println(mock);
    }

    @Test
    public void test7() {
        Object a = "{\"a\":\"@integer(0,127)\",\"t\":\"@string()\",\"k\":\"@integer(0,127)\"}";
        Object mock = Mock.mock(a);
        System.out.println(mock);
    }

    @Test
    public void test4() {
//        List<String> a = new ArrayList(){};
//        TypeReference<List<String>> a= new TypeReference<List<String>>(){};
        Object mock = Mock.mock("@boolean()");
        System.out.println(mock);
    }

    @Test
    public void test5() {
        TypeReference<List<String>> typeReference = new TypeReference<List<String>>() {};
    }

}
