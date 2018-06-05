package com.yammer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class NodeInfoDB {


    public static HashMap<Integer, NodeInfo> nodeInfos = new HashMap();
    static{
        //nodeInfos.put(1, new NodeInfo());
    }


    public static List<NodeInfo> getNodeInfos(){
        return new ArrayList<NodeInfo>(nodeInfos.values());
    }

    public static NodeInfo createNodeRegister(NodeInfo nodeInfo){

        Random rand = new Random();

        int  n = rand.nextInt(1000) + 1;

        return nodeInfos.put(n, new NodeInfo(nodeInfo.getName(),
                nodeInfo.getLocation(),nodeInfo.getIPAdress(),nodeInfo.getDescription(),
                nodeInfo.getConnectors(),nodeInfo.getMeta()));
    }

}
