package com.hoping.owl.flymock.flymock;

import com.hoping.owl.flymock.AddressDict;
import com.hoping.owl.flymock.FlyRandom;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * Created by houping wang on 2019/3/12
 *
 * @author houping wang
 */
public class FlyRandomTest {

    @Test
    public void test() {
        Logger LOGGER = LoggerFactory.getLogger(AddressDict.class);
        LOGGER.info("测试");
        System.out.println(LOGGER);
    }

    @Test
    public void booleanRandom() {
        for (int i = 0; i < 10; i++) {
            Boolean bl = FlyRandom.booleanRandom();
            System.out.println(bl);
        }
    }

    @Test
    public void booleanRandom1() {
        for (int i = 0; i < 100; i++) {
            Boolean bl = FlyRandom.booleanRandom(1, 99, true);
            System.out.println(bl);
        }
    }

    @Test
    public void natural() {
        for (int i = 0; i < 20; i++) {
            Integer natural = FlyRandom.natural();
            System.out.println(natural);
        }
    }

    @Test
    public void natural1() {
        for (int i = 0; i < 20; i++) {
            Integer natural = FlyRandom.natural(2091128960);
            System.out.println(natural);
        }
    }

    @Test
    public void natural2() {
        for (int i = 0; i < 20; i++) {
            Integer natural = FlyRandom.natural(0, 2);
            System.out.println(natural);
        }
    }

    @Test
    public void StringToDouble() {
        String str = "-123.1230";
        double v = Double.parseDouble(str);
        System.out.println(v);
    }

    @Test
    public void integer() {
        for (int i = 0; i < 20; i++) {
            Integer natural = FlyRandom.integer();
            System.out.println(natural);
        }
    }

    @Test
    public void integer1() {
        for (int i = 0; i < 20; i++) {
            Integer natural = FlyRandom.integer(0);
            System.out.println(natural);
        }
    }

    @Test
    public void integer2() {
        for (int i = 0; i < 20; i++) {
            Integer natural = FlyRandom.integer(-1, 1);
            System.out.println(natural);
        }
    }

    @Test
    public void doubleRandom() {
        for (int i = -100; i < 100; i++) {
            System.out.println(FlyRandom.doubleRandom());
        }
    }

    @Test
    public void doubleRandom1() {
        for (int i = -100; i < 100; i++) {
            System.out.println(FlyRandom.doubleRandom(-100, 100, 2));
        }
    }

    @Test
    public void doubleRandom2() {
        for (int i = -100; i < 100; i++) {
            System.out.println(FlyRandom.doubleRandom(100));
        }
    }

    @Test
    public void doubleRandom3() {
        for (int i = -100; i < 100; i++) {
            System.out.println(FlyRandom.doubleRandom(-100, 100));
        }
    }

    @Test
    public void doubleRandom4() {
        for (int i = -100; i < 100; i++) {
            System.out.println(FlyRandom.doubleRandom(-100, 100, 2, 3));
        }
    }

    @Test
    public void character() {
        for (int i = 0; i < 100; i++) {
            Character character = FlyRandom.character();
            System.out.println(character);
        }
    }

    @Test
    public void character1() {
        for (int i = 0; i < 100; i++) {
            Character character = FlyRandom.character("number");
            System.out.println(character);
        }
    }

    @Test
    public void string() {
        for (int i = 0; i < 100; i++) {
            System.out.println(FlyRandom.string());
        }
    }

    @Test
    public void string1() {
        for (int i = 0; i < 100; i++) {
            System.out.println(FlyRandom.string(5));
        }
    }

    @Test
    public void string2() {
        for (int i = 0; i < 100; i++) {
            System.out.println(FlyRandom.string("abc", 3));
        }
    }

    @Test
    public void string3() {
        for (int i = 0; i < 100; i++) {
            System.out.println(FlyRandom.string("c", 1, 3));
        }
    }

    @Test
    public void string4() {
        for (int i = 0; i < 100; i++) {
            System.out.println(FlyRandom.string(1, 1));
        }
    }

    @Test
    public void range() {
        System.out.println(FlyRandom.range(10));
    }

    @Test
    public void range1() {
        System.out.println(FlyRandom.range(5, 20));
    }

    @Test
    public void range2() {
        System.out.println(FlyRandom.range(2, 20, 4));
    }

    @Test
    public void date() {
        System.out.println(FlyRandom.date());
    }

    @Test
    public void time() {
        System.out.println(FlyRandom.time());
    }

    @Test
    public void datetime() {
        System.out.println(FlyRandom.datetime());
    }

    @Test
    public void datetime1() {
        System.out.println(FlyRandom.datetime("yyyy.MM.dd G 'at' HH:mm:ss z"));
    }

