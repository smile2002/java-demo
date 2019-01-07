package cn.smile.domain;

/**
 * Created by Smile on 2018/7/19.
 */
public class User {
    private String userId;
    private Integer age;
    private String name;

    public String getUserId() {return this.userId;}
    public Integer getAge() {return this.age;}
    public String getName() {return this.name;}

    public void setUserId(String userId) {this.userId = userId;}
    public void setAge(Integer age) {this.age = age;}
    public void setName(String name) {this.name = name;}
}