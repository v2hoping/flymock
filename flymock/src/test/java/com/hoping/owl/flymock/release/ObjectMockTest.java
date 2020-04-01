package com.hoping.owl.flymock.release;

import com.alibaba.fastjson.JSON;
import com.hoping.owl.flymock.TypeReference;
import com.hoping.owl.flymock.model.UserT;
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
 * Created by houping wang on 2019/4/13
 *
 * @author houping wang
 */
public class ObjectMockTest {

    @Test
    public void test() {
        printTest(new TypeReference<Boolean>() {});
        printTest(new TypeReference<Character>() {});
        printTest(new TypeReference<Byte>() {});
        printTest(new TypeReference<Short>() {});
        printTest(new TypeReference<Integer>() {});
        printTest(new TypeReference<Long>() {});
        printTest(new TypeReference<Float>() {});
        printTest(new TypeReference<Double>() {});
        printTest(new TypeReference<PlaceholderHandle>() {});
        printTest(new TypeReference<String>() {});
        printTest(new TypeReference<StrategyType>() {});
        printTest(new TypeReference<BigInteger>() {});
        printTest(new TypeReference<BigDecimal>() {});
        printTest(new TypeReference<BigInteger>() {});
        printTest(new TypeReference<Timestamp>() {});
        printTest(new TypeReference<Date>() {});
        printTest(new TypeReference<Calendar>() {});
        printTest(new TypeReference<String[]>() {});
        printTest(new TypeReference<Integer[]>() {});
        printTest(new TypeReference<List<String>>() {});
        printTest(new TypeReference<List<Integer>>() {});
        printTest(new TypeReference<Set<String>>() {});
        printTest(new TypeReference<Set<Integer>>() {});
        printTest(new TypeReference<ArrayList<String>>() {});
        printTest(new TypeReference<Map<String, Integer>>() {});
        printTest(new TypeReference<Map<String, List<String>>>() {});
        printTest(new TypeReference<Map<String, Map<String,String>>>() {});
        printTest(new TypeReference<UserT<String,Integer>>() {});
    }

    /**
     * 循环嵌套
     */
    @Test
    public void test1() {
        printTest(new TypeReference<Nesting>(){});
    }

    /**
     * 互相嵌套
     */
    @Test
    public void test2() {
        printTest(new TypeReference<NestingA>(){});
    }

    private void printTest(TypeReference<?> type) {
        Template<?> template = TemplateMock.template(type);
        Object o = template.mockType();
        System.out.println("模板---" + template.getOriginTemplate());
        System.out.println("结果---" + JSON.toJSONString(o));
    }

    public static class Nesting {

        private String name;

        private Nesting parent;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Nesting getParent() {
            return parent;
        }

        public void setParent(Nesting parent) {
            this.parent = parent;
        }
    }

    public static class NestingA {

        private String name;

        private NestingB nestingB;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public NestingB getNestingB() {
            return nestingB;
        }

        public void setNestingB(NestingB nestingB) {
            this.nestingB = nestingB;
        }
    }

    public static class NestingB {

        private String name;

        private NestingA nestingA;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public NestingA getNestingA() {
            return nestingA;
        }

        public void setNestingA(NestingA nestingA) {
            this.nestingA = nestingA;
        }
    }
}
