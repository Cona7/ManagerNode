package com.yammer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class NodeInfoDB {

    private static final Logger LOG = LoggerFactory.getLogger(NodeInfoDB.class);


    public static HashMap<Integer, NodeInfo> nodeInfos = new HashMap();
    static{
        //nodeInfos.put(1, new NodeInfo());
    }


    public static List<NodeInfo> getNodeInfos(){
        return new ArrayList<NodeInfo>(nodeInfos.values());
    }

    public static NodeInfo getNodebyName(String name){
        for (NodeInfo node : nodeInfos.values()){
            LOG.info(node.getName());
            if(node.getName().equals(name)){
                LOG.info(node.getName());
                return node;
            }
        }
        return null;
    }

    public static NodeInfo createNodeRegister(NodeInfo nodeInfo){

        Random rand = new Random();

        int  n = rand.nextInt(1000) + 1;

        return nodeInfos.put(n, new NodeInfo(nodeInfo.getName(),
                nodeInfo.getLocation(),nodeInfo.getIPAdress(),nodeInfo.getDescription(),
                nodeInfo.getConnectors(),nodeInfo.getMeta()));
    }

}