    @Test
    public void now() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            System.out.println(FlyRandom.now());
        }
    }

    @Test
    public void now1() {
        for (int i = 0; i < 10; i++) {
            System.out.println(FlyRandom.now("hour"));
        }
    }

    @Test
    public void now2() {
        for (int i = 0; i < 10; i++) {
            System.out.println(FlyRandom.now("hour", "yyyy-MM-dd HH:mm:ss"));
        }
    }

    @Test
    public void end() {
        for (int i = 0; i < 10; i++) {
            System.out.println(FlyRandom.end("hour", "yyyy-MM-dd HH:mm:ss"));
        }
    }

    @Test
    public void end1() {
        for (int i = 0; i < 10; i++) {
            System.out.println(FlyRandom.end("day"));
        }
    }

    @Test
    public void color() {
        for (int i = 0; i < 10; i++) {
            System.out.println(FlyRandom.color());
        }
    }

    @Test
    public void hex() {
        for (int i = 0; i < 10; i++) {
            System.out.println(FlyRandom.hex());
        }
    }

    @Test
    public void rgb() {
        for (int i = 0; i < 10; i++) {
            System.out.println(FlyRandom.rgb());
        }
    }

    @Test
    public void paragraph() {
        for (int i = 0; i < 10; i++) {
            System.out.println(FlyRandom.paragraph());
        }
    }

    @Test
    public void paragraph1() {
        for (int i = 0; i < 10; i++) {
            System.out.println(FlyRandom.paragraph(2));
        }
    }

    @Test
    public void paragraph2() {
        for (int i = 0; i < 10; i++) {
            System.out.println(FlyRandom.paragraph(2, 10));
        }
    }

    @Test
    public void sentence() {
        for (int i = 0; i < 10; i++) {
            System.out.println(FlyRandom.sentence());
        }
    }

    @Test
    public void sentence1() {
        for (int i = 0; i < 10; i++) {
            System.out.println(FlyRandom.sentence(3));
        }
    }

    @Test
    public void sentence2() {
        for (int i = 0; i < 10; i++) {
            System.out.println(FlyRandom.sentence(10, 15));
        }
    }

    @Test
    public void word() {
        for (int i = 0; i < 10; i++) {
            System.out.println(FlyRandom.word());
        }
    }

    @Test
    public void word1() {
        for (int i = 0; i < 10; i++) {
            System.out.println(FlyRandom.word(10));
        }
    }

    @Test
    public void word2() {
        for (int i = 0; i < 10; i++) {
            System.out.println(FlyRandom.word(1, 3));
        }
    }

    @Test
    public void word3() {
        for (int i = 0; i < 10; i++) {
            System.out.println(FlyRandom.word(true));
        }
    }

    @Test
    public void title() {
        for (int i = 0; i < 10; i++) {
            System.out.println(FlyRandom.title());
        }
    }

    @Test
    public void title1() {
        for (int i = 0; i < 10; i++) {
            System.out.println(FlyRandom.title(1));
        }
    }

    @Test
    public void title2() {
        for (int i = 0; i < 10; i++) {
            System.out.println(FlyRandom.title(2, 3));
        }
    }

    @Test
    public void cparagraph() {
        for (int i = 0; i < 10; i++) {
            System.out.println(FlyRandom.cparagraph());
        }
    }

    @Test
    public void cparagraph1() {
        for (int i = 0; i < 10; i++) {
            System.out.println(FlyRandom.cparagraph(1));
        }
    }

    @Test
    public void cparagraph2() {
        for (int i = 0; i < 10; i++) {
            System.out.println(FlyRandom.cparagraph(1, 10));
        }
    }

    @Test
    public void csentence() {
    }

    @Test
    public void csentence1() {
    }

    @Test
    public void csentence2() {
    }

    @Test
    public void cword() {
        for (int i = 0; i < 10; i++) {
            System.out.println(FlyRandom.cword());
        }
    }

    @Test
    public void cword1() {
        for (int i = 0; i < 10; i++) {
            System.out.println(FlyRandom.cword("我是谁"));
        }
    }

    @Test
    public void cword2() {
        for (int i = 0; i < 10; i++) {
            System.out.println(FlyRandom.cword(10));
        }
    }

    @Test
    public void cword3() {
        for (int i = 0; i < 10; i++) {
            System.out.println(FlyRandom.cword(1, 2));
        }
    }

    @Test
    public void cword4() {
        for (int i = 0; i < 10; i++) {
            System.out.println(FlyRandom.cword("我是谁", 2));
        }
    }

    @Test
    public void cword5() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(FlyRandom.cword(null, 2, 10));
        }
    }

    @Test
    public void ctitle() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(FlyRandom.ctitle());
        }
    }

    @Test
    public void ctitle1() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(FlyRandom.ctitle(2));
        }
    }

    @Test
    public void ctitle2() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(FlyRandom.ctitle(2));
        }
    }

    @Test
    public void first() {
        for (int i = 0; i < 10; i++) {
            System.out.println(FlyRandom.first());
        }
    }

    @Test
    public void last() {
        for (int i = 0; i < 10; i++) {
            System.out.println(FlyRandom.last());
        }
    }

    @Test
    public void name() {
        for (int i = 0; i < 10; i++) {
            System.out.println(FlyRandom.name());
        }
    }

    @Test
    public void cfirst() {
        for (int i = 0; i < 10; i++) {
            System.out.println(FlyRandom.cfirst());
        }
    }

    @Test
    public void clast() {
        for (int i = 0; i < 10; i++) {
            System.out.println(FlyRandom.clast());
        }
    }

    @Test
    public void cname() {
        for (int i = 0; i < 10; i++) {
            System.out.println(FlyRandom.cname());
        }
    }

    @Test
    public void url() {
        for (int i = 0; i < 10; i++) {
            System.out.println(FlyRandom.url());
        }
    }

    @Test
    public void domain() {
        for (int i = 0; i < 10; i++) {
            System.out.println(FlyRandom.domain());
        }
    }

    @Test
    public void protocol() {
        for (int i = 0; i < 10; i++) {
            System.out.println(FlyRandom.protocol());
        }
    }

    @Test
    public void tld() {
        for (int i = 0; i < 10; i++) {
            System.out.println(FlyRandom.tld());
        }
    }

    @Test
    public void email() {
        for (int i = 0; i < 10; i++) {
            System.out.println(FlyRandom.email());
        }
    }

    @Test
    public void ip() {
        for (int i = 0; i < 10; i++) {
            System.out.println(FlyRandom.ip());
        }
    }

    @Test
    public void mobile() {
        for (int i = 0; i < 10; i++) {
            System.out.println(FlyRandom.mobile());
        }
    }

    @Test
    public void region() {
        for (int i = 0; i < 10; i++) {
            System.out.println(FlyRandom.region());
        }
    }

    @Test
    public void province() {
        for (int i = 0; i < 10; i++) {
            System.out.println(FlyRandom.province());
        }
    }

    @Test
    public void city() {
        for (int i = 0; i < 10; i++) {
            System.out.println(FlyRandom.city());
        }
    }

    @Test
    public void city1() {
        for (int i = 0; i < 10; i++) {
            System.out.println(FlyRandom.city(true));
        }
    }

    @Test
    public void county() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
