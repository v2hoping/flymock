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
public class RangeHandle extends AbstractPlaceholderHandle<List<Integer>> {
    @Override
    public List<Integer> invoke(PlaceholderWrap placeholderWrap) {
        List<String> args = placeholderWrap.getArgs();
        if(ArrayUtil.isEmpty(args)) {
            return FlyRandom.range(0);
        }
        if(args.size() == 1) {
            return FlyRandom.range(Integer.parseInt(args.get(0)));
        }
        if(args.size() == 2) {
            return FlyRandom.range(Integer.parseInt(args.get(0)), Integer.parseInt(args.get(1)));
        }
        if(args.size() == 3) {
            return FlyRandom.range(Integer.parseInt(args.get(0)), Integer.parseInt(args.get(1)), Integer.parseInt(args.get(2)));
        }
        return FlyRandom.range(0);
    }

    @Override
    public String key() {
        return "range";
    }
}
