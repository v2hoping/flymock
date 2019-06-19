package com.hoping.owl;

import com.hoping.owl.flymock.placeholder.AbstractPlaceholderHandle;
import com.hoping.owl.flymock.placeholder.PlaceholderHandle;
import com.hoping.owl.flymock.placeholder.PlaceholderWrap;
import com.hoping.owl.flymock.strategy.Strategy;
import org.springframework.stereotype.Component;

/**
 * Created by houping wang on 2019/4/24
 *
 * @author houping wang
 */
@Component
public class TestHandler extends AbstractPlaceholderHandle<String> {
    @Override
    public String invoke(PlaceholderWrap placeholderWrap) {
        return null;
    }

    @Override
    public String key() {
        return "哈哈哈";
    }
}
