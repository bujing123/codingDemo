package com.jd.ljy;

public class Son extends Father{
    private String study;

    public String getStudy() {
        return study;
    }

    public void setStudy(String study) {
        this.study = study;
    }

    public void updateSchool(String school){
        this.school = school;
    }

    public void printSchool(){
        System.out.println("school:"+super.getSchool());
    }
}
