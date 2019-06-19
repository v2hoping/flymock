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
public class CharacterHandle extends AbstractPlaceholderHandle<Character> {

    @Override
    public Character invoke(PlaceholderWrap placeholderWrap) {
        List<String> args = placeholderWrap.getArgs();
        if (ArrayUtil.isEmpty(args)) {
            return FlyRandom.character();
        }
        if (args.size() == 1) {
            return FlyRandom.character(args.get(0));
        }
        return FlyRandom.character();
    }

    @Override
    public String key() {
        return "character";
    }
}
