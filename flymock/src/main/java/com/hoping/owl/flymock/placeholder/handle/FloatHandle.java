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
public class FloatHandle extends AbstractPlaceholderHandle<Double> {

    @Override
    public Double invoke(PlaceholderWrap placeholderWrap) {
        List<String> args = placeholderWrap.getArgs();
        if(ArrayUtil.isEmpty(args)) {
            return FlyRandom.doubleRandom();
        }
        if(args.size() == 1) {
            return FlyRandom.doubleRandom(Integer.parseInt(args.get(0)));
        }
        if(args.size() == 2) {
            return FlyRandom.doubleRandom(Integer.parseInt(args.get(0)), Integer.parseInt(args.get(1)));
        }
        if(args.size() == 3) {
            return FlyRandom.doubleRandom(Integer.parseInt(args.get(0)), Integer.parseInt(args.get(1)),
                    Integer.parseInt(args.get(2)));
        }
        if(args.size() == 4) {
            return FlyRandom.doubleRandom(Integer.parseInt(args.get(0)), Integer.parseInt(args.get(1)),
                    Integer.parseInt(args.get(2)), Integer.parseInt(args.get(3)));
        }
        return FlyRandom.doubleRandom();
    }

    @Override
    public String key() {
        return "float";
    }
}
