[English](https://github.com/v2hoping/flymock/blob/master/doc/english/Readme.md)|[中文](https://github.com/v2hoping/flymock/blob/master/README.md)

# flymock

Flymock is a Java class library that can generate mock data objects directly from class. It can also convert class into mock data templates, and then generate JSON data from mock data templates. Flymock can support a wide range of Java types, including basic types, complex objects, generics, enum, nested generics, and so on.

## Flymock goal

* Provide direct generation of mock Java object from types, reducing new object and setting value in unit test
* Provide simple template() and mock() methods to convert from type to mock data template and directly from type to mock Java object
* Wide-ranging support for Java types, including Java generics
* Provide 7 Format Generation Rules
* Provide multiple placeholders, such as @cparagraph (), @email (), @ip (), @province (), allowing custom placeholders to be quickly set through the @FiledMock annotation
* Support for arbitrarily complex objects (including deep nesting and generic nesting, arrays)
* Support global simulation rules for fields. Personalized and authentic simulation data can be defined according to the organization.(X)

## Spring-boot-starter-flymock goal

* Provide SpringBoot Starter，fast integration with SpringBoot
* Provide@EnableFlyMock and spring.fly.mock.enable parameter control Starter
* Support interception classe and method to return mock Java object based on SpringBoot Aop and @FlyMock annotation

## Maven Setting

```xml
<dependency>
   <groupId>com.hoping.owl</groupId>
   <artifactId>flymock</artifactId>
   <version>0.1.0</version>
</dependency>
```

```xml
<dependency>
   <groupId>com.hoping.owl</groupId>
   <artifactId>spring-boot-starter-flymock</artifactId>
   <version>0.1.0</version>
</dependency>
```

## Documentation

* Data Template Definition Specification
* Data Template Generation Rules
* Default Placeholder Rule
* Project Structure Information
* Api Documentation

## Transformation relation

Java Type------>Java template data------>JSON data<------>Java object

## Usage

#### Java type generate Java object

```java
//Basic type，for example：Integer、Date etc
Integer integerValue = TemplateMock.mock(new TypeReference<Integer>() {});
Date dateValue = TemplateMock.mock(new TypeReference<Date>(){});
```

```java
//Nested complex objects, for example: User
User userValue = TemplateMock.mock(new TypeReference<User>() {});
```

```java
//Generic object，for example：List<Stirng>
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
         * Name of Education
         */
        private String name;

        /**
         * Education time
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

### Java type generate JSON data

```java
String userJson = TemplateMock.json(new TypeReference<User>() {});
```

### Java type generate Java template data

```java
Template<User> template = TemplateMock.template(new TypeReference<User>() {});
```

### Java template data generate JSON data

```java
Template<User> template = TemplateMock.template(new TypeReference<User>() {});
String userJson = template.mockToJson();
```

### Java template data generate Java object

```java
Template<User> template = TemplateMock.template(new TypeReference<User>() {});
User user = template.mockType();
```

### Java template data generate Java object

```java
String infoTemplateJson = "{\"name\":\"@string()\",\"educations\":[{\"date\":1557457171390,\"name\":\"vhJ\"}]}";
Template<User> template = new Template<>(infoTemplateJson, new TypeReference<User>(){});
User user = template.mockType();
```

### Sett generation strategy and placeholder

```java
public class Room {
    
    @FiledMock(strategy = "2", value = "email is:@email()")
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
Room user = template.mockType();//{"name":"email is:ahu@163.comemail is:ahu@163.com","educations":[{"date":1557454313538,"name":"nz8k"}]}
```

### Custom placeholder

```java
public static class MoviePlaceholderHandle extends AbstractPlaceholderHandle<String> {

    @Override
    public String invoke(PlaceholderWrap placeholderWrap) {
        List<String> args = placeholderWrap.getArgs();
        if(args.size() == 0) {
            return  "《Iron Man》";
        }else{
            return  "《Iron Man" + args.get(0) +"》";
        }
    }

    @Override
    public String key() {
        return "movie";
    }
}
```

```java
//add the movie placeholder processor
static {
    Mock.put(new MoviePlaceholderHandle());
}
```

```java
public class Info {

    @FiledMock(strategy = "2", value = "movie is:@movie(2)")
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
Info user = template.mockType();//{"name":"movie is:《Iron Man2》movie :《Iron Man2》","educations":[{"date":1557456720233,"name":"wdDK9"}]}
```

## Spring-boot-starter-flymock Usage

### Enable starter

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

### Intercept and mock all method return value of a class

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

### Intercept and simulate the return value of a single method

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

### SpringBoot Custom placeholder

```java
@Component
public static class MoviePlaceholderHandle extends AbstractPlaceholderHandle<String> {

    @Override
    public String invoke(PlaceholderWrap placeholderWrap) {
        List<String> args = placeholderWrap.getArgs();
        if(args.size() == 0) {
            return  "《Iron Man》";
        }else{
            return  "《Iron Man" + args.get(0) +"》";
        }
    }

    @Override
    public String key() {
        return "movie";
    }
}
```

## Thank & Contributor

FlyMock refer to Mock.js, and the template JSON specification basically support each other (but not exactly the same), which can be seen as an enhancement and extension of the Java version.
