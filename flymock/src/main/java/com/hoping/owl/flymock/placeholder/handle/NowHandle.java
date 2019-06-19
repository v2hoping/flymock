package com.hoping.owl.flymock.placeholder.handle;

import com.hoping.owl.flymock.FlyRandom;
import com.hoping.owl.flymock.placeholder.AbstractPlaceholderHandle;
import com.hoping.owl.flymock.placeholder.PlaceholderHandle;
import com.hoping.owl.flymock.placeholder.PlaceholderWrap;
import com.hoping.owl.flymock.util.ArrayUtil;

import java.util.Date;
import java.util.List;

/**
 * Created by houping wang on 2019/4/15
 *
 * @author houping wang
 */
public class NowHandle extends AbstractPlaceholderHandle<Date> {
    @Override
    public Date invoke(PlaceholderWrap placeholderWrap) {
        List<String> args = placeholderWrap.getArgs();
        if(ArrayUtil.isEmpty(args)) {
            return FlyRandom.now();
        }
        if(args.size() == 1) {
            return FlyRandom.now(args.get(0));
        }
        return FlyRandom.now();
    }

    @Override
    public String key() {
        return "now";
    }
}
