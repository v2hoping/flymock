package com.hoping.owl.flymock.placeholder;

import com.hoping.owl.flymock.FlyMockException;
import com.hoping.owl.flymock.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by houping wang on 2019/4/10
 * 占位符包装字符串
 *
 * @author houping wang
 */
public class PlaceholderWrap {

    /**
     * 占位符原始字符串
     */
    private final String originPlaceholder;

    private final String key;

    private final List<String> args = new ArrayList<>();

    public PlaceholderWrap(String originPlaceholder, String key, List<StringBuilder> argsValues) {
        this.originPlaceholder = originPlaceholder;
        this.key = key;
        if(argsValues != null) {
            for(StringBuilder sb : argsValues) {
                args.add(sb.toString());
            }
        }
        valid();
    }

    public String getOriginPlaceholder() {
        return originPlaceholder;
    }

    public String getKey() {
        return key;
    }

    public List<String> getArgs() {
        return args;
    }

    private void valid() {
        if (StringUtil.isBlank(originPlaceholder)
                || StringUtil.isBlank(key)
                || args == null) {
            throw new FlyMockException("PlaceholderWrap值必须存在");
        }
    }
}
