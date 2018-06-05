package com.yammer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NodeRegisterDB {

    public static HashMap<Integer, NodeRegister> nodeRegisters = new HashMap();
    static{
       nodeRegisters.put(1, new NodeRegister(1,"localhost","ds","ds"));
    }


    public static List<NodeRegister> getNodeRegisters(){
        return new ArrayList<NodeRegister>(nodeRegisters.values());
    }

    public static NodeRegister createNodeRegister(NodeRegister nodeRegister){
        return nodeRegisters.put(nodeRegister.getId(), new NodeRegister(nodeRegister.getId(),
                nodeRegister.getIPAdress(),nodeRegister.getName(),nodeRegister.getDevice()));
    }
}
