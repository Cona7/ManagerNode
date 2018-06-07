package com.yammer;

import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;

import javax.ws.rs.core.*;

import com.google.gson.Gson;

import org.slf4j.LoggerFactory;


@Produces(MediaType.TEXT_PLAIN)
@Path("/client/")
public class RESTClientController
{
    private Client client;

    public RESTClientController(Client client) throws Exception {
        this.client = client;
    }

    @GET
    @Path("/{nodeName}/{connector}")
    public String getSensors(@PathParam("nodeName") String nodeName,
                             @PathParam("connector") String connector)
    {
        NodeInfo nodeInfo = NodeInfoDB.getNodeByName(nodeName);
        WebTarget webTarget = client.target("http://" + nodeInfo.getIPAddress() +
                ":8080/api/sensorReadings/" + connector);
        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();

        LoggerFactory.getLogger(App.class).error(response.getStatusInfo().toString());

        SensorRecive sensorRecive = new Gson().fromJson(response.readEntity(String.class), SensorRecive.class);

        return sensorRecive.getSensorList().toString();
    }

    @GET
    @Path("/{nodeName}/{connector}/{startDate}")
    public String getSensorsByDateStart(@PathParam("nodeName") String nodeName,
                                        @PathParam("startDate") String startDate,
                                        @PathParam("connector") String connector)
    {
        NodeInfo nodeInfo = NodeInfoDB.getNodeByName(nodeName);
        WebTarget webTarget = client.target("http://" + nodeInfo.getIPAddress() +
                ":8080/api/sensorReadings/" + connector + "?startDate=" + startDate);
        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();

        LoggerFactory.getLogger(App.class).error(response.getStatusInfo().toString());

        SensorRecive sensorRecive = new Gson().fromJson(response.readEntity(String.class), SensorRecive.class);

        return sensorRecive.getSensorList().toString();
    }

    @GET
    @Path("/{nodeName}/{connector}/{startDate}/{endDate}")
    public String getSensorByDateStartToEndDate(@PathParam("startDate") String startDate,
                                                @PathParam("endDate") String endDate,
                                                @PathParam("nodeName") String nodeName,
                                                @PathParam("connector") String connector)
    {
        NodeInfo nodeInfo = NodeInfoDB.getNodeByName(nodeName);
        WebTarget webTarget = client.target("http://" + nodeInfo.getIPAddress() +
                ":8080/api/sensorReadings/" + connector + "?startDate=" + startDate + "&endDate=" + endDate);
        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();

        LoggerFactory.getLogger(App.class).error(response.getStatusInfo().toString());

        SensorRecive sensorRecive = new Gson().fromJson(response.readEntity(String.class), SensorRecive.class);

        return sensorRecive.getSensorList().toString();
    }
}