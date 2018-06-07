package com.yammer;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.client.JerseyClientBuilder;

import org.fusesource.mqtt.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;

public class App extends Application<Configuration> {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    @Override
    public void initialize(Bootstrap<Configuration> b) {
    }

    @Override
    public void run(Configuration c, Environment e) throws Exception {

        final Client client = new JerseyClientBuilder(e).build("ApiRESTClient");

        LOGGER.info("Registering NodeRegisterRESTController");
        e.jersey().register(new NodeRegisterRESTController(e.getValidator(), client));

        LOGGER.info("Registering RESTClientController");
        e.jersey().register(new RESTClientController(client));
    }

    public static void main(String[] args) throws Exception {

        new App().run(args);

        LOGGER.info("Registering MQTT");
        MQTT mqtt = new MQTT();

        mqtt.setHost("10.19.128.214", 1883);
        FutureConnection connection = mqtt.futureConnection();
        connection.connect();
        connection.subscribe(new Topic[]{new Topic("Alarm", QoS.AT_LEAST_ONCE)});

        Thread t1 = new Thread(new Runnable() {
            public void run()
            {
               while(true) {
                   Message message = null;
                   Future<Message> receive;
                   try {
                       receive = connection.receive();
                       message = receive.await();
                       message.ack();
                       String recivedMessage = new String(message.getPayload(), "UTF-8");
                       System.out.println(recivedMessage);
                   } catch (Exception e) {
                       e.printStackTrace();
                   }
               }
            }});
        t1.start();
    }
}