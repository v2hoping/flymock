package com.hoping.owl.flymock.placeholder;

import com.hoping.owl.flymock.placeholder.manager.LocalPlaceholderManager;
import com.hoping.owl.flymock.util.StringUtil;
import com.hoping.owl.flymock.util.ArrayUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by houping wang on 2019/4/9
 *
 * @author houping wang
 */
public class MessagePlaceholderFormat {

    /**
     * 推进标识-字符检索
     */
    private static final int IDE_RAW = 0;
    /**
     * 推进标识-标识检索
     */
    private static final int IDE_INDEX = 1;
    /**
     * 推进标识-参数检索
     */
    private static final int IDE_PARAM = 2;

    private static final Logger LOGGER = LoggerFactory.getLogger(MessagePlaceholderFormat.class);

    private static final long serialVersionUID = 6479157306784022952L;

    private static PlaceholderManager placeholderManager = null;

    static {
        setPlaceholderManager(new LocalPlaceholderManager());
    }

    public static void setPlaceholderManager(PlaceholderManager placeholderManager) {
        MessagePlaceholderFormat.placeholderManager = placeholderManager;
        placeholderManager.init();
    }

    public static boolean put(PlaceholderHandle placeholderHandle) {
        if(placeholderManager == null) {
            return false;
        }
        return placeholderManager.put(placeholderHandle);
    }

    /**
     * The initially expected number of subformats in the format
     */
    private static final int INITIAL_FORMATS = 10;

    /**
     * The positions where the results of formatting each argument are to be inserted
     * into the pattern.
     *
     * @serial
     */
    private int[] offsets = new int[INITIAL_FORMATS];

    /**
     * 生成参数.
     */
    private List<Object> paramBlocks = new ArrayList<>();

    /**
     * 占位符预处理.
     */
    private List<PlaceholderPreHandle> placeholderPreHandles = new ArrayList<>();

    /**
     * The string that the formatted values are to be plugged into.  In other words, this
     * is the pattern supplied on construction with all of the {} expressions taken out.
     *
     * @serial
     */
    private String pattern = "";

    /**
     * One less than the number of entries in <code>offsets</code>.  Can also be thought of
     * as the index of the highest-numbered element in <code>offsets</code> that is being used.
     * All of these arrays should have the same number of elements being used as <code>offsets</code>
     * does, and so this variable suffices to tell us how many entries are in all of them.
     *
     * @serial
     */
    private int maxOffset = -1;

    /**
     * Constructs a MessageFormat for the specified locale and
     * pattern.
     * The constructor first sets the locale, then parses the pattern and
     * creates a list of subformats for the format elements contained in it.
     * Patterns and their interpretation are specified in the
     * <a href="#patterns">class description</a>.
     *
     * @param pattern the pattern for this message format
     * @throws IllegalArgumentException if the pattern is invalid
     * @since 1.4
     */
    public MessagePlaceholderFormat(String pattern) {
        applyPattern(pattern);
    }

    /**
     * Sets the pattern used by this message format.
     * The method parses the pattern and creates a list of subformats
     * for the format elements contained in it.
     * Patterns and their interpretation are specified in the
     * <a href="#patterns">class description</a>.
     *
     * @param pattern the pattern for this message format
     * @throws IllegalArgumentException if the pattern is invalid
     */
    public void applyPattern(String pattern) {
        //@key(params)
        //words-key
        StringBuilder[] segments = new StringBuilder[2];
        List<StringBuilder> params = new ArrayList<>();
        StringBuilder originKey = new StringBuilder();

        segments[IDE_RAW] = new StringBuilder();

        int part = IDE_RAW;
        int formatNumber = 0;
        maxOffset = -1;
        for (int i = 0; i < pattern.length(); ++i) {
            char ch = pattern.charAt(i);
            if (part == IDE_RAW) {
                if (ch == '@') {
                    part = IDE_INDEX;
                    if (segments[IDE_INDEX] == null) {
                        segments[IDE_INDEX] = new StringBuilder();
                    }
                    originKey = makeOriginKey(originKey, ch);
                } else {
                    segments[part].append(ch);
                }
            } else if (part == IDE_INDEX) {
                switch (ch) {
                    case '(':
                        params = new ArrayList<>();
                        part = IDE_PARAM;
                        break;
                    default:
                        segments[part].append(ch);
                        break;
                }
                originKey = makeOriginKey(originKey, ch);
            } else {
                switch (ch) {
                    case ',':
                        originKey = makeOriginKey(originKey, ch);
                        part++;
                        break;
                    case ')':
                        originKey = makeOriginKey(originKey, ch);
                        part = IDE_RAW;
                        makeFormat(formatNumber, segments, params, originKey.toString());
                        formatNumber++;
                        // throw away other segments
                        segments[IDE_INDEX] = null;
                        params.clear();
                        originKey = null;
                        break;
                    default:
                        originKey = makeOriginKey(originKey, ch);
                        makeParam(part, params, ch);
                        break;
                }
            }
        }
        this.pattern = segments[0].toString();
    }

