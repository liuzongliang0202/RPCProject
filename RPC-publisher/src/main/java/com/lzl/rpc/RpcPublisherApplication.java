package com.lzl.rpc;

import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lzl.rpc.execute.ExecuteService;

@SpringBootApplication
public class RpcPublisherApplication {

	public static void main(String[] args) {
		SpringApplication.run(RpcPublisherApplication.class, args);
		System.out.println("-------------------------");
		new Thread(new Runnable()
        {           
            @Override
            public void run()
            {    
                try
                {
                    ServerSocket serverSocket =new ServerSocket(9090);
                    while(true) {
                        Socket socket=serverSocket.accept();
                        ExecuteService.exec(socket);
                    } 
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        },"listenerThread").start();
		
	}

}

