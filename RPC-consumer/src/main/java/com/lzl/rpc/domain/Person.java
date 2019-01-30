package com.lzl.rpc.domain;

import java.io.Serializable;

public class Person implements Serializable
{
    /**
     * TODO 添加字段注释
     */
    private static final long serialVersionUID = 1L;
    private String name;
    private int age;
    private String address;
    private String grade;
    private String education;
    private String mobilephoneNumber;
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public int getAge()
    {
        return age;
    }
    public void setAge(int age)
    {
        this.age = age;
    }
    public String getAddress()
    {
        return address;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }
    public String getGrade()
    {
        return grade;
    }
    public void setGrade(String grade)
    {
        this.grade = grade;
    }
    public String getEducation()
    {
        return education;
    }
    public void setEducation(String education)
    {
        this.education = education;
    }
    public String getMobilephoneNumber()
    {
        return mobilephoneNumber;
    }
    public void setMobilephoneNumber(String mobilephoneNumber)
    {
        this.mobilephoneNumber = mobilephoneNumber;
    }
    @Override
    public String toString()
    {
        return "Person [name=" + name + ", age=" + age + ", address=" + address + ", grade=" + grade + ", education="
            + education + ", mobilephoneNumber=" + mobilephoneNumber + "]";
    }
}
