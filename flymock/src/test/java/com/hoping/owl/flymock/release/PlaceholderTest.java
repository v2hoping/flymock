package com.hoping.owl.flymock.release;

import com.hoping.owl.flymock.Mock;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by houping wang on 2019/4/16
 *
 * @author houping wang
 */
public class PlaceholderTest {

    @Test
    public void booleanTest() {
        Object var = print("@boolean()");
        Assert.assertTrue(var instanceof Boolean);
        Object var1 = print("@boolean(1,9,true)");
        Assert.assertTrue(var1 instanceof Boolean);
    }

    @Test
    public void cfirst() {
        Object print = print("@cfirst()");
        Assert.assertTrue(print instanceof String);
    }

    @Test
    public void character() {
        print("@character()");
        print("@character(lower)");
        print("@character(upper)");
        print("@character(number)");
        print("@character(symbol)");
        print("@character(aeiou)");
    }

    @Test
    public void city() {
        print("@city()");
        print("@city(true)");
    }

    @Test
    public void clast() {
        print("@clast()");
    }

    @Test
    public void cname() {
        print("@cname()");
    }

    @Test
    public void color() {
        print("@color()");
    }

    @Test
    public void county() {
        print("@county()");
        print("@county(true)");
    }

    @Test
    public void cparagraph() {
        print("@cparagraph()");
        print("@cparagraph(2)");
        print("@cparagraph(1,3)");
    }

    @Test
    public void csentence(){
        print("@csentence()");
        print("@csentence(5)");
        print("@csentence(3,5)");
    }

    @Test
    public void ctitle() {
        print("@ctitle()");
        print("@ctitle(3)");
        print("@ctitle(3,5)");
    }

    @Test
    public void cword() {
        print("@cword()");
        print("@cword(3)");
        print("@cword(零一二三四五六七八九十)");
        print("@cword(零一二三四五六七八九十,3)");
        print("@cword(3,5)");
        print("@cword(零一二三四五六七八九十,5,7)");
    }

    @Test
    public void date() {
        print("@date()");
    }

    @Test
    public void datetime() {
        print("@datetime(yyyy-MM-dd HH:mm:ss)");
        print("@datetime(yy-MM-dd a HH:mm:ss)");
        print("@datetime(y-MM-dd HH:mm:ss)");
        print("@datetime(y-M-d H:m:s)");
        print("@datetime(HH:mm:ss)");
        print("@datetime()");
    }

    @Test
    public void domain() {
        print("@domain()");
    }

    @Test
    public void email() {
        print("@email()");
    }

    @Test
    public void enumTest() {
        print("@enum(A,B,C)");
    }

    @Test
    public void first() {
        print("@first()");
    }

    @Test
    public void floatTest() {
        print("@float()");
        print("@float(0)");
        print("@float(60,100)");
        print("@float(60,100,3)");
        print("@float(60,100,3,5)");

    }

    @Test
    public void guid() {
        print("@guid()");
    }

    @Test
    public void hex() {
        print("@hex()");
    }

    @Test
    public void id() {
        print("@id()");
    }

    @Test
    public void increment() {
        print("@increment()");
    }

    @Test
    public void integer() {
        print("@integer()");
        print("@integer(10000)");
        print("@integer(60,100)");
    }

    @Test
    public void ip(){
        print("@ip()");
    }

    @Test
    public void last() {
        print("@last()");
    }

    @Test
    public void name() {
        print("@name()");
    }

    @Test
    public void natural() {
        print("@natural()");
        print("@natural(10000)");
        print("@natural(60,100)");
    }

    @Test
    public void now() {
        print("@now()");
        print("@now(year)");//年的开始
        print("@now(month)");//月的开始
        print("@now(week)");//周的开始
        print("@now(day)");//日的开始
        print("@now(hour)");//时的开始
        print("@now(minute)");//分的开始
        print("@now(second)");//秒的开始
    }

    @Test
    public void paragraph() {
        print("@paragraph()");
        print("@paragraph(2)");
        print("@paragraph(1,3)");
    }

    @Test
    public void protocol() {
        print("@protocol()");
    }

    @Test
    public void province() {
        print("@province()");
    }

    @Test
    public void range() {
        print("@range(10)");
        print("@range(3,7)");
        print("@range(1,10,2)");
    }

    @Test
    public void region() {
        print("@region()");
    }

    @Test
    public void rgba() {
        print("@rgba()");
    }

    @Test
    public void rgb() {
        print("@rgb()");
    }

    @Test
    public void sentence(){
        print("@sentence()");
        print("@sentence(3)");
        print("@sentence(3,5)");
    }

    @Test
    public void string() {
        print("@string()");
        print("@string(5)");
        print("@string(7,10)");
        print("@string(lower,5)");
        print("@string(upper,5)");
        print("@string(number,5)");
        print("@string(symbol,5)");
        print("@string(aeiou,5)");
        print("@string(aeiou,1,3)");
    }

    @Test
    public void time() {
        print("@time()");
    }

    @Test
    public void timestamp() {
        print("@timestamp()");
    }

    @Test
    public void title() {
        print("@title()");
        print("@title(3)");
        print("@title(3,5)");
    }

    @Test
    public void tld() {
        print("@tld()");
    }

    @Test
    public void url() {
        print("@url()");
    }

    @Test
    public void word() {
        print("@word()");
        print("@word(5)");
        print("@word(3,5)");
    }

    @Test
    public void zip() {
        print("@zip()");
    }

    private Object print(Object o) {
        Object mock = Mock.mock(o);
        System.out.println(mock);
        Assert.assertNotEquals(mock, o);
        return mock;
    }
}

