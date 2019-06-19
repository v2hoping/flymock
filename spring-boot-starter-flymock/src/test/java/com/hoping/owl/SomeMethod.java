package com.hoping.owl;

import com.hoping.owl.starter.model.FlyMock;
import org.springframework.stereotype.Component;

/**
 * Created by houping wang on 2019/5/10
 *
 * @author houping wang
 */
@Component
@FlyMock
public class SomeMethod {

    public String sayString() {
        return null;
    }

    public User sayUser() {
        return null;
    }
}
