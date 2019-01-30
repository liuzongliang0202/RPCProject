package com.lzl.rpc.register;


import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import com.lzl.rpc.filter.ServiceFilter;

public class PublishService
{
    private static final String zk_service = "192.168.59.128:2181";
    
    private static final String path = "/service/rpc";
    
    static CountDownLatch countDownLatch = new CountDownLatch(1);
    
    public static void registerService() {
        String url = "10.73.158.134:9090:QueryPersonnelInfo:QueryPersonnelInfoImpl";
        byte[] data = url.getBytes();
        
        ZooKeeper zooKeeper;
        try
        {
            zooKeeper = new ZooKeeper(zk_service, 10000, new Watcher()
            {            
                @Override
                public void process(WatchedEvent event)
                {
                    if(event.getState().equals(KeeperState.Disconnected)) {
                        System.out.println("已下线，不能再提供服务");
                        ServiceFilter.isOpen = false;
                    }
                    if(event.getState().equals(KeeperState.SyncConnected)) {
                        ServiceFilter.isOpen = true;
                    }
                }
            });
            zooKeeper.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            System.out.println("节点创建成功");
        }
        catch (Exception e)
        {
            System.out.println("不能正常连接");
            e.printStackTrace();
        }       
    }
}
