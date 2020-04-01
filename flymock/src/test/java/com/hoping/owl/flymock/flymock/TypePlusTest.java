package com.hoping.owl.flymock.flymock;

import com.alibaba.fastjson.JSON;
import com.hoping.owl.flymock.TypeReference;
import com.hoping.owl.flymock.model.UserT;
import com.hoping.owl.flymock.object.Template;
import com.hoping.owl.flymock.object.TemplateMock;
import org.junit.Test;

/**
 * Created by houping wang on 2019/11/15
 *
 * @author houping wang
 */
public class TypePlusTest {

    @Test
    public void test() {
        String json = TemplateMock.json(new TypeReference<UserT<String, Integer>>(){});
        UserT<String, Integer> mock = TemplateMock.mock(new TypeReference<UserT<String, Integer>>(){});
        Template<UserT<String, Integer>> template = TemplateMock.template(new TypeReference<UserT<String, Integer>>(){});
        UserT<String, Integer> stringIntegerUserT = template.mockType();
    }

    @Test
    public void test1() {

    }
}
