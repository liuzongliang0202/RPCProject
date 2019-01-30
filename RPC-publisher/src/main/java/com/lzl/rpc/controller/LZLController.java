package com.lzl.rpc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lzl.rpc.domain.Person;
import com.lzl.rpc.service.QueryPersonnelInfo;

@Controller
@ResponseBody
@RequestMapping("/rpc")
public class LZLController
{
    @Autowired
    private QueryPersonnelInfo queryPersonnelInfo;
    @RequestMapping("/queryInfo")
    public String queryPersonneInfo(@RequestParam("person") Person person) {
        String personInfo = queryPersonnelInfo.query(person.getName(), person.getAge());
        return personInfo; 
    }
    @RequestMapping("/query")
    public String queryInfo(@RequestParam("name") String name,@RequestParam("age") int age) {
        String personInfo = queryPersonnelInfo.query(name, age);
        return personInfo;      
    }
}
