package com.hoping.owl.flymock.placeholder.handle;

import com.hoping.owl.flymock.FlyRandom;
import com.hoping.owl.flymock.placeholder.AbstractPlaceholderHandle;
import com.hoping.owl.flymock.placeholder.PlaceholderHandle;
import com.hoping.owl.flymock.placeholder.PlaceholderWrap;

import java.util.List;

/**
 * Created by houping wang on 2019/4/15
 *
 * @author houping wang
 */
public class IntegerHandle extends AbstractPlaceholderHandle<Integer> {

    @Override
    public Integer invoke(PlaceholderWrap placeholderWrap) {
        List<String> args = placeholderWrap.getArgs();
        if(args == null || args.size() == 0) {
            return FlyRandom.integer();
        }else if(args.size() == 1) {
            return FlyRandom.integer(Integer.parseInt(args.get(0)));
        }else if(args.size() == 2) {
            return FlyRandom.integer(Integer.parseInt(args.get(0)),
                    Integer.parseInt(args.get(1)));
        }
        return FlyRandom.integer();
    }

    @Override
    public String key() {
        return "integer";
    }
}
