package com.hoping.owl.flymock.placeholder.handle;

import com.hoping.owl.flymock.FlyRandom;
import com.hoping.owl.flymock.placeholder.AbstractPlaceholderHandle;
import com.hoping.owl.flymock.placeholder.PlaceholderHandle;
import com.hoping.owl.flymock.placeholder.PlaceholderWrap;

import java.util.List;

/**
 * Created by houping wang on 2019/4/10
 *
 * @author houping wang
 */
public class BooleanHandle extends AbstractPlaceholderHandle<Boolean> {

    @Override
    public Boolean invoke(PlaceholderWrap placeholderWrap) {
        List<String> args = placeholderWrap.getArgs();
        if (args == null || args.size() == 0) {
            return FlyRandom.booleanRandom();
        }
        if (args.size() == 3) {
            return FlyRandom.booleanRandom(Integer.parseInt(args.get(0)), Integer.parseInt(args.get(1)), Boolean.parseBoolean(args.get(2)));
        }
        return FlyRandom.booleanRandom();
    }

    @Override
    public String key() {
        return "boolean";
    }
}
