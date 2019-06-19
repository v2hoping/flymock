package com.hoping.owl.flymock.object;

import com.alibaba.fastjson.JSON;
import com.hoping.owl.flymock.Mock;
import com.hoping.owl.flymock.TypeReference;

/**
 * Created by houping wang on 2019/4/15
 *
 * @author houping wang
 */
public class Template<T> {

    private final String originTemplate;

    private final Object objectTemplate;

    private final TypeReference<T> t;

    public Template(Object objectTemplate, TypeReference<T> t) {
        this.objectTemplate = objectTemplate;
        if (objectTemplate instanceof String) {
            this.originTemplate = (String) objectTemplate;
        } else if (objectTemplate == null) {
            this.originTemplate = null;
        } else {
            this.originTemplate = JSON.toJSONString(objectTemplate);
        }
        this.t = t;
    }

    public String mockToJson() {
        return Mock.mockToJson(originTemplate);
    }

    public T mockType() {
        return Mock.mock(originTemplate, t);
    }

    public Object mock() {
        return Mock.mock(originTemplate);
    }

    public String getOriginTemplate() {
        return originTemplate;
    }
}
