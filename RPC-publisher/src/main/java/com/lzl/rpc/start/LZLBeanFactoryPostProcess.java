package com.lzl.rpc.start;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import com.lzl.rpc.register.PublishService;
@Component
public class LZLBeanFactoryPostProcess implements BeanFactoryPostProcessor
{
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
        throws BeansException
    {
      try
    {
        PublishService.registerService();
        System.out.println("调用postprocess服务");
    }
    catch (Exception e)
    {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
      
    }
    
}
