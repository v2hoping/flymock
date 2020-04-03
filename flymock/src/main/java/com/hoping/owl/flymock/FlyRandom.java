package com.hoping.owl.flymock;

import com.hoping.owl.flymock.util.ArrayUtil;
import com.hoping.owl.flymock.util.DateUtil;
import com.hoping.owl.flymock.util.StringUtil;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by houping wang on 2019/3/11
 * 随机模拟生成数据
 *
 * @author houping wang
 */
public class FlyRandom {

    /**
     * 自增ID.
     */
    private static AtomicInteger increment = new AtomicInteger(0);

    private static final int ZERO = 0;

    private static final int MAC_NUM = 4;

    private static final int HALF_INTEGER_VALUE = 0x3fffffff;

    private static final List<String> CHARACTER_FORMAT = Arrays.asList("lower", "upper", "number", "symbol");

    private static final String LOWER_CHARACTER = "abcdefghijklmnopqrstuvwxyz";

    private static final String UPPER_CHARACTER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final String NUMBER_CHARACTER = "0123456789";

    private static final String SYMBOL_CHARACTER = "!@#$%^&*()[]";

    private static final String ALPHA_CHARACTER = LOWER_CHARACTER + UPPER_CHARACTER;

    private static final String WHOLE_CHARACTER = ALPHA_CHARACTER + NUMBER_CHARACTER + SYMBOL_CHARACTER;

    private static final String CHINESE_CHARACTERS = "的一是在不了有和人这中大为上个国我以要他时来用们生到作地于出就分对成会可主发年动同工也能下过子说产种面而方后多定行学法所民得经十三之进着等部度家电力里如水化高自二理起小物现实加量都两体制机当使点从业本去把性好应开它合还因由其些然前外天政四日那社义事平形相全表间样与关各重新线内数正心反你明看原又么利比或但质气第向道命此变条只没结解问意建月公无系军很情者最立代想已通并提直题党程展五果料象员革位入常文总次品式活设及管特件长求老头基资边流路级少图山统接知较将组见计别她手角期根论运农指几九区强放决西被干做必战先回则任取据处队南给色光门即保治北造百规热领七海口东导器压志世金增争济阶油思术极交受联什认六共权收证改清己美再采转更单风切打白教速花带安场身车例真务具万每目至达走积示议声报斗完类八离华名确才科张信马节话米整空元况今集温传土许步群广石记需段研界拉林律叫且究观越织装影算低持音众书布复容儿须际商非验连断深难近矿千周委素技备半办青省列习响约支般史感劳便团往酸历市克何除消构府称太准精值号率族维划选标写存候毛亲快效斯院查江型眼王按格养易置派层片始却专状育厂京识适属圆包火住调满县局照参红细引听该铁价严龙飞";

    private static final int CHINESE_START = 0x4E00;

    private static final int CHINESE_STOP = 0x9FA5;

    private static final String[] FIRST_NAMES = {"James", "John", "Robert", "Michael", "William",
            "David", "Richard", "Charles", "Joseph", "Thomas",
            "Christopher", "Daniel", "Paul", "Mark", "Donald",
            "George", "Kenneth", "Steven", "Edward", "Brian",
            "Ronald", "Anthony", "Kevin", "Jason", "Matthew",
            "Gary", "Timothy", "Jose", "Larry", "Jeffrey",
            "Frank", "Scott", "Eric", "Mary", "Patricia", "Linda", "Barbara", "Elizabeth",
            "Jennifer", "Maria", "Susan", "Margaret", "Dorothy",
            "Lisa", "Nancy", "Karen", "Betty", "Helen",
            "Sandra", "Donna", "Carol", "Ruth", "Sharon",
            "Michelle", "Laura", "Sarah", "Kimberly", "Deborah",
            "Jessica", "Shirley", "Cynthia", "Angela", "Melissa",
            "Brenda", "Amy", "Anna"};

