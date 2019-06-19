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
public class CwordHandle extends AbstractPlaceholderHandle<String> {

    @Override
    public String invoke(PlaceholderWrap placeholderWrap) {
        List<String> args = placeholderWrap.getArgs();
        if(ArrayUtil.isEmpty(args)) {
            return FlyRandom.cword();
        }
        if(args.size() == 1) {
            if(StringUtil.isInteger(args.get(0))) {
                return FlyRandom.cword(Integer.parseInt(args.get(0)));
            }else{
                return FlyRandom.cword(args.get(0));
            }
        }
        if(args.size() == 2) {
            if(StringUtil.isInteger(args.get(0))) {
                return FlyRandom.cword(Integer.parseInt(args.get(0)), Integer.parseInt(args.get(1)));
            }else{
                return FlyRandom.cword(args.get(0), Integer.parseInt(args.get(1)));
            }
        }
        if(args.size() == 3) {
            return FlyRandom.cword(args.get(0), Integer.parseInt(args.get(1)), Integer.parseInt(args.get(2)));
        }
        return FlyRandom.cword();
    }

    @Override
    public String key() {
        return "cword";
    }
}
