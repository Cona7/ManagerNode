package com.yammer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class NodeInfoDB {

    private static final Logger LOGGER = LoggerFactory.getLogger(NodeInfoDB.class);

    public static HashMap<Integer, NodeInfo> nodeInfos = new HashMap();
    static{
    }

    public static List<NodeInfo> getAllNodes(){
        return new ArrayList<NodeInfo>(nodeInfos.values());
    }

    public static NodeInfo getNodeByName(String name){
        for (NodeInfo node : nodeInfos.values()){
            if(node.getName().equals(name)){
                return node;
            }
        }
        LOGGER.error("There is no Node in database with this name");
        return null;
    }

    public static NodeInfo createNewNode(NodeInfo nodeInfo){

        Random rand = new Random();
        int  n = rand.nextInt(1000) + 1;

        return nodeInfos.put(n, new NodeInfo(nodeInfo.getName(),
                nodeInfo.getLocation(),nodeInfo.getIPAddress(),nodeInfo.getDescription(),
                nodeInfo.getConnectors(),nodeInfo.getMeta()));
    }
}