    private static final String[] LAST_NAMES = {"Smith", "Johnson", "Williams", "Brown", "Jones",
            "Miller", "Davis", "Garcia", "Rodriguez", "Wilson",
            "Martinez", "Anderson", "Taylor", "Thomas", "Hernandez",
            "Moore", "Martin", "Jackson", "Thompson", "White",
            "Lopez", "Lee", "Gonzalez", "Harris", "Clark",
            "Lewis", "Robinson", "Walker", "Perez", "Hall",
            "Young", "Allen"};
    /**
     * 随机生成一个常见的中文姓。
     * [世界常用姓氏排行](http://baike.baidu.com/view/1719115.htm)
     * [玄派网 - 网络小说创作辅助平台](http://xuanpai.sinaapp.com/)
     */
    private static final String[] FIRST_CNAMES =
            {"王", "李", "张", "刘", "陈", "杨", "赵", "黄", "周", "吴",
                    "徐", "孙", "胡", "朱", "高", "林", "何", "郭", "马", "罗",
                    "梁", "宋", "郑", "谢", "韩", "唐", "冯", "于", "董", "萧",
                    "程", "曹", "袁", "邓", "许", "傅", "沈", "曾", "彭", "吕",
                    "苏", "卢", "蒋", "蔡", "贾", "丁", "魏", "薛", "叶", "阎",
                    "余", "潘", "杜", "戴", "夏", "锺", "汪", "田", "任", "姜",
                    "范", "方", "石", "姚", "谭", "廖", "邹", "熊", "金", "陆",
                    "郝", "孔", "白", "崔", "康", "毛", "邱", "秦", "江", "史",
                    "顾", "侯", "邵", "孟", "龙", "万", "段", "雷", "钱", "汤",
                    "尹", "黎", "易", "常", "武", "乔", "贺", "赖", "龚", "文"};

    private static final String[] LAST_CNAMES =
            {"伟", "芳", "娜", "秀英", "敏", "静", "丽", "强", "磊", "军",
                    "洋", "勇", "艳", "杰", "娟", "涛", "明", "超", "秀兰", "霞",
                    "平", "刚", "桂英"};

    private static final String TLDS = "com net org edu gov int mil cn com.cn net.cn gov.cn org.cn 中国 中国互联.公司 中国互联.网络 tel biz cc tv info name hk mobi asia cd travel pro museum coop aero ad ae af ag ai al am an ao aq ar as at au aw az ba bb bd be bf bg bh bi bj bm bn bo br bs bt bv bw by bz ca cc cf cg ch ci ck cl cm cn co cq cr cu cv cx cy cz de dj dk dm do dz ec ee eg eh es et ev fi fj fk fm fo fr ga gb gd ge gf gh gi gl gm gn gp gr gt gu gw gy hk hm hn hr ht hu id ie il in io iq ir is it jm jo jp ke kg kh ki km kn kp kr kw ky kz la lb lc li lk lr ls lt lu lv ly ma mc md mg mh ml mm mn mo mp mq mr ms mt mv mw mx my mz na nc ne nf ng ni nl no np nr nt nu nz om qa pa pe pf pg ph pk pl pm pn pr pt pw py re ro ru rw sa sb sc sd se sg sh si sj sk sl sm sn so sr st su sy sz tc td tf tg th tj tk tm tn to tp tr tt tv tw tz ua ug uk us uy va vc ve vg vn vu wf ws ye yu za zm zr zw";

    private static final String PROTOCOLS = "http ftp gopher mailto mid cid news nntp prospero telnet rlogin tn3270 wais";

    private static final String[] REGION = {"东北", "华北", "华东", "华中", "华南", "西南", "西北"};

    /**
     * boolean
     * @return true/false
     */
    public static Boolean booleanRandom() {
        return booleanRandom(1, 1, true);
    }

    /**
     * boolean Random
     * @param min min
     * @param max max
     * @param bl bl
     * @return true/false
     */
    public static Boolean booleanRandom(Integer min, Integer max, Boolean bl) {
        if (null != bl) {
            min = (null == min) ? 1 : min;
            max = (null == max) ? 1 : max;
            int total = new Random().nextInt(min + max);
            return total - min < 0 ? bl : !bl;
        }
        return new Random().nextBoolean();
    }

    /**
     * natural
     * @return natural, type is Integer
     */
    public static Integer natural() {
        return natural(0);
    }

