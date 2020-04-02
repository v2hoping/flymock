package com.hoping.owl.flymock.util;

import java.util.regex.Pattern;

/**
 * Created by houping wang on 2019/3/14
 *
 * @author houping wang
 */
public class StringUtil {

    private static final Pattern INTEGER_PATTERN = Pattern.compile("^[-\\+]?[\\d]*$");

    public static boolean isInteger(String str) {
        return INTEGER_PATTERN.matcher(str).matches();
    }

    public static String firstToUpperCase(String str) {
        char[] chars = str.toCharArray();
        if (chars[0] >= 'a' && chars[0] <= 'z') {
            chars[0] = (char) (chars[0] - 32);
        }
        return new String(chars);
    }

    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((!Character.isWhitespace(str.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotBlank(String str) {
        return !StringUtil.isBlank(str);
    }

    public static String lowerFirst(String name) {
        if(StringUtil.isBlank(name)) {
            return name;
        }
        char[] chars = name.toCharArray();
        chars[0] = Character.toLowerCase(chars[0]);
        return String.valueOf(chars);
    }

    public static String lastSpotKey(String str) {
        String[] split = str.split("\\.");
        return split[split.length - 1];
    }


}
