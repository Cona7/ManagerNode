package com.yammer;

import java.net.URISyntaxException;

import javax.validation.Validator;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/nodeRegister")
@Produces(MediaType.APPLICATION_JSON)
public class NodeRegisterRESTController {

    private final Validator validator;

    private static final Logger LOGGER = LoggerFactory.getLogger(NodeRegisterRESTController.class);

    public NodeRegisterRESTController(Validator validator) {
        this.validator = validator;
    }

    @GET
    public Response getNodeRegisters() {
        return Response.ok(NodeInfoDB.getAllNodes()).build();
    }

    @POST
    public Response createNewNode(NodeInfo nodeInfo) throws URISyntaxException {
        LOGGER.error("body param" + nodeInfo.toString());
        NodeInfoDB.createNewNode(nodeInfo);
        return Response.ok().entity(nodeInfo).build();
    }
}