    public static Object format(String pattern) {
        MessagePlaceholderFormat temp = new MessagePlaceholderFormat(pattern);
        return temp.format();
    }

    /**
     * Equality comparison between two message format objects
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MessagePlaceholderFormat other = (MessagePlaceholderFormat) obj;
        return (maxOffset == other.maxOffset
                && pattern.equals(other.pattern)
                && ArrayUtil.equalList(paramBlocks, other.paramBlocks));
    }

    /**
     * Generates a hash code for the message format object.
     */
    @Override
    public int hashCode() {
        // enough for reasonable distribution
        return pattern.hashCode();
    }

    /**
     * 是否属于独立的占位符.
     * 独立占位符可能返回各种类型
     * 非独立占位符会返回String类型
     * @return true/false
     */
    public boolean haveSinglePlaceholder() {
        return maxOffset == 0 && StringUtil.isBlank(pattern);
    }

    public Class returnType() {
        if(haveSinglePlaceholder()) {
            PlaceholderPreHandle placeholderPreHandle = placeholderPreHandles.get(0);
            PlaceholderHandle placeholderHandle = placeholderPreHandle.getPlaceholderHandle();
            return placeholderHandle.returnClassType();
        }
        return String.class;
    }

    private static StringBuilder makeOriginKey(StringBuilder sb, char c) {
        if (sb == null) {
            sb = new StringBuilder();
        }
        sb.append(c);
        return sb;
    }

    private void makeParam(int part, List<StringBuilder> params, char ch) {
        int paramsIndexDefault = part - 2;
        StringBuilder defaultSb = null;
        if (paramsIndexDefault >= params.size()) {
            defaultSb = new StringBuilder();
            params.add(defaultSb);
        }
        defaultSb = params.get(paramsIndexDefault);
        if (defaultSb == null) {
            defaultSb = new StringBuilder();
            params.add(defaultSb);
        }
        defaultSb.append(ch);
    }

    public Object format() {
        StringBuilder result = new StringBuilder();
        int lastOffset = 0;
        //为1时直接返回该类型对象
        if (maxOffset == 0 && StringUtil.isBlank(pattern)) {
            PlaceholderPreHandle placeholderPreHandle = placeholderPreHandles.get(0);
            return placeholderPreHandle.generate();
        }
        for (int i = 0; i <= maxOffset; ++i) {
            result.append(pattern.substring(lastOffset, offsets[i]));
            lastOffset = offsets[i];
            PlaceholderPreHandle placeholderPreHandle = placeholderPreHandles.get(i);
            Object generate = placeholderPreHandle.generate();
            result.append(generate == null ? "null" : generate);
        }
        result.append(pattern.substring(lastOffset, pattern.length()));
        return result.toString();
    }

    private String generatePlaceholderStr(StringBuilder[] segments, List<StringBuilder> params) {
        StringBuilder sb = new StringBuilder();
        sb.append('@').append(segments[IDE_INDEX]);
        if (params != null) {
            sb.append('(');
            for (StringBuilder param : params) {
                sb.append(param);
            }
            sb.append(')');
        }
        return sb.toString();
    }

    private void makeFormat(int offsetNumber, StringBuilder[] textSegments, List<StringBuilder> params, String originKeyStr) {
        String[] segments = new String[textSegments.length];
        for (int i = 0; i < textSegments.length; i++) {
            StringBuilder oneseg = textSegments[i];
            segments[i] = (oneseg != null) ? oneseg.toString() : "";
        }
        String key = textSegments[IDE_INDEX].toString();
        // get the placeholder handle
        PlaceholderHandle placeholderHandle = placeholderManager.select(key);
        placeholderPreHandles.add(new PlaceholderPreHandle(placeholderHandle, new PlaceholderWrap(originKeyStr, textSegments[IDE_INDEX].toString(), params)));
        maxOffset = offsetNumber;
        offsets[offsetNumber] = segments[IDE_RAW].length();
    }

    private static class PlaceholderPreHandle {

        private PlaceholderHandle placeholderHandle;

        private PlaceholderWrap placeholderWrap;

        private PlaceholderPreHandle(PlaceholderHandle placeholderHandle, PlaceholderWrap placeholderWrap) {
            this.placeholderHandle = placeholderHandle;
            this.placeholderWrap = placeholderWrap;
        }

        public PlaceholderHandle getPlaceholderHandle() {
            return placeholderHandle;
        }

        public void setPlaceholderHandle(PlaceholderHandle placeholderHandle) {
            this.placeholderHandle = placeholderHandle;
        }

        public PlaceholderWrap getPlaceholderWrap() {
            return placeholderWrap;
        }

        public void setPlaceholderWrap(PlaceholderWrap placeholderWrap) {
            this.placeholderWrap = placeholderWrap;
        }

        public Object generate() {
            if(placeholderHandle == null) {
                return placeholderWrap.getOriginPlaceholder();
            }
            return placeholderHandle.invoke(placeholderWrap);
        }
    }
}