//            Integer natural = FlyRandom.natural(1, 4);
//            System.out.println("暂停" + natural);
//            Thread.sleep(natural * 1000);
            System.out.println(FlyRandom.county());
        }
    }

    @Test
    public void county1() {
        for (int i = 0; i < 10; i++) {
            System.out.println(FlyRandom.county(true));
        }
    }

    @Test
    public void zip() {
        for (int i = 0; i < 10; i++) {
            System.out.println(FlyRandom.zip());
        }
    }

    @Test
    public void capitalize() {
        System.out.println(FlyRandom.capitalize("wanghouping"));
    }

    @Test
    public void upper() {
        System.out.println(FlyRandom.upper("wanghouping"));
    }

    @Test
    public void lower() {
        System.out.println(FlyRandom.lower("waNGHOUping"));
    }

    @Test
    public void pick() {
        for(int i = 0; i < 10; i ++) {
            System.out.println(FlyRandom.pick(Arrays.asList("wang", "hou", "ping")));
        }
    }

    @Test
    public void pick1() {
        String[] args = {"wang", "hou", "ping"};
        for(int i = 0; i < 10; i ++) {
            System.out.println(FlyRandom.pick(args));
        }
    }

    @Test
    public void shuffle() {
        String[] args = {"wang", "hou", "ping"};
        for(int i = 0; i < 10; i ++) {
            System.out.println(FlyRandom.shuffle(args));
        }
    }

    @Test
    public void shuffle1() {
        for(int i = 0; i < 10; i ++) {
            System.out.println(FlyRandom.shuffle(Arrays.asList("wang", "hou", "ping")));
        }
    }

    @Test
    public void guid() {
        for (int i = 0; i < 10; i++) {
            System.out.println(FlyRandom.guid());
        }
    }

    @Test
    public void guid1() {
        for (int i = 0; i < 10; i++) {
            System.out.println(FlyRandom.guid("upper"));
        }
    }

    @Test
    public void id() {
        for (int i = 0; i < 10; i++) {
            System.out.println(FlyRandom.id());
        }
    }

    @Test
    public void increment() {
        for (int i = 0; i < 10; i++) {
            System.out.println(FlyRandom.increment());
        }
    }

    @Test
    public void idcard() {
        for (int i = 0; i < 10; i++) {
            System.out.println(FlyRandom.idCard());
        }
    }

    @Test
    public void mac() {
        for (int i = 0; i < 10; i++) {
            System.out.println(FlyRandom.mac());
        }
    }


}