package com.jiangxb.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @author jiangxiangbo
 * @date 2020/8/1
 * @Description: TODO
 */
public class FastJsonTest {

    public static void main(String[] args) {
        // json转对象
        String jsonStr = "{\"name\":\"张三\"}";
        Student student = JSON.parseObject(jsonStr, Student.class);
        System.out.println("json转对象： "+student.toString());

        // 对象转json
        Student student1 = new Student();
        student1.setName("张三");
        String jsonString = JSONObject.toJSONString(student1);
        System.out.println("对象转json： "+jsonString);

        JSON.parseObject(jsonString, Student.class);
    }

}

class Student {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // 无Grade属性
    public Integer getGrade() {
        return 100;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }

}

