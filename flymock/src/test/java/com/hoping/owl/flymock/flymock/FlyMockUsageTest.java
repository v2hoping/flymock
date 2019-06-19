package com.hoping.owl.flymock.flymock;

import com.hoping.owl.flymock.Mock;
import com.hoping.owl.flymock.TypeReference;
import com.hoping.owl.flymock.annotation.FiledMock;
import com.hoping.owl.flymock.object.Template;
import com.hoping.owl.flymock.object.TemplateMock;
import com.hoping.owl.flymock.placeholder.AbstractPlaceholderHandle;
import com.hoping.owl.flymock.placeholder.PlaceholderWrap;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * Created by houping wang on 2019/5/9
 * Java类型------>Java模板数据------>JSON数据<------>Java对象
 * Java类型->Java对象 TemplateMock.mock()
 * Java类型->JSON数据 TemplateMock.json()
 * Java类型->Java模板 TemplateMock.template()
 * Java模板数据->JSON数据 Template.mockToJson()
 * JSON数据->Java对象 Template.mockType()
 * @author houping wang
 */
public class FlyMockUsageTest {

    @Test
    public void test() {
        //基本类型
        Integer integerValue = TemplateMock.mock(new TypeReference<Integer>() {});
        //时间
        Date dateValue = TemplateMock.mock(new TypeReference<Date>(){});
        //数组
        Integer[] integerValues = TemplateMock.mock(new TypeReference<Integer[]>() {});
        //复杂对象
        User userValue = TemplateMock.mock(new TypeReference<User>() {});
        //泛型对象
        List<String> listTValues = TemplateMock.mock(new TypeReference<List<String>>() {});
    }

    @Test
    public void test1() {
        String integerJson = TemplateMock.json(new TypeReference<Integer>() {});//1
        String listJson = TemplateMock.json(new TypeReference<List<String>>() {});//[100]
        String userJson = TemplateMock.json(new TypeReference<User>() {});//{"name":"gXB","educations":[{"date":1557405600693,"name":"3)ad"}]}
        System.out.println(userJson);
    }

    @Test
    public void test2() {
        Template<User> template = TemplateMock.template(new TypeReference<User>() {});
    }

    @Test
    public void test3() {
        String infoTemplateJson = "{\"name\":\"@string()\",\"educations\":[{\"date\":1557457171390,\"name\":\"vhJ\"}]}";
        Template<User> template = new Template<>(infoTemplateJson, new TypeReference<User>(){});
        User user = template.mockType();
    }

    static {
        Mock.put(new MoviePlaceholderHandle());
    }

    public static void main(String[] args) {
        Template<User> template = TemplateMock.template(new TypeReference<User>() {});
        User user = template.mockType();
        String userJson = template.mockToJson();
        System.out.println(userJson);
    }


    public static class MoviePlaceholderHandle extends AbstractPlaceholderHandle<String> {

        @Override
        public String invoke(PlaceholderWrap placeholderWrap) {
            List<String> args = placeholderWrap.getArgs();
            if(args.size() == 0) {
                return  "《钢铁侠》";
            }else{
                return  "《钢铁侠" + args.get(0) +"》";
            }
        }

        @Override
        public String key() {
            return "movie";
        }
    }

    public static class User {

        @FiledMock(strategy = "2", value = "电影是:@movie(2)")
        private String name;

        private List<Education> educations;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Education> getEducations() {
            return educations;
        }

        public void setEducations(List<Education> educations) {
            this.educations = educations;
        }

        public static class Education {
            /**
             * 教育名称
             */
            private String name;

            /**
             * 教育时间
             */
            private Date date;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Date getDate() {
                return date;
            }

            public void setDate(Date date) {
                this.date = date;
            }
        }
    }
}
