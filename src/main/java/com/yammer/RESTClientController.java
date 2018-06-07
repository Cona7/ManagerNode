package com.yammer;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;

import javax.ws.rs.core.*;

import com.google.gson.Gson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Produces(MediaType.TEXT_PLAIN)
@Path("/client/")
public class RESTClientController
{
    private Client client;

    private static final Logger LOG = LoggerFactory.getLogger(RESTClientController.class);

    public RESTClientController(Client client) throws Exception {
        this.client = client;

    }

    public RESTClientController() {

    }



    @GET
    @Path("/{name}/sensors/")
    public String getSensors(@PathParam("name") String name)
    {
        NodeInfo nodeInfo = new NodeInfo();
        nodeInfo = NodeInfoDB.getNodebyName(name);
        LOG.info(nodeInfo.getIPAdress());
        WebTarget webTarget = client.target("http://" + nodeInfo.getIPAdress() + ":8080/api/sensorReadings/sensor_temp");
        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();

        LoggerFactory.getLogger(App.class).error(response.getStatusInfo().toString());

//        Object output = response.readEntity(String.class);

        SensorRecive sensorRecive = new Gson().fromJson(response.readEntity(String.class), SensorRecive.class);

        return sensorRecive.getSensorList().toString();
    }

    @GET
    @Path("/sensors/")
    public String getSensors1()
    {
        WebTarget webTarget = client.target("http://10.19.128.214:8080/api/sensorReadings/sensor_temp");
        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();

        LoggerFactory.getLogger(App.class).error(response.getStatusInfo().toString());

//        Object output = response.readEntity(String.class);

        SensorRecive sensorRecive = new Gson().fromJson(response.readEntity(String.class), SensorRecive.class);

        return sensorRecive.getSensorList().toString();
    }

//
//        JsonParser jp = new JsonParser();
//        JsonElement jsonElement = jp.parse(output.toString());
//        JsonArray jsonSensors = jsonElement.getAsJsonArray();
//
//        Gson gson = new Gson();
//        for (JsonElement object : jsonSensors) {
//            Sensor sensor = gson.fromJson(object, Sensor.class);
//            sensors.add(sensor);
//        }
//        return sensors.toString();



    @GET
    @Path("/sensors/{startDate}/{endDate}")
    public String getSensorbyDateStartToEnd(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate)
    {
        WebTarget webTarget = client.target("http://10.129.0.141:8080/api/sensorReadings/sensor_temp?startDate="+startDate+"&endDate="+endDate);
        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();

        LoggerFactory.getLogger(App.class).error(response.getStatusInfo().toString());;

        SensorRecive sensorRecive = new Gson().fromJson(response.readEntity(String.class), SensorRecive.class);

        return sensorRecive.getSensorList().toString();

    }

    @GET
    @Path("/{name}/sensors/{startDate}")
    public String getSensorsbyDateStart(@PathParam("name") String name, @PathParam("startDate") String startDate)
    {
        NodeInfo nodeInfo = new NodeInfo();
        nodeInfo = NodeInfoDB.getNodebyName(name);
        WebTarget webTarget = client.target("http://" + nodeInfo.getIPAdress() + ":8080/api/sensorReadings/sensor_temp?startDate="+startDate);
        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();

        LoggerFactory.getLogger(App.class).error(response.getStatusInfo().toString());;

        SensorRecive sensorRecive = new Gson().fromJson(response.readEntity(String.class), SensorRecive.class);

        return sensorRecive.getSensorList().toString();

    }
//
//    @GET
//    @Path("/sen/")
//    public String getEmployeeById()
//    {
//        //Do not hard code in your application
//        WebTarget webTarget = client.target("http://"+NodeRegisterDB.nodeRegisters.get(1).getIPAdress()+":8080/sensors/temp");
//        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
//        Response response = invocationBuilder.get();
//        List<Sensor> sensors= new ArrayList<Sensor>();
//        sensors = response.readEntity(ArrayList.class);
//        return sensors.toString();
//    }


    @POST
    @Path("/nodeRegister")
    public Response postNodeRegister(NodeRegister nodeRegister)
    {
        WebTarget webTarget = client.target("http://localhost:8080/nodeRegister");
        Response response = webTarget.request().post(Entity.json(nodeRegister));
        return response;
    }
//
//    @POST
//    @Path("/nodeRegister/other")
//    public Response postNodeRegToOtherSensors(NodeRegister nodeRegister)
//    {
//        WebTarget webTarget = client.target("http://localhost:8080/nodeRegister");
//        Response response = webTarget.request().post(Entity.json(nodeRegister));
//        return response;
//    }
}