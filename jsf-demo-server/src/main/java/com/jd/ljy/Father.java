package com.jd.ljy;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Father {
    private String name;
    private Integer age;
    protected String school = "BUPT";

    public Father() {
    }

    public Father(String name, Integer age, String school) {
        this.name = name;
        this.age = age;
        this.school = school;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    public void show(){
        System.out.println("name:"+name+",age:"+age);
    }

}
