package com.hoping.owl.flymock.placeholder.handle;

import com.hoping.owl.flymock.FlyRandom;
import com.hoping.owl.flymock.placeholder.AbstractPlaceholderHandle;
import com.hoping.owl.flymock.placeholder.PlaceholderHandle;
import com.hoping.owl.flymock.placeholder.PlaceholderWrap;
import com.hoping.owl.flymock.util.StringUtil;

import java.util.List;

/**
 * Created by houping wang on 2019/4/15
 *
 * @author houping wang
 */
public class StringHandle extends AbstractPlaceholderHandle<String> {

    @Override
    public String invoke(PlaceholderWrap placeholderWrap) {
        List<String> args = placeholderWrap.getArgs();
        if(args == null || args.size() == 0) {
            return FlyRandom.string();
        }
        if(args.size() == 1) {
            return FlyRandom.string(Integer.parseInt(args.get(0)));
        }
        if(args.size() == 2) {
            String param1 = args.get(0);
            if(StringUtil.isInteger(param1)) {
                return FlyRandom.string(Integer.parseInt(param1), Integer.parseInt(args.get(1)));
            }else{
                return FlyRandom.string(param1, Integer.parseInt(args.get(1)));
            }
        }
        if(args.size() == 3) {
            return FlyRandom.string(args.get(0), Integer.parseInt(args.get(1)), Integer.parseInt(args.get(2)));
        }
        return FlyRandom.string();
    }

    @Override
    public String key() {
        return "string";
    }
}
