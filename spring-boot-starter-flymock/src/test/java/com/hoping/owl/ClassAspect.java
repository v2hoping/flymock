package com.hoping.owl;

import com.hoping.owl.starter.model.FlyMock;
import com.hoping.owl.starter.model.MatchingMode;
import org.springframework.stereotype.Component;

/**
 * Created by houping wang on 2019/4/24
 *
 * @author houping wang
 */
@Component
@FlyMock(matchingMode = MatchingMode.NULL)
public class ClassAspect {

    public Integer hello() {
        return null;
    }
}
