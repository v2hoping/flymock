package com.hoping.owl.flymock.flymock;

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
        Template o9 = TemplateMock.template(new TypeReference<String>() {});
        Template o10 = TemplateMock.template(new TypeReference<StrategyType>() {});
        Template o11 = TemplateMock.template(new TypeReference<BigInteger>() {});
        Template o12 = TemplateMock.template(new TypeReference<BigDecimal>() {});
        Template o13 = TemplateMock.template(new TypeReference<BigInteger>() {});
        Template o15 = TemplateMock.template(new TypeReference<Timestamp>() {});
        Template o14 = TemplateMock.template(new TypeReference<Date>() {});
        Template o16 = TemplateMock.template(new TypeReference<Calendar>() {});
        Template o17 = TemplateMock.template(new TypeReference<String[]>() {});
        Template o18 = TemplateMock.template(new TypeReference<Integer[]>() {});
        Template o19 = TemplateMock.template(new TypeReference<List<String>>() {});
        Template o20 = TemplateMock.template(new TypeReference<List<Integer>>() {});
        Template o21 = TemplateMock.template(new TypeReference<Set<String>>() {});
        Template o22 = TemplateMock.template(new TypeReference<Set<Integer>>() {});
        Template o23 = TemplateMock.template(new TypeReference<ArrayList<String>>() {});
        Template o24 = TemplateMock.template(new TypeReference<Map<String, Integer>>() {});
        Template o25 = TemplateMock.template(new TypeReference<Map<String, List<String>>>() {});
        Template o26 = TemplateMock.template(new TypeReference<Map<String, Map<String,String>>>() {});
        Template o27 = TemplateMock.template(new TypeReference<UserT<String,Integer>>() {});
    }

    private void printTest(TypeReference<?> type) {
        Template<?> template = TemplateMock.template(type);
        Object o = template.mockType();
        System.out.println("模板---" + template.getOriginTemplate());
        System.out.println("结果---" + JSON.toJSONString(o));
    }

}
