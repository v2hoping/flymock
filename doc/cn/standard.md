默认支持数据占位符，会使用特定条件的数据随机生成替换占位符。

## 数据占位符定义

### @boolean()
随机生成一个布尔值，50%概率生成true
``` java
Mock.mock("@boolean()"); --> true
```
随机生成一个布尔值，值为 value 的概率是 min / (min + max)，值为 !value 的概率是 max / (min + max)。
``` java
Mock.mock("@boolean(1,9,true)"); --> false
```

### @natural()
随机生成一个自然数，Integer类型
``` java
Mock.mock("@natural()"); --> 1530974618
```
随机生成一个自然数，Integer类型，大于等于min
``` java
Mock.mock("@natural(10000)"); --> 1784700884
```
随机生成一个自然数，Integer类型，大于等于min，小于等于max
``` java
Mock.mock("@natural(60,100)"); --> 90
```

### @integer()
随机生成一个整数，Integer类型
``` java
Mock.mock("@integer()"); --> -1071498475
```
随机生成一个整数，Integer类型，大于等于min
``` java
Mock.mock("@integer(10000)"); --> 116638338
```
随机生成一个整数，Integer类型，大于等于min，小于等于max
``` java
Mock.mock("@integer(60,100)"); --> 76
```

### @float()
随机生成一个小数，Double类型
``` java
Mock.mock("@integer()"); --> 28193.379
```
随机生成一个小数，Double类型，整数部分大于等于min
``` java
Mock.mock("@integer(0)"); --> 1717.48335
```
随机生成一个小数，Double类型，整数部分大于等于min
``` java
Mock.mock("@integer(60,100)"); --> 95.845917
```
随机生成一个小数，Double类型，整数部分大于等于min,小于等于max，小数部分大于等于dmin位
``` java
Mock.mock("@integer(60,100,3)"); --> 76.67858
```
随机生成一个小数，Double类型，整数部分大于等于min,小于等于max，小数部分大于等于dmin位，小于等于dmax位
``` java
print("@float(60,100,3,5)"); --> 75.8508
```

### @character()
随机生成一个字符，Character类型，包含数字、大小写字母、特殊符号
``` java
Mock.mock("@character()"); --> j
```
随机生成一个字符，Character类型，小写字母
``` java
Mock.mock("@character(lower)"); --> p
```
随机生成一个字符，Character类型，大写英文字符
``` java
Mock.mock("@character(upper)"); --> B
```
随机生成一个字符，Character类型，数字字符
``` java
Mock.mock("@character(number)"); --> 7 
```
随机生成一个字符，Character类型，特殊字符
``` java
Mock.mock("@character(symbol)"); --> [
```
随机生成一个字符，Character类型，在范围字符数组中的字符
``` java
Mock.mock("@character(aeiou)"); --> u
```

### @string()
随机生成一个字符串，String类型，包含数字、大小写字母、特殊符号
``` java
Mock.mock("@string()"); --> lolDykv
```
随机生成一个字符串，String类型，生成count个字符，包含数字、大小写字母、特殊符号
``` java
Mock.mock("@string(5)"); --> dhVah
```
随机生成一个字符串，String类型，大于等于min，小于等于max的数字、大小写字母、特殊符号 
``` java
Mock.mock("@string(7,10)"); --> X4DWS9TxYB
```
随机生成一个字符串，String类型，生成count个小写字符
``` java
Mock.mock("@string(lower,5)"); --> pnifr
```
随机生成一个字符串，String类型，生成count个大写数字字符
``` java
Mock.mock("@string(upper,5)"); --> PJTZT
```
随机生成一个字符串，String类型，生成count个数字
``` java
Mock.mock("@string(number,5)"); --> 89785
```
随机生成一个字符串，String类型，生成count个特殊字符
``` java
Mock.mock("@string(symbol,5)"); --> ))#*$
```
随机生成一个字符串，String类型，生成count个字符池中的字符
``` java
Mock.mock("@string(aeiou,5)"); --> aauuo
```
随机生成一个字符串，String类型，生成等于小于min，大于等于max个字符池中的字符
``` java
Mock.mock("@string(aeiou,1,3)"); --> eo
```