    public static Integer natural(Integer min) {
        return natural(min, Integer.MAX_VALUE);
    }

    public static Integer natural(Integer min, Integer max) {
        min = (null == min || min < 0) ? ZERO : min;
        max = (null == max || max >= Integer.MAX_VALUE - 1) ? Integer.MAX_VALUE - 1 : max;
        if(min > max) {
            Integer tmp = min;
            min = max;
            max = tmp;
        }
        return new Random().nextInt(max - min + 1) + min;
    }

    /**
     * integer
     * @return integer
     */
    public static Integer integer() {
        return integer(-HALF_INTEGER_VALUE, HALF_INTEGER_VALUE);
    }

    public static Integer integer(Integer min) {
        return integer(min, HALF_INTEGER_VALUE);
    }

    public static Integer integer(Integer min, Integer max) {
        min = (null == min) ? -HALF_INTEGER_VALUE : min;
        max = (null == max) ? HALF_INTEGER_VALUE : max;
        return new Random().nextInt(max - min + 1) + min;
    }

    /**********
     * double *
     **********/
    /**
     * double
     * @return double
     */
    public static Double doubleRandom() {
        return doubleRandom(-65536);
    }

    public static Double doubleRandom(Integer min) {
        return doubleRandom(min, 65536);
    }

    public static Double doubleRandom(Integer min, Integer max) {
        return doubleRandom(min, max, 0, 6);
    }

    public static Double doubleRandom(Integer min, Integer max, Integer dMin) {
        return doubleRandom(min, max, dMin, 6);
    }

    public static Double doubleRandom(Integer min, Integer max, Integer dMin, Integer dMax) {
        return Double.parseDouble(doubleRandomStr(min, max, dMin, dMax));
    }

    public static String doubleRandomStr(Integer min, Integer max, Integer dMin, Integer dMax) {
        Integer first = integer(min, max);
        dMin = Math.max(Math.min(dMin, 17), 0);
        dMax = Math.max(Math.min(dMax, 17), 0);
        int count = natural(dMin, dMax);
        StringBuilder sb = new StringBuilder();
        sb.append(first);
        for (int i = 0; i < count; i++) {
            if (0 == i) {
                sb.append(".");
            }
            sb.append(i == (count - 1) ? character("123456789") : character("number"));
        }
        return sb.toString();
    }

    /*************
     * character *
     *************/
    /**
     * character
     * @return character
     */
    public static Character character() {
        return character(CHARACTER_FORMAT.get(natural(0, CHARACTER_FORMAT.size() - 1)));
    }

    /**
     * 生成字符
     *
     * @param format lower|upper|number|symbol
     *               pool: "abcd"相等于'a'|'b'|'c'|'d'
     * @return Character
     */
    public static Character character(String format) {
        if (null == format) {
            throw new FlyMockException("character method, format not could is null");
        }
        String characters = format;
        switch (format) {
            case "lower":
                characters = LOWER_CHARACTER;
                break;
            case "upper":
                characters = UPPER_CHARACTER;
                break;
            case "number":
                characters = NUMBER_CHARACTER;
                break;
            case "symbol":
                characters = SYMBOL_CHARACTER;
                break;
            default:
        }
        return characters.charAt(natural(ZERO, characters.length() - 1));
    }

    /**
     * string
     * @return string
     */
    public static String string() {
        return string(3, 7);
    }

    public static String string(Integer count) {
        return string(WHOLE_CHARACTER, count);
    }

    public static String string(String format, Integer count) {
        return string(format, count, count);
    }

    public static String string(Integer min, Integer max) {
        return string(WHOLE_CHARACTER, min, max);
    }

    /**
     * 生成字符串
     *
     * @param format lower|upper|number|symbol
     *               pool: "abcd"相当于'a'|'b'|'c'|'d'
     * @param min    if is null set 3, min value is 0
     * @param max    if is null set 10, max value is Integer.Max
     * @return String
     */
    public static String string(String format, Integer min, Integer max) {
        min = (null == min) ? 3 : (min < 0 ? 0 : min);
        max = (null == max) ? 10 : (max > Integer.MAX_VALUE ? Integer.MAX_VALUE : max);
        StringBuilder sb = new StringBuilder();
        Integer count = natural(min, max);
        for (int i = 0; i < count; i++) {
            sb.append(character(format));
        }
        return sb.toString();
    }

