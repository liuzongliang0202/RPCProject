package com.lzl.rpc.execute;

import java.applet.AppletContext;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.context.ApplicationContext;

import com.lzl.rpc.domain.Person;
import com.lzl.rpc.serviceImpl.QueryPersonnelInfoImpl;
import com.lzl.rpc.start.LZLApplicationContextAware;

public class ExecuteService
{
    public static ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 5, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100));
    public static void exec(Socket socket) {
        FutureTask<Object> task = new FutureTask<Object>(new Callable<Object>()
        {

            @Override
            public Object call()
                throws Exception
            {
                OutputStream outputStream= null;
                InputStream inputStream=null;
                ObjectOutputStream oos =null;
                ObjectInputStream ois=null;
                try
                {
                        inputStream=socket.getInputStream();              
                        ois = new ObjectInputStream(inputStream);
                        String serviceImpl=ois.readUTF();
                        String method = (String)ois.readUTF();
                        Person person=(Person)ois.readObject();
                        ApplicationContext context=LZLApplicationContextAware.getApplicationContext();
//                        QueryPersonnelInfoImpl impl=(QueryPersonnelInfoImpl)context.getBean(serviceImpl);
//                        Method method2=impl.getClass().getMethod(method,Person.class);
//                        String result =(String)method2.invoke(impl.getClass().newInstance(), person);
                        String result = (String)context.getBean(serviceImpl).getClass().getMethod(method, Person.class).invoke(context.getBean(serviceImpl).getClass().newInstance(), person);
                        outputStream=socket.getOutputStream();
                        oos =new ObjectOutputStream(outputStream);
                        oos.writeObject(result);
                        oos.flush();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                finally {
                    try
                    {
                        ois.close();
                        oos.close();
                        outputStream.close();
                        inputStream.close();
                        socket.close();
                    }
                    catch (Exception e2)
                    {
                        e2.printStackTrace();
                    }
                }
                return "ok";
            }
        });
        executor.execute(task);     
    }
    
}
