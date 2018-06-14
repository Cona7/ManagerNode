package com.yammer;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.io.IOException;

public class PhilipsHueMethods {

    private static final Logger LOGGER = LoggerFactory.getLogger(PhilipsHueMethods.class);

    private static final String TURN_ON_LIGHTS_BODY = "{\"on\":true}";

    private static final String TURN_OFF_LIGHTS_BODY = "{\"on\":false}";

    private static final String PUT_RED_LIGHT = "{\"on\":true, \"sat\":254, \"bri\":254,\"hue\":10000}";

    private static final String PUT_BLUE_LIGHT = "{\"on\":true, \"sat\":254, \"bri\":254,\"hue\":45000}";

    private static final String PUT_GREEN_LIGHT = "{\"on\":true, \"sat\":254, \"bri\":254,\"hue\":25000}";

    private static String USER_NAME_BODY;

    public PhilipsHueMethods(){

        Config cfg = new Config();

        Properties configFile = new java.util.Properties();
        try {
            configFile.load(this.getClass().getClassLoader().
                    getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        USER_NAME_BODY = cfg.getProperty("userName");
    }

    public static void philipsHueTurnOn(int light) {
            HttpPut request = new HttpPut("http://10.19.4.100/api/" + USER_NAME_BODY + "/lights/" + light + "/state");
            try {
                request.setEntity(new StringEntity(TURN_ON_LIGHTS_BODY));

                HttpResponse response = PhilipsHueApiHelper.getInstance().getHttpClient().execute(request);
                EntityUtils.consumeQuietly(response.getEntity());
            } catch (Exception e) {
                LOGGER.error("Error", e);
            }
    }

    public static void philipsHueTurnOff(int light) {
            HttpPut request = new HttpPut("http://10.19.4.100/api/" + USER_NAME_BODY + "/lights/" + light + "/state");
            try {
                request.setEntity(new StringEntity(TURN_OFF_LIGHTS_BODY));
                HttpResponse response = PhilipsHueApiHelper.getInstance().getHttpClient().execute(request);
                EntityUtils.consumeQuietly(response.getEntity());
            } catch (Exception e) {
                LOGGER.error("Error", e);
            }
    }

    public static void philipsHueRedLights(int light) {

            HttpPut request = new HttpPut("http://10.19.4.100/api/" + USER_NAME_BODY + "/lights/" + light + "/state");
            try {
                request.setEntity(new StringEntity(PUT_RED_LIGHT));
                HttpResponse response = PhilipsHueApiHelper.getInstance().getHttpClient().execute(request);
                EntityUtils.consumeQuietly(response.getEntity());
            } catch (Exception e) {
                LOGGER.error("Error", e);
            }
    }

    public static void philipsHueGreenLights(int light) {

            HttpPut request = new HttpPut("http://10.19.4.100/api/" + USER_NAME_BODY + "/lights/" + light + "/state");
            try {
                request.setEntity(new StringEntity(PUT_GREEN_LIGHT));
                HttpResponse response = PhilipsHueApiHelper.getInstance().getHttpClient().execute(request);
                EntityUtils.consumeQuietly(response.getEntity());
            } catch (Exception e) {
                LOGGER.error("Error", e);

        }
    }

    public static void philipsHueBlueLights(int light) {
            HttpPut request = new HttpPut("http://10.19.4.100/api/" + USER_NAME_BODY + "/lights/" + light + "/state");
            try {
                request.setEntity(new StringEntity(PUT_BLUE_LIGHT));
                HttpResponse response = PhilipsHueApiHelper.getInstance().getHttpClient().execute(request);
                EntityUtils.consumeQuietly(response.getEntity());
            } catch (Exception e) {
                LOGGER.error("Error", e);

        }
    }
}
