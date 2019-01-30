package com.lzl.rpc.serviceImpl;

import org.springframework.stereotype.Service;

import com.lzl.rpc.domain.Person;
import com.lzl.rpc.service.QueryPersonnelInfo;
@Service(value="QueryPersonnelInfoImpl")
public class QueryPersonnelInfoImpl implements QueryPersonnelInfo
{
    @Override
    public String query(String name, int age)
    {
        String msg = "liuzongliang";
        return msg;
    }

    @Override
    public String queryPerson(Person person)
    {
        String msg = "远程服务调用成功";
        return msg;
    }  
}