    /**
     * range
     * @param stop stop
     * @return 1,2,3...stop
     */
    public static List<Integer> range(Integer stop) {
        return range(0, stop);
    }

    public static List<Integer> range(Integer start, Integer stop) {
        return range(start, stop, 1);
    }

    public static List<Integer> range(Integer start, Integer stop, Integer step) {
        if (null == stop) {
            throw new FlyMockException("stop is null, please set value");
        }
        step = Math.abs(step);
        stop = start > stop ? start : stop;
        Integer len = (int) Math.max(Math.ceil((stop - start) / step), 0);
        List<Integer> list = new ArrayList<>(len);
        for (int v = start; v < stop; v += step) {
            list.add(v);
        }
        return list;
    }

    /**
     * date
     * example:2004-04-08
     * @return date,the type is String
     */
    public static String date() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    public static Long timeStamp() {
        return System.currentTimeMillis();
    }

    /**
     * time
     * example:21:18:44
     * @return time
     */
    public static String time() {
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }

    /**
     * datetime
     * example:2004-04-08 21:18:44
     * @return datetime
     */
    public static String datetime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public static String datetime(String format) {
        return new SimpleDateFormat(format).format(new Date());
    }

    /**
     * now
     * Beginning of Time in Different Dimensions
     * @return now
     */
    public static Date now() {
        return new Date();
    }

    public static Date now(String unit) {
        if (null == unit) {
            throw new FlyMockException("param unit must be filled");
        }
        Date date = new Date();
        switch (unit) {
            case "year":
                return DateUtil.getYearBeginTime(date);
            case "half":
                return DateUtil.getHalfYearBeginTime(date);
            case "month":
                return DateUtil.getMonthBeginTime(date);
            case "week":
                return DateUtil.getWeekBeginTime(date);
            case "day":
                return DateUtil.getDayBeginTime(date);
            case "hour":
                return DateUtil.getHourBeginTime(date);
            case "minute":
                return DateUtil.getTenMinuteBeginTime(date);
            case "second":
                return DateUtil.getSecondBeginTime(date);
            case "now":
            default:
                return date;
        }
    }

    /**
     * getting the start of time
     *
     * @param unit   now|year|half|month|week|day|hour|minute|second
     *               param unit must be filled
     * @param format format of time
     * @return Date
     */
    public static String now(String unit, String format) {
        format = StringUtil.isNotBlank(format) ? format : DateUtil.PATTERN[7];
        return new SimpleDateFormat(format).format(now(unit));
    }

    public static Date end(String unit) {
        if (null == unit) {
            throw new FlyMockException("param unit must be filled");
        }
        Date date = new Date();
        switch (unit) {
            case "year":
                return DateUtil.getYearEndTime(date);
            case "half":
                return DateUtil.getHalfYearEndTime(date);
            case "month":
                return DateUtil.getMonthEndTime(date);
            case "week":
                return DateUtil.getWeekEndTime(date);
            case "day":
                return DateUtil.getDayEndTime(date);
            case "hour":
                return DateUtil.getHourEndTime(date);
            case "minute":
                return DateUtil.getMinuteEndTime(date);
            case "second":
                return DateUtil.getSecondEndTime(date);
            case "now":
            default:
                return date;
        }
    }

    public static String end(String unit, String format) {
        format = StringUtil.isNotBlank(format) ? format : DateUtil.PATTERN[7];
        return new SimpleDateFormat(format).format(end(unit));
    }

    /**
     * image
     * @return image
     */
    public static String color() {
        return "#" + hex();
    }

    public static String hex() {
        return hex(3);
    }

    public static String rgb() {
        return MessageFormat.format("rgb({0},{1},{2})", natural(0, 255), natural(0, 255), natural(0, 255));
    }

    /**
     * paragraph
     * @return paragraph
     */
    public static String paragraph() {
        return paragraph(null);
    }

    public static String paragraph(Integer len) {
        return paragraph(len, len);
    }

