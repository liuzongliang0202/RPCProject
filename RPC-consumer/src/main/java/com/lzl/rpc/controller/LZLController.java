package com.lzl.rpc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lzl.rpc.domain.Person;
import com.lzl.rpc.proxy.RPCProxy;
import com.lzl.rpc.service.QueryPersonnelInfo;

@Controller
@RequestMapping("/lzl")
@ResponseBody
public class LZLController
{
    @RequestMapping("/rpc")
    public String getPersonInfo(@RequestParam String name,@RequestParam String age) {
        Person person = new Person();
        person.setName(name);
        person.setAge(Integer.valueOf(age));
        RPCProxy proxy =new RPCProxy();
        QueryPersonnelInfo queryPersonnelInfo = proxy.getProxyInstance();
        return queryPersonnelInfo.queryPerson(person);        
    }
}
