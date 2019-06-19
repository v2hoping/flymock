package com.hoping.owl.flymock.placeholder.handle;

import com.hoping.owl.flymock.FlyRandom;
import com.hoping.owl.flymock.placeholder.AbstractPlaceholderHandle;
import com.hoping.owl.flymock.placeholder.PlaceholderHandle;
import com.hoping.owl.flymock.placeholder.PlaceholderWrap;
import com.hoping.owl.flymock.util.ArrayUtil;
import com.hoping.owl.flymock.util.StringUtil;

import java.util.List;

/**
 * Created by houping wang on 2019/4/16
 *
 * @author houping wang
 */
public class WordHandle extends AbstractPlaceholderHandle<String> {
    @Override
    public String invoke(PlaceholderWrap placeholderWrap) {
        List<String> args = placeholderWrap.getArgs();
        if(ArrayUtil.isEmpty(args)) {
            return FlyRandom.word();
        }
        if(args.size() == 1) {
            if(StringUtil.isInteger(args.get(0))) {
                return FlyRandom.word(Integer.parseInt(args.get(0)));
            }else {
                return FlyRandom.word(Boolean.getBoolean(args.get(0)));
            }
        }
        if(args.size() == 2) {
            return FlyRandom.word(Integer.parseInt(args.get(0)), Integer.parseInt(args.get(1)));
        }
        return FlyRandom.word();
    }

    @Override
    public String key() {
        return "word";
    }
}
