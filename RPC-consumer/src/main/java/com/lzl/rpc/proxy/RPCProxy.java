package com.lzl.rpc.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.lzl.rpc.domain.Person;
import com.lzl.rpc.net.LZLSocket;
import com.lzl.rpc.service.QueryPersonnelInfo;
import com.lzl.rpc.subscribe.SubscribeService;

public class RPCProxy implements InvocationHandler
{
    public RPCProxy()
    {        
    }
    
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
        throws Throwable
    {
        Person person = (Person)args[0];
        byte[] data = SubscribeService.subscribe();
        String msg = new String(data);
        String[] message = msg.split(":");
        String ip = message[0];
        String port = message[1];
        String service = message[2];//是用来判断服务的，当前就一个节点一个服务，就不用判断
        String serviceImpl = message[3];
        /*
         * 这里还缺一个负载策略，待实现
         */
        String result = LZLSocket.connect(ip, port, serviceImpl, method, person);
        System.out.println("result="+result);
        return result;
    }
    
    public QueryPersonnelInfo getProxyInstance() {
        return (QueryPersonnelInfo)Proxy.newProxyInstance(QueryPersonnelInfo.class.getClassLoader(), new Class<?>[]{QueryPersonnelInfo.class}, this);
    }
}