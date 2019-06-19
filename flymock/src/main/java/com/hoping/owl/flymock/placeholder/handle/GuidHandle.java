package com.hoping.owl.flymock.placeholder.handle;

import com.hoping.owl.flymock.FlyRandom;
import com.hoping.owl.flymock.placeholder.AbstractPlaceholderHandle;
import com.hoping.owl.flymock.placeholder.PlaceholderHandle;
import com.hoping.owl.flymock.placeholder.PlaceholderWrap;
import com.hoping.owl.flymock.util.ArrayUtil;

import java.util.List;

/**
 * Created by houping wang on 2019/4/16
 *
 * @author houping wang
 */
public class GuidHandle extends AbstractPlaceholderHandle<String> {
    @Override
    public String invoke(PlaceholderWrap placeholderWrap) {
        List<String> args = placeholderWrap.getArgs();
        if(ArrayUtil.isEmpty(args)) {
            return FlyRandom.guid();
        }
        if(args.size() == 1) {
            return FlyRandom.guid(args.get(0));
        }
        return FlyRandom.guid();
    }

    @Override
    public String key() {
        return "guid";
    }
}
