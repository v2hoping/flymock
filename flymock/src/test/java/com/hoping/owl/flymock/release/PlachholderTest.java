package com.hoping.owl.flymock.release;

import com.hoping.owl.flymock.Mock;
import org.junit.Test;

/**
 * Created by houping wang on 2020/3/5
 * 回归测试
 * @author houping wang
 */
public class PlachholderTest {

    //** String **
    @Test
    public void string() {
        mock("{\"name|1-3\":\"@cname()\"}");//ok
        mock("{\"name|2\":\"@cname()\"}");//ok
        mock("{\"name|1-2.1-2\":\"@cname()\"}");
        mock("{\"name|1-3.2\":\"@cname()\"}");
        mock("{\"name|4.1-3\":\"@cname()\"}");
        mock("{\"name|2.2\":\"@cname()\"}");
        mock("{\"name|+3\":\"@cname()\"}");
    }

    //** integer **
    @Test
    public void integer() {
        mock("{\"name|2-3\":1}");//ok
        mock("{\"name|2\":1}");
        mock("{\"name|1-2.1-2\":1}");
        mock("{\"name|3-5.2\":1}");
        mock("{\"name|4.1-3\":1}");
        mock("{\"name|2.2\":1}");
        mock("{\"name|+3\":1}");//ok
    }

    //** float **
    @Test
    public void floatTest() {
        mock("{\"name|2-3\":1.2}");//ok
        mock("{\"name|2\":1.2}");//ok
        mock("{\"name|1-2.1-2\":1.2}");//ok
        mock("{\"name|3-5.2\":1.2}");//ok
        mock("{\"name|4.1-3\":1.2}");//ok
        mock("{\"name|2.2\":1.2}");//ok
        mock("{\"name|+3\":1.2}");//ok
    }

    //** boolean **
    @Test
    public void booleanTest() {
        mock("{\"name|2-3\":true}");//ok
        mock("{\"name|1\":true}");//ok
        mock("{\"name|1-2.1-2\":true}");
        mock("{\"name|3-5.2\":true}");
        mock("{\"name|4.1-3\":true}");
        mock("{\"name|2.2\":true}");
        mock("{\"name|+3\":true}");
    }

    //** object **
    @Test
    public void objectTest() {
        mock("{\"aa|1-2\":{\"name\":\"@cname()\",\"educations\":[{\"date\":1557457171390,\"name\":\"vhJ\"}]}}");//ok
        mock("{\"aa|1\":{\"name\":\"@cname()\",\"educations\":[{\"date\":1557457171390,\"name\":\"vhJ\"}]}}");//ok
        mock("{\"aa|1-2.1-2\":{\"name\":\"@cname()\",\"educations\":[{\"date\":1557457171390,\"name\":\"vhJ\"}]}}");
        mock("{\"aa|3-5.2\":{\"name\":\"@cname()\",\"educations\":[{\"date\":1557457171390,\"name\":\"vhJ\"}]}}");
        mock("{\"aa|4.1-3\":{\"name\":\"@cname()\",\"educations\":[{\"date\":1557457171390,\"name\":\"vhJ\"}]}}");
        mock("{\"aa|2.2\":{\"name\":\"@cname()\",\"educations\":[{\"date\":1557457171390,\"name\":\"vhJ\"}]}}");
        mock("{\"aa|+1\":{\"name\":\"@cname()\",\"educations\":[{\"date\":1557457171390,\"name\":\"vhJ\"}]}}");
    }

    @Test
    public void array() {
        mock("{\"aa|3-5\":[{\"date\":1557457171390,\"name\":\"vhJ\"},{\"date\":111111,\"name\":\"是\"}]}");//ok
        mock("{\"aa|1\":[{\"date\":1557457171390,\"name\":\"vhJ\"},{\"date\":111111,\"name\":\"是\"}]}");//ok
        mock("{\"aa|1-2.1-2\":[{\"date\":1557457171390,\"name\":\"vhJ\"},{\"date\":111111,\"name\":\"是\"}]}");
        mock("{\"aa|3-5.2\":[{\"date\":1557457171390,\"name\":\"vhJ\"},{\"date\":111111,\"name\":\"是\"}]}");
        mock("{\"aa|4.1-3\":[{\"date\":1557457171390,\"name\":\"vhJ\"},{\"date\":111111,\"name\":\"是\"}]}");
        mock("{\"aa|2.2\":[{\"date\":1557457171390,\"name\":\"vhJ\"},{\"date\":111111,\"name\":\"是\"}]}");
        mock("{\"aa|+3\":[{\"date\":1557457171390,\"name\":\"vhJ\"},{\"date\":111111,\"name\":\"是\"}]}");
    }

    private String mock(String template) {
        String mockJson = Mock.mockToJson(template);
        System.out.println(mockJson);
        return mockJson;
    }

}
