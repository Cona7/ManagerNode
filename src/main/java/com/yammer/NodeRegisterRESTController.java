package com.yammer;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.yammer.NodeRegisterDB;
import com.yammer.NodeRegister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/nodeRegister")
@Produces(MediaType.APPLICATION_JSON)
public class NodeRegisterRESTController {

    private final Validator validator;

    private Client client;

    private static final Logger LOG = LoggerFactory.getLogger(NodeRegisterRESTController.class);

    public NodeRegisterRESTController(Validator validator, Client client) {
        this.client = client;
        this.validator = validator;
    }

    @GET
    public Response getNodeRegisters() {
        return Response.ok(NodeInfoDB.getNodeInfos()).build();
    }

    @POST
    public Response createNodeRegistersToOthers(NodeInfo nodeInfo) throws URISyntaxException {
        // validation
        LOG.error("body param" + nodeInfo.toString());
        NodeInfoDB.createNodeRegister(nodeInfo);
//        App.refreshOtherNodes(nodeRegister);

//        for(NodeRegister node: NodeRegisterDB.getNodeRegisters()){
//            WebTarget webTarget = client.target("http://"+node.getIPAdress()+":8080/nodeRegister/other");
//            nodeRegister.setId(2);
//            webTarget.request().post(Entity.json(nodeRegister));
//        }
        return Response.ok().entity(nodeInfo).build();
    }

//    @POST
//    @Path("/other")
//    public Response createNodeRegisters(NodeRegister nodeRegister) throws URISyntaxException {
//        // validation
//        LOG.error("body param" + nodeRegister.toString());
//        NodeRegisterDB.createNodeRegister(nodeRegister);
////        App.refreshOtherNodes(nodeRegister);
//        return Response.ok().build();
//    }


}
