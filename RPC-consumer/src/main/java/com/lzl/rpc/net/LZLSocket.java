package com.lzl.rpc.net;

import java.io.IOException;
import java.io.InputStream;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import java.lang.reflect.Method;
import java.net.Socket;

import com.lzl.rpc.domain.Person;

public class LZLSocket
{
    public static String connect(String ip, String port, String serviceImpl, Method method, Person person)
    {
        String msg = "调用失败";
        Socket socket = null;
        OutputStream outputStream = null;
        InputStream inputStream = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        
        try
        {
            int portId = Integer.valueOf(port);
            socket = new Socket(ip, portId);
            outputStream = socket.getOutputStream(); 
            inputStream = socket.getInputStream();
            oos = new ObjectOutputStream(outputStream);        
            oos.writeUTF(serviceImpl);
            oos.writeUTF(method.getName());
            oos.writeObject(person);
            oos.flush();
            System.out.println("正在调用远程服务。。。。。。。。");
            
            ois = new ObjectInputStream(inputStream);
            msg = (String)ois.readObject();
            System.out.println(msg);
            return msg;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                oos.close();
                ois.close();
                outputStream.close();
                inputStream.close();
                socket.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return msg;
    }
}
