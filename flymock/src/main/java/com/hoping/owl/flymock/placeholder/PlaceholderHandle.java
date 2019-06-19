package com.hoping.owl.flymock.placeholder;

/**
 * Created by houping wang on 2019/4/10
 *
 * @author houping wang
 */
public interface PlaceholderHandle<T> {

    T invoke(PlaceholderWrap placeholderWrap);

    String key();

    Class returnClassType();

}
