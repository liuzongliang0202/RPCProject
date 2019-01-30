package com.lzl.rpc.service;

import com.lzl.rpc.domain.Person;

public interface QueryPersonnelInfo
{
    String queryPerson(Person person);
    
    String query(String name, int age);
}
