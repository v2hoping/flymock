package com.hoping.owl.flymock.placeholder.manager;

import com.hoping.owl.flymock.placeholder.PlaceholderHandle;
import com.hoping.owl.flymock.placeholder.PlaceholderManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by houping wang on 2019/4/24
 *
 * @author houping wang
 */
public abstract class AbstractPlaceholderManager implements PlaceholderManager {

    protected static final Logger LOGGER = LoggerFactory.getLogger(LocalPlaceholderManager.class);

    protected Map<String, PlaceholderHandle> hashMap = new ConcurrentHashMap<>();

    @Override
    public PlaceholderHandle select(String key) {
        return hashMap.get(key);
    }

    public boolean put(String key, PlaceholderHandle value) {
        hashMap.put(key, value);
        return true;
    }

    public void addAll(Set<PlaceholderHandle> set) {
        if (set == null) {
            return;
        }
        for (PlaceholderHandle placeholderHandle : set) {
            hashMap.put(placeholderHandle.key(), placeholderHandle);
        }
    }

    @Override
    public boolean put(PlaceholderHandle placeholderHandle) {
        return put(placeholderHandle.key(), placeholderHandle);
    }
}
