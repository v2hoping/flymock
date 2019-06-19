package com.hoping.owl;

import com.hoping.owl.starter.FlyMockProperties;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by houping wang on 2019/4/24
 *
 * @author houping wang
 */
public class ClassAspectTest extends Base{

//    @Autowired
//    private FlyMockProperties flyMockProperties;

    @Autowired
    private ClassAspect classAspect;

    @Test
    public void test() {
        Integer hello = classAspect.hello();
    }
}
