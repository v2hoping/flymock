package com.hoping.owl.flymock.placeholder.handle;

import com.hoping.owl.flymock.FlyRandom;
import com.hoping.owl.flymock.placeholder.AbstractPlaceholderHandle;
import com.hoping.owl.flymock.placeholder.PlaceholderHandle;
import com.hoping.owl.flymock.placeholder.PlaceholderWrap;
import com.hoping.owl.flymock.util.ArrayUtil;

import java.util.List;

/**
 * Created by houping wang on 2019/4/15
 *
 * @author houping wang
 */
public class TimeStampHandle extends AbstractPlaceholderHandle<Long> {
    @Override
    public Long invoke(PlaceholderWrap placeholderWrap) {
        List<String> args = placeholderWrap.getArgs();
        if(ArrayUtil.isEmpty(args)) {
            return FlyRandom.timeStamp();
        }
        return FlyRandom.timeStamp();
    }

    @Override
    public String key() {
        return "timestamp";
    }
}
