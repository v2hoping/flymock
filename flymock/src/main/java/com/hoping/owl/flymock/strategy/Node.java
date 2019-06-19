package com.hoping.owl.flymock.strategy;

import java.util.Map;
import java.util.Objects;

/**
 * Created by houping wang on 2019/4/4
 *
 * @author houping wang
 */
public class Node<K, V> implements Map.Entry<K, V> {
    private final K key;
    private V value;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public final K getKey() {
        return key;
    }

    @Override
    public final V getValue() {
        return value;
    }

    @Override
    public final String toString() {
        return key + "=" + value;
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(key) ^ Objects.hashCode(value);
    }

    @Override
    public final V setValue(V newValue) {
        V oldValue = value;
        value = newValue;
        return oldValue;
    }

    @Override
    public final boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof Map.Entry) {
            Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;
            return Objects.equals(key, e.getKey()) &&
                    Objects.equals(value, e.getValue());
        }
        return false;
    }
}
