package com.hoping.owl.flymock.flymock;

import com.hoping.owl.flymock.placeholder.MessagePlaceholderFormat;
import org.junit.Test;

/**
 * Created by houping wang on 2019/5/7
 *
 * @author houping wang
 */
public class PlaceholderReturnType {

    @Test
    public void booleanTest() {
        MessagePlaceholderFormat messagePlaceholderFormat = new MessagePlaceholderFormat("@boolean()");
        Object format = messagePlaceholderFormat.format();
        Class aClass = messagePlaceholderFormat.returnType();
    }
}
