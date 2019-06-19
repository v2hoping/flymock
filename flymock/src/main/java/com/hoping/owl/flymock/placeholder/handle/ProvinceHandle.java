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
public class ProvinceHandle extends AbstractPlaceholderHandle<String> {

    @Override
    public String invoke(PlaceholderWrap placeholderWrap) {
        List<String> args = placeholderWrap.getArgs();
        if(ArrayUtil.isEmpty(args)) {
            return FlyRandom.province();
        }
        return FlyRandom.province();
    }

    @Override
    public String key() {
        return "province";
    }
}
