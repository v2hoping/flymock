package com.hoping.owl;

/**
 * Created by houping wang on 2019/5/10
 *
 * @author houping wang
 */
public class User {

    private String name;

    private Integer age;

    private String[] describes;

    private String email;

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String[] getDescribes() {
        return describes;
    }

    public void setDescribes(String[] describes) {
        this.describes = describes;
    }
}
