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
public class SentenceHandle extends AbstractPlaceholderHandle<String> {
    @Override
    public String invoke(PlaceholderWrap placeholderWrap) {
        List<String> args = placeholderWrap.getArgs();
        if(ArrayUtil.isEmpty(args)) {
            return FlyRandom.sentence();
        }
        if(args.size() == 1) {
            return FlyRandom.sentence(Integer.parseInt(args.get(0)));
        }
        if(args.size() == 2) {
            return FlyRandom.sentence(Integer.parseInt(args.get(0)), Integer.parseInt(args.get(1)));
        }
        return FlyRandom.sentence();
    }

    @Override
    public String key() {
        return "sentence";
    }
}
