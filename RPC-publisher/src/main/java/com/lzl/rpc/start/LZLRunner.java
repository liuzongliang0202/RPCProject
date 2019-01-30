package com.lzl.rpc.start;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.lzl.rpc.register.PublishService;
/**
 * 
 * 服务启动时往注册中心注册服务
 *
 * @author lzl
 * @version v01 2019年1月28日
 * 
 */
@Component
public class LZLRunner implements CommandLineRunner
{
    @Override
    public void run(String... args)
        throws Exception
    {
//        //注册服务
//        PublishService.registerService();
        System.out.println("开始服务");
    }    
}
