package com.hoping.owl.flymock.util;

import java.util.List;
import java.util.Random;

/**
 * Created by houping wang on 2019/3/15
 *
 * @author houping wang
 */
public class ArrayUtil {

    public static <T> T pick(List<T> list) {
        if (isEmpty(list)) {
            return null;
        }
        return list.get(new Random().nextInt(list.size()));
    }

    public static String pick(String[] list) {
        if (isEmpty(list)) {
            return null;
        }
        return list[(new Random().nextInt(list.length))];
    }

    public static boolean isEmpty(List list) {
        return null == list || list.size() == 0;
    }

    public static boolean isEmpty(String[] list) {
        return null == list || list.length == 0;
    }

    public static boolean equalList(List list1, List list2) {
        if (list1 == null && list2 == null) {
            return true;
        } else if (list1 != null && list2 != null) {
            return (list1.size() == list2.size()) && (list1.containsAll(list2));
        } else {
            return false;
        }
    }
}
