package com.hoping.owl.flymock.placeholder.handle;

import com.hoping.owl.flymock.FlyRandom;
import com.hoping.owl.flymock.placeholder.AbstractPlaceholderHandle;
import com.hoping.owl.flymock.placeholder.PlaceholderHandle;
import com.hoping.owl.flymock.placeholder.PlaceholderWrap;

import java.util.List;

/**
 * Created by houping wang on 2019/4/11
 *
 * @author houping wang
 */
public class EnumHandle extends AbstractPlaceholderHandle<String> {

    @Override
    public String invoke(PlaceholderWrap placeholderWrap) {
        String originPlaceholder = placeholderWrap.getOriginPlaceholder();
        List<String> args = placeholderWrap.getArgs();
        if(args == null || args.size() == 0) {
            return originPlaceholder;
        }else{
            Integer index = FlyRandom.natural(0, args.size() - 1);
            return args.get(index);
        }
    }

    @Override
    public String key() {
        return "enum";
    }
}