### @range()
随机生成一个数组，Array类型，生成步长为1，递增至小于count的count个数组元素
``` java 
Mock.mock("@range(10)"); ——> [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
```
随机生成一个数组，Array类型，生成大于等于min，小等于max的max-min个数组元素
``` java
Mock.mock("@range(3,7)"); ——> [3, 4, 5, 6]
```
随机生成一个数组，Array类型，生成大于等于min，小等于max的(max-min)/step个数组元素
``` java
Mock.mock("@range(1,10,2)"); ——> [1, 3, 5, 7, 9]
```

### @date() 
生成当前时间的年月日时间，String类型
``` java 
Mock.mock("@date()"); ——> 2020-02-01
```

### @time
``` java 
Mock.mock("@time()"); ——> 19:34:17
```

### @datetime()
生成年-月-日 时:分:秒格式的当前时间，String类型
``` java 
Mock.mock("@datetime()"); ——> 2020-03-20 19:38:06
```

生成一个指定格式的当前时间，String类型
``` java 
Mock.mock("@datetime(yy-MM-dd a HH:mm:ss)"); ——> 20-03-20 下午 19:38:06
```

### @now()
生成当前时间，Date类型
``` java 
Mock.mock("@now()"); ——> Fri Mar 20 20:01:39 CST 2020
```
生成当前时间，Date类型，年的开始
``` java 
Mock.mock("@now(year)"); ——> Wed Jan 01 00:00:00 CST 2020
```
生成当前时间，Date类型，月的开始
``` java 
Mock.mock("@now(month)"); ——> Sun Mar 01 00:00:00 CST 2020
```
生成当前时间，Date类型，周的开始
``` java 
Mock.mock("@now(week)"); ——> Mon Mar 16 00:00:00 CST 2020
```
生成当前时间，Date类型，天的开始
``` java 
Mock.mock("@now(day)"); ——> Fri Mar 20 00:00:00 CST 2020
```
生成当前时间，Date类型，时的开始
``` java 
Mock.mock("@now(hour)"); ——> Fri Mar 20 20:00:00 CST 2020
```
生成当前时间，Date类型，分的开始
``` java 
Mock.mock("@now(minute)"); ——> Fri Mar 20 20:00:00 CST 2020
```
生成当前时间，Date类型，秒的开始
``` java 
Mock.mock("@now(second)"); ——> Fri Mar 20 20:01:01 CST 2020
```

### @color()
生成一个color，String类型
``` java 
Mock.mock("@color()"); ——> #f02176
```

### @hex()
生成一个hex，String类型
``` java 
Mock.mock("@hex()"); ——> #f02176
```

### @rgb()
生成一个RGB，String类型
``` java 
Mock.mock("@rgb()"); ——> rgb(239, 121, 242)
```

### @paragraph()
生成一段英文文章，String类型
``` java 
Mock.mock("@paragraph()"); ——>Opzjl bryzngso fxytmxpkn. ijiakotvhx zdylvlhrd. qjonm mpnu.  
```
生成一段英文文章，String类型，count个段落
``` java 
Mock.mock("@paragraph(2)"); ——> ohnemho kranbp.whnbjdgtu tcgvs fpurfsi ncq roqilepiy byzlvptfk tnet. 
```
生成一段英文文章，String类型，小于等于min，大于等于max个段落
``` java 
Mock.mock("@paragraph(1,3)"); ——> Getogmxk  psdthhqev vtcirx wzpyjgfg swhi ybqzaxyagh ynefgn uejdwbunna sqwkwlnt pjr twvbvvg.
```

### @sentence()
生成一段英文句子，String类型
``` java 
Mock.mock("@sentence()"); ——> Wavdwmw wjizmmmzt juqa gtxhqicfqt jmcbnswgqj mbb fdecwqyeww wyxuojzey clowf qkewipq bciy nirndyu.
```
生成一段英文句子，String类型，count个单词
``` java 
Mock.mock("@sentence(3)"); ——> Zblnv lapbpo qdw.
```
生成一段英文句子，String类型，小于等于min，大于等于max个单词
``` java 
Mock.mock("@sentence(3,5)"); ——> Jtfaovnu svylmurx agr.
```