    public static String paragraph(Integer min, Integer max) {
        Integer len = range(3, 7, min, max);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(sentence()).append(" ");
        }
        return sb.toString();
    }

    /**
     * sentence
     * @return sentence
     */
    public static String sentence() {
        return sentence(null);
    }

    public static String sentence(Integer len) {
        return sentence(len, len);
    }

    public static String sentence(Integer min, Integer max) {
        Integer len = range(12, 18, min, max);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            if (0 == i) {
                sb.append(word(true));
            } else {
                sb.append(word());
            }
            if (i == len - 1) {
                sb.append(".");
            } else {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    /**
     * 生成首字母大写或小写单词
     *
     * @param bl false/true
     * @return String
     */
    public static String word(Boolean bl) {
        return word(null, null, bl);
    }

    public static String word() {
        return word(false);
    }

    public static String word(Integer len) {
        return word(len, len);
    }

    public static String word(Integer min, Integer max) {
        return word(min, max, false);
    }

    public static String word(Integer min, Integer max, boolean bl) {
        Integer len = range(3, 10, min, max);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            if (0 == i && bl) {
                sb.append(character("upper"));
            } else {
                sb.append(character("lower"));
            }
        }
        return sb.toString();
    }

    /**
     * title
     * @return title
     */
    public static String title() {
        return title(null);
    }

    public static String title(Integer len) {
        return title(len, len);
    }

    public static String title(Integer min, Integer max) {
        Integer len = range(3, 7, min, max);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(word(3, 7, true));
            if (i != len - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    /**
     * cparagraph
     * @return cparagraph
     */
    public static String cparagraph() {
        return cparagraph(null);
    }

    public static String cparagraph(Integer len) {
        return cparagraph(len, len);
    }

    public static String cparagraph(Integer min, Integer max) {
        Integer len = range(3, 7, min, max);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(csentence());
        }
        return sb.toString();
    }

    /**
     * csentence
     * @return csentence
     */
    public static String csentence() {
        return csentence(null);
    }

    public static String csentence(Integer len) {
        return csentence(len, len);
    }

    public static String csentence(Integer min, Integer max) {
        Integer len = range(12, 18, min, max);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(cword());
            if (i == len - 1) {
                sb.append("。");
            }
        }
        return sb.toString();
    }

    /*********
     * cword *
     *********/
    /**
     * cword
     * @return cword
     */
    public static String cword() {
        return cword(1);
    }

    public static String cword(String pool) {
        return cword(pool, 1);
    }

    public static String cword(Integer len) {
        return cword(len, len);
    }

    public static String cword(String pool, Integer len) {
        return cword(pool, len, len);
    }

    public static String cword(Integer min, Integer max) {
        return cword("common", min, max);
    }

    /**
     * 生成一个或多个汉字
     *
     * @param pool 汉字池 null|common
     *             null:所有汉字
     *             common:常用汉字
     *             "我是谁"|1相当于"我"
     * @param min  最小数
     * @param max  最大数
     * @return String
     */
    public static String cword(String pool, Integer min, Integer max) {
        StringBuilder sb = new StringBuilder();
        Integer len = range(1, 1, min, max);
        if (null == pool) {
            for (int i = 0; i < len; i++) {
                sb.append(allword());
            }
            return sb.toString();
        }
        pool = "common".equals(pool) ? CHINESE_CHARACTERS : pool;
        for (int i = 0; i < len; i++) {
            sb.append(pool.charAt(natural(0, pool.length() - 1)));
        }
        return sb.toString();
    }

    /**
     * ctitle
     * @return ctitle
     */
    public static String ctitle() {
        return ctitle(null);
    }

    public static String ctitle(Integer len) {
        return ctitle(len, len);
    }

    public static String ctitle(Integer min, Integer max) {
        Integer len = range(3, 7, min, max);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(cword(3, 7));
            if (i != len - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    /**
     * name
     * @return first
     */
    public static String first() {
        return ArrayUtil.pick(FIRST_NAMES);
    }

    public static String last() {
        return ArrayUtil.pick(LAST_NAMES);
    }

    public static String name() {
        return first() + " " + last();
    }

    /**
     * cname
     * @return cname
     */
    public static String cfirst() {
        return ArrayUtil.pick(FIRST_CNAMES);
    }

    public static String clast() {
        return ArrayUtil.pick(LAST_CNAMES);
    }

    public static String cname() {
        return cfirst() + clast();
    }

    /**
     * web
     * @return web
     */
    public static String url() {
        return protocol() + "://" + domain() + "/" + word();
    }

    public static String domain() {
        return word() + "." + tld();
    }

    public static String protocol() {
        String[] protocols = PROTOCOLS.split(" ");
        return protocols[natural(0, protocols.length - 1)];
    }

    public static String tld() {
        String[] tlds = TLDS.split(" ");
        return tlds[natural(0, tlds.length - 1)];
    }

    public static String email() {
        return word() + "@" + domain();
    }

    public static String ip() {
        return natural(0, 255) + "." + natural(0, 255) + "." + natural(0, 255) + "." + natural(0, 255);
    }

    public static String mobile() {
        return "1" + character("34578") + string("number", 9);
    }

    /**
     * Address
     * @return Address
     */
    public static String region() {
        return ArrayUtil.pick(REGION);
    }

    public static String province() {
        return AddressDict.province();
    }

    public static String city() {
        return AddressDict.city();
    }

    public static String city(Boolean bl) {
        return AddressDict.city(bl);
    }

    public static String county() {
        return AddressDict.county();
    }

    public static String county(Boolean bl) {
        return AddressDict.county(bl);
    }

    public static String zip() {
        return AddressDict.getRandomKey();
    }

    /**
     * Capitalize the first letter of a word
     *
     * @param word word
     * @return String
     */
    public static String capitalize(String word) {
        return StringUtil.firstToUpperCase(word);
    }

    public static String upper(String word) {
        return word.toUpperCase();
    }

    public static String lower(String word) {
        return word.toLowerCase();
    }

    public static String pick(List<String> arr) {
        return ArrayUtil.pick(arr);
    }

    public static String pick(String... arr) {
        return ArrayUtil.pick(arr);
    }

    public static List<String> shuffle(List<String> arr) {
        Collections.shuffle(arr);
        return arr;
    }

    public static List<String> shuffle(String... arr) {
        ArrayList<String> arrList = new ArrayList<>(Arrays.asList(arr));
        Collections.shuffle(arrList);
        return arrList;
    }

    /**
     * number
     * @return number
     */
    public static String guid() {
        return guid("native");
    }

    /**
     * Generate UUID
     *
     * @param format native|normal|upper
     * @return String
     */
    public static String guid(String format) {
        switch (format) {
            case "native":
                return UUID.randomUUID().toString();
            case "normal":
                return UUID.randomUUID().toString().replace("-", "");
            case "upper":
                return UUID.randomUUID().toString().replace("-", "").toUpperCase();
            default:
                return UUID.randomUUID().toString();
        }
    }

    public static String id() {
        return string("number", 2) + "0000" + string("number", 12);
    }

    public static Integer increment() {
        return increment.getAndIncrement();
    }

    public static String idCard() {
        return string("number", 18);
    }

    public static String mac() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < MAC_NUM; i++) {
            sb.append(byteHex());
            if (i != 3) {
                sb.append(":");
            }
        }
        return sb.toString();
    }

    private static String byteHex() {
        return Integer.toHexString(natural(0, 15)) + Integer.toHexString(natural(0, 15));
    }

    private static Integer range(Integer defaultMin, Integer defaultMax, Integer min, Integer max) {
        if (null == defaultMin || null == defaultMax) {
            throw new FlyMockException("defaultMin or defaultMax is null, please set value");
        }
        return null == min ? natural(defaultMin, defaultMax) :
                (null == max ? min : natural(min, max));
    }

    private static String allword() {
        Integer word = natural(CHINESE_START, CHINESE_STOP);
        char c = (char) word.intValue();
        return String.valueOf(c);
    }

    private static String hex(Integer num) {
        num = (num < 0) ? 1 : num;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num; i++) {
            sb.append(Integer.toHexString(natural(0, 255)));
        }
        return sb.toString();
    }
}
