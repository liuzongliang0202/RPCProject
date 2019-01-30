package com.lzl.rpc.start;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
@Component
public class LZLApplicationContextAware implements ApplicationContextAware
{
    static ApplicationContext applicationContext;
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
        throws BeansException
    {
        LZLApplicationContextAware.applicationContext=applicationContext;
        
    }
    
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