### @word()
生成一个英文单词，String类型
``` java 
Mock.mock("@word()"); ——> vmhicyvm
```
生成一个英文单词，String类型，count个字母
``` java 
Mock.mock("@word(5)"); ——> ltxmd
```
生成一个英文单词，String类型，小于等于min，大于等于max个字母
``` java 
Mock.mock("@word(3,5)"); ——> roqu
```

### @title()
生成一个英文标题，String类型，首字母大写
``` java 
Mock.mock("@title()"); ——> Hpe Jitqm Pcylwl Sgfvth Nnucz Zbukkdi
```
生成一个英文标题，String类型，count个单词，首字母大写
``` java 
Mock.mock("@title(5)"); ——> Yjcied Bqjk Exxu
```
生成一个英文标题，String类型，小于等于min，大于等于max，首字母大写
``` java 
Mock.mock("@title(3,5)"); ——> Cleojot Tnlwmpt Mnsx
```

### @cparagraph()

生成一段中文文章，String类型
``` java 
Mock.mock("@cparagraph()"); ——> 消从次成研统事权想受从两。积战么听划联土法斗统例教。约委线己目成在结越厂性感领。完高生条机线安。厂提机了响越。算即平能。记包约如史时。
```
生成一段中文段落，String类型，count个段落
``` java 
Mock.mock("@cparagraph(2)"); ——> 精才色置说物那常导什低志间产。最当持资则声便间写而京这方身确。
```
生成一段中文段落，String类型，小于等于min，大于等于max个段落
``` java 
Mock.mock("@cparagraph(3,5)"); ——> 象界小那车传强金满角步强。团件存团器与进从果备则技术线群。商料很白金话并即少主线志越斯。
```

### @csentence()
生成一段中文标题，String类型
``` java 
Mock.mock("@csentence()"); ——> 业律战政发各件拉京组子这老解。更次果被他对新强己国转型。响最金务则院成资土设起体按题前完。
```
生成一段中文标题，String类型，count个单词
``` java 
Mock.mock("@csentence(3)"); ——> 做者家样己。
```
生成一段中文标题，String类型，小于等于min，大于等于max个单词
``` java 
Mock.mock("@csentence(3,5)"); ——> 声细各验。
```

### @cword()
生成一个词语，包含一个汉字，String类型
``` java 
Mock.mock("@cword()"); ——> 计
```
生成一个词语，String类型，count个汉字
``` java 
Mock.mock("@cword(3)"); ——> 与界美
```
生成一个词语，String类型，小于等于min，大于等于max个汉字
``` java 
Mock.mock("@cword(3,5)"); ——> 能非表
```
生成一个词语，String类型，从字符池中随机选取汉字
``` java 
Mock.mock("@cword(零一二三四五六七八九十)"); ——> 五
```
生成一个词语，String类型，从字符池中随机选取N个汉字
``` java 
Mock.mock("@cword(零一二三四五六七八九十,3)"); ——> 五三三
```
生成一个词语，String类型，小于等于min，大于等于max个汉字
``` java 
Mock.mock("@cword(零一二三四五六七八九十,3,5)"); ——> 三一九五四
```

### @ctitle()
生成一段标题，String类型，产生随机个词语 
``` java 
Mock.mock("@ctitle()"); ——> 有权治 务保示做称 口根光点史备们 头并样应的级
```
生成一段标题，String类型，产生N个词语
``` java 
Mock.mock("@ctitle(3)"); ——> 重号期所并克 去之又成海 广样据建
```
生成一段标题，String类型，产生大于等于min，小于等于max个词语
``` java 
Mock.mock("@ctitle(3,5)"); ——> 花和毛 农音叫什认 们统广 真般连好 产效过半约导维
```

### first()
生成英文姓氏，String类型
