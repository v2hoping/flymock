English|中文

# flymock

flymock是一个可以将类直接生成模拟数据对象的java类库。它还可以将类转换为模拟数据模板，再由模拟数据模板生成JSON数据。flymock可以支持广泛的java类型，包含基本类型、复杂对象、泛型、枚举、嵌套泛型等。同时支持字段生成策略和多种占位符。

## Flymock目标

* 提供直接由类型生成模拟Java对象，减少单元测试中new对象和设置值
* 提供简单的template()和mock()方法，由类型转换为模拟数据模板和由类型直接转为模拟Java对象
* 广泛的支持Java类型，包括Java泛型
* 支持7种格式生成规则
* 支持多样的占位符，例如@cparagraph()、@email()、@ip()、@province()，允许自定义占位符，可通过@FiledMock注解快速设置
* 支持任意复杂的对象(包括深层嵌套和泛型嵌套、数组)
* 支持字段全局模拟规则，可根据组织情况，定义个性化、真实性高的模拟数据(X)

## Spring-boot-starter-flymock目标

* 提供SpringBoot starter，与SpringBoot快速集成
* 提供@EnableFlyMock和spring.fly.mock.enable参数来开关Stater
* 基于SpringBoot Aop提供和@FlyMock注解，支持拦截类、拦截方法返回模拟Java对象

## Maven Setting

```xml
<dependency>
   <groupId>com.v2hoping</groupId>
   <artifactId>flymock</artifactId>
   <version>0.1.1</version>
</dependency>
```

```xml
<dependency>
   <groupId>com.v2hoping</groupId>
   <artifactId>spring-boot-starter-flymock</artifactId>
   <version>0.1.1</version>
</dependency>
```

## 转换关系

Java类型------>Java模板数据------>JSON数据<------>Java对象

## 用法

#### Java类型生成Java对象

```java
//基本类型,举例：Integer、Date等
Integer integerValue = TemplateMock.mock(new TypeReference<Integer>() {});
Date dateValue = TemplateMock.mock(new TypeReference<Date>(){});
```

```java
//嵌套复杂对象,举例：User
User userValue = TemplateMock.mock(new TypeReference<User>() {});
```

```java
//泛型对象，举例：List<Stirng>
List<String> listTValues = TemplateMock.mock(new TypeReference<List<String>>() {});
```

```java
public class User {
    private String name;

    private List<Education> educations;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Education> getEducations() {
        return educations;
    }

    public void setEducations(List<Education> educations) {
        this.educations = educations;
    }

    public static class Education {
        /**
         * 教育名称
         */
        private String name;

        /**
         * 教育时间
         */
        private Date date;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }
    }
}
```

### Java类型生成JSON数据

```java
String userJson = TemplateMock.json(new TypeReference<User>() {});
```

### Java类型生成Java模板数据

```java
Template<User> template = TemplateMock.template(new TypeReference<User>() {});
```

### Java模板数据生成JSON数据

```java
Template<User> template = TemplateMock.template(new TypeReference<User>() {});
String userJson = template.mockToJson();
```

### Java模板数据生成Java对象

```java
Template<User> template = TemplateMock.template(new TypeReference<User>() {});
User user = template.mockType();
```

### Java模板数据字符串生成Java对象

```java
String infoTemplateJson = "{\"name\":\"@string()\",\"educations\":[{\"date\":1557457171390,\"name\":\"vhJ\"}]}";
Template<User> template = new Template<>(infoTemplateJson, new TypeReference<User>(){});
User user = template.mockType();
```

### 设置生成策略和占位符

```java
public class Room {
    
    @FiledMock(strategy = "2", value = "邮箱是:@email()")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```

```java
Template<Room> template = TemplateMock.template(new TypeReference<Room>() {});
Room user = template.mockType();//{"name":"邮箱是:ahu@163.com邮箱是:ahu@163.com","educations":[{"date":1557454313538,"name":"nz8k"}]}
```

### 自定义占位符

```java
public static class MoviePlaceholderHandle extends AbstractPlaceholderHandle<String> {

    @Override
    public String invoke(PlaceholderWrap placeholderWrap) {
        List<String> args = placeholderWrap.getArgs();
        if(args.size() == 0) {
            return  "《钢铁侠》";
        }else{
            return  "《钢铁侠" + args.get(0) +"》";
        }
    }

    @Override
    public String key() {
        return "movie";
    }
}
```

```java
//添加该电影占位符处理器
static {
    Mock.put(new MoviePlaceholderHandle());
}
```

```java
public class Info {

    @FiledMock(strategy = "2", value = "电影是:@movie(2)")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```

```java
Template<Info> template = TemplateMock.template(new TypeReference<Info>() {});
Info user = template.mockType();//{"name":"电影是:《钢铁侠2》电影是:《钢铁侠2》","educations":[{"date":1557456720233,"name":"wdDK9"}]}
```

## Spring-boot-starter-flymock用法

### 开启starter

```java
@SpringBootApplication
@ComponentScan(basePackages = {"com.hoping"})
@EnableFlyMock
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

### 拦截并模拟类的所有方法返回值

```java
@Component
@FlyMock
public class SomeMethod {

    public String sayString() {
        return null;
    }

    public User sayUser() {
        return null;
    }
}
```

```java
String str = someMethod.sayString();
User user = someMethod.sayUser();
```

### 拦截并模拟单个方法返回值

```java
@Component
public class SomeMethod {

    public String sayString() {
        return null;
    }

    @FlyMock
    public User sayUser() {
        return null;
    }
}
```

```java
User user = someMethod.sayUser();
```

### SpringBoot自定义占位符

```java
@Component
public static class MoviePlaceholderHandle extends AbstractPlaceholderHandle<String> {

    @Override
    public String invoke(PlaceholderWrap placeholderWrap) {
        List<String> args = placeholderWrap.getArgs();
        if(args.size() == 0) {
            return  "《钢铁侠》";
        }else{
            return  "《钢铁侠" + args.get(0) +"》";
        }
    }

    @Override
    public String key() {
        return "movie";
    }
}
```

## 更多文档

- [默认支持占位符](doc/chinese/placeholder.md)
- [数据模板定义规范](doc/chinese/standard.md)

## 感谢 & 贡献者

FlyMock参考了Mock.js，模板JSON规范基本互相支持(不完全相同)，可以看做是java版本的增强和扩展.
