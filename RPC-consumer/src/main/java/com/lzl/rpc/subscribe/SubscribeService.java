package com.lzl.rpc.subscribe;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class SubscribeService
{
    private static final String zk_service = "192.168.59.128:2181";
    
    public static byte[] subscribe() throws Exception{
        ZooKeeper zk =new ZooKeeper(zk_service, 5000, new Watcher()
        {           
            @Override
            public void process(WatchedEvent event)
            {
               if(event.getType().equals(EventType.NodeChildrenChanged)) {
                   
               } 
            }
        });
        byte[] data = zk.getData("/service/rpc", true, new Stat());
        return data;
    }
}
